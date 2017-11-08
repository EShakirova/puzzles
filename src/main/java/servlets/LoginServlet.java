package servlets;

import entity.User;
import org.apache.log4j.Logger;
import services.AuthorizationService;
import services.AuthorizationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static AuthorizationService as = new AuthorizationServiceImpl();
    private static final Logger log = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Start LoginServlet doGet");

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Start LoginServlet doPost");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        req.getSession().setAttribute("isAuth", false);
        User user = as.auth(login, password);

        if (user!=null) {
            req.getSession().setAttribute("isAuth", true);
            req.getSession().setAttribute("user", user );
            // resp.sendRedirect("/puzzles/puzzle");
            req.getRequestDispatcher("/menu.jsp").forward(req, resp);
        } else {
            req.setAttribute("login", login);
            req.setAttribute("password", password);
            req.setAttribute("isAuth", false);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
