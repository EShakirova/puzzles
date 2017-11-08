package servlets;

import db.dao.PuzzleDAO;
import entity.Puzzle;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PuzzleServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(PuzzlesServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Start PuzzleServlet doGet ");
        int puzzleID = Integer.valueOf(req.getParameter("puzzleID"));

        PuzzleDAO puzzleDAO = new PuzzleDAO();
        try {
            Puzzle puzzle = puzzleDAO.getById(puzzleID);
            req.setAttribute("puzzle", puzzle);
        } catch (PuzzleDAO.PuzzleDAOException e) {
            log.info(e);
        }
        req.getRequestDispatcher("/puzzle.jsp").forward(req, resp);
    }
}
