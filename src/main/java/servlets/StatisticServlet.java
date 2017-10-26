package servlets;

import db.dao.StatisticItemDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatisticServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(StatisticServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Start StatisticServlet doGet");

        StatisticItemDAO statisticItemDAO = new StatisticItemDAO() ;

    }
}
