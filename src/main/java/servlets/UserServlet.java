package servlets;

import db.dao.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(PuzzlesServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Start UsersServlet doGet");
        int userID = Integer.valueOf(req.getParameter("userID"));

        UserDAO userDAO = new UserDAO();
        try {
            userDAO.getByID(userID);
            req.setAttribute("user", userDAO);
        } catch (UserDAO.UserDAOException e) {
            log.info(e);
        }
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }
}
