package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static db.DBSettings.DB_LOGIN;
import static db.DBSettings.DB_PASS;
import static db.DBSettings.DB_PATH;

public class ConnectionManagerPostgresSQL implements IConnectionManager{

    private static final ConnectionManagerPostgresSQL INSTANCE =
            new ConnectionManagerPostgresSQL();

    private Connection connection;

    private ConnectionManagerPostgresSQL() {
        try {
            Class.forName("org.postgresql.Driver");
            connection =
                    DriverManager.getConnection(DB_PATH, DB_LOGIN, DB_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionManagerPostgresSQL getInstance(){
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }
}
