package servlets;

import db.dao.UserDAO;
import org.apache.log4j.Logger;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsersServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(PuzzlesServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Start UsersServlet doGet");

        List<User> userList = new ArrayList<>();
        UserDAO user = new UserDAO();
        try {
            userList.addAll(user.getAll());
            req.setAttribute("users", userList);

        } catch (UserDAO.UserDAOException e) {
            log.info(e);
        }
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }
}
