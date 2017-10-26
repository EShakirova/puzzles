package servlets;

import org.apache.log4j.Logger;
import services.backupDB.BackupDBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BackupDBServlet extends HttpServlet {

    private static BackupDBService dbs = new BackupDBService();
    private static final Logger log = Logger.getLogger(BackupDBServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (dbs.startThreads()) {
            req.setAttribute("errorText", "Dead lock");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/backupDB.jsp").forward(req, resp);
        }
    }


}
