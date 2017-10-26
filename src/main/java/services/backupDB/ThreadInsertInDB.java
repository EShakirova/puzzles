package services.backupDB;


import db.dao.ICommonDAO;
import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;

public class ThreadInsertInDB<T, V extends ICommonDAO<T>> extends Thread {
    private static final Logger log = Logger.getLogger(ThreadInsertInDB.class);

    private BlockingQueue<T> queue;
    private V objectDAO;

    public ThreadInsertInDB(BlockingQueue<T> queue, V objectDAO) {
        this.queue = queue;
        this.objectDAO = objectDAO;
    }

    @Override
    public void run() {
        try {
            while (!(ThreadPutInQueue.prodComplete && queue.isEmpty())) {
                //if (queue != null) {
                while (!queue.isEmpty()) {
                    T item = queue.take();
                    if (!objectDAO.insert(item)) {
                        queue.put(item);

                    }
                }//sleep(100);
            }
        } catch (InterruptedException e) {
            log.error(e);
        } catch (Exception e) {
            log.error(e);
        }
    }
}
