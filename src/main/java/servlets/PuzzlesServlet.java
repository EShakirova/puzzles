package servlets;

import db.dao.PuzzleDAO;
import org.apache.log4j.Logger;
import pojo.Puzzle;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PuzzlesServlet extends HttpServlet{

    private static final Logger log = Logger.getLogger(PuzzlesServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Start PuzzlesServlet doGet");

        List<Puzzle> puzzleList = new ArrayList<>();
        PuzzleDAO puzzleDAO = new PuzzleDAO();
        try {
            User user = (User)req.getSession().getAttribute("user");
            if (!user.isAdmin()) {
                puzzleList.addAll(puzzleDAO.getAllWithStatByUser(user.getId()));
            }
            else
            {
                puzzleList.addAll(puzzleDAO.getAll());
            }
            req.setAttribute("puzzle", puzzleList);
        } catch (PuzzleDAO.PuzzleDAOException e) {
            //e.printStackTrace();
            log.error("Что-то сломалось ", e);
        }
        req.getRequestDispatcher("/puzzlesForAdmin.jsp").forward(req, resp);

    }
}
