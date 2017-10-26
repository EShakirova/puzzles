package services.backupDB;

import classesFromXSD.Statistics;
import db.dao.PuzzleDAO;
import db.dao.StatisticItemDAO;
import db.dao.UserDAO;
import org.apache.log4j.Logger;
import pojo.Puzzle;
import pojo.StatisticItem;
import pojo.User;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BackupDBService {
    private static Statistics statistic;
    public static BlockingQueue<Puzzle> queuePuzzle = new LinkedBlockingQueue<>();
    public static BlockingQueue<User> queueUser = new LinkedBlockingQueue<>();
    public static BlockingQueue<StatisticItem> queueStatItem = new LinkedBlockingQueue<>();
    private static final Logger log = Logger.getLogger(BackupDBService.class);

    public boolean startThreads() {

        boolean error = false;

        log.info("Start BackupDBServlet doGet");
        ThreadPutInQueue threadPutInQueue = new ThreadPutInQueue(queuePuzzle, queueUser, queueStatItem);

        PuzzleDAO puzzleDAO = new PuzzleDAO();
        UserDAO userDAO = new UserDAO();
        StatisticItemDAO statisticItemDAO = new StatisticItemDAO();

        ThreadInsertInDB threadInsertInDBPuzzle = new ThreadInsertInDB(queuePuzzle, puzzleDAO);
        ThreadInsertInDB threadInsertInDBUser = new ThreadInsertInDB(queueUser, userDAO);
        ThreadInsertInDB threadInsertInDBStatItem = new ThreadInsertInDB(queueStatItem, statisticItemDAO);

        threadPutInQueue.start();
        threadInsertInDBPuzzle.start();
        threadInsertInDBUser.start();
        threadInsertInDBStatItem.start();

        try {
            int cntPuzzle = 0;
            int cntStat = 0;
            int cntUser = 0;
            Thread.sleep(1000);
            error = false;
            while (!(queuePuzzle.isEmpty() &&
                    queueStatItem.isEmpty() &&
                    queueUser.isEmpty())) {

                if (cntPuzzle == queuePuzzle.size()
                        && cntUser == queueUser.size()
                        && cntStat == queueStatItem.size()) {
                    error = true;
                    break;
                }
                cntStat = queueStatItem.size();
                cntUser = queueUser.size();
                cntPuzzle = queuePuzzle.size();
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return error;
    }
}
