package servlets;

import db.dao.UserDAO;
import org.apache.log4j.Logger;
import services.RegistrationService;
import services.RegistrationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    public static RegistrationService registrationService = new RegistrationServiceImpl();
    private static final Logger log = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Start RegistrationServlet doPost");

        String login =  req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName  = req.getParameter("lastName");
        String email = req.getParameter("email");

        try {
            if (registrationService.regUser(firstName, lastName, login, email, password) != null){
                req.getSession().setAttribute("isAuth", true);
                resp.sendRedirect("/puzzles/puzzle");
                //req.getRequestDispatcher("/hello").forward(req, resp);
            }
            else {
                getServletContext().getRequestDispatcher("/").forward(req, resp);
            }
        } catch (UserDAO.UserDAOException e) {
            log.error(e);
        }
    }
}
