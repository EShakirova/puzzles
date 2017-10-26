package db.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import pojo.Menu;
import services.backupDB.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static db.DBSettings.MENU_GET_ALL_BY_USER;

@Component
public class MenuDAO {

    public static class MenuDAOException extends Exception {
    }

    public DBConnectionPool connectionPool = DBConnectionPool.getInstance();

    private static final Logger log = Logger.getLogger(PuzzleDAO.class);

    public List<Menu> getAllByUser(Boolean isAdmin) throws MenuDAOException {
        log.info("Start MENU_GET_ALL_BY_USER ");
        List<Menu> menuList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(MENU_GET_ALL_BY_USER);
            statement.setBoolean(1, isAdmin);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Menu menu = new Menu(
                        resultSet.getString("name"),
                        resultSet.getString("ahref"));
                menuList.add(menu);
            }
        } catch (SQLException e) {
            log.info(e);
        }
        finally {
            connectionPool.closeConnection(connection);
        }
        return menuList;
    }

}
