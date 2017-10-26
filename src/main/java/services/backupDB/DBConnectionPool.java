package services.backupDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static db.DBSettings.*;

public class DBConnectionPool {

    private int maxCount = 15;
    private BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(maxCount, false);
    private static DBConnectionPool instance;

     private DBConnectionPool() {
        try {
            Class.forName("org.postgresql.Driver");
            for (int i = 0; i < maxCount; i++) {
                pool.add(DriverManager.getConnection(DB_PATH, DB_LOGIN, DB_PASS));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DBConnectionPool getInstance(){
        if (instance == null) {
            instance = new DBConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = pool.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection){
        if (connection != null){
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
