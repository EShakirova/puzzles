package db.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import pojo.DifficultyLevel;
import pojo.Menu;
import services.backupDB.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static db.DBSettings.DIFFICULTYLEVEL_GET_ALL;
import static db.DBSettings.MENU_GET_ALL_BY_USER;

@Component
public class DifficultyLevelDAO {
    public static class DifficultyLevelDAOException extends Exception {
    }

    public DBConnectionPool connectionPool = DBConnectionPool.getInstance();

    private static final Logger log = Logger.getLogger(PuzzleDAO.class);

    public List<DifficultyLevel> getAll() throws DifficultyLevelDAOException {
        log.info("Start DIFFICULTY_LEVEL_GET_ALL ");
        List<DifficultyLevel> difficultyLevels = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(DIFFICULTYLEVEL_GET_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DifficultyLevel difficultyLevel = new DifficultyLevel(
                        resultSet.getInt("id"),
                        resultSet.getString("name"));
                difficultyLevels.add(difficultyLevel);
            }
        } catch (SQLException e) {
            log.info(e);
            throw new DifficultyLevelDAOException();
        } finally {
            connectionPool.closeConnection(connection);
        }
        return difficultyLevels;
    }

    public DifficultyLevel getByID(int id) throws DifficultyLevelDAOException {
        log.info("Start DIFFICULTY_LEVEL_GET_ALL ");
        DifficultyLevel difficultyLevel = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DIFFICULTYLEVEL_GET_BY_ID");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            difficultyLevel = new DifficultyLevel(resultSet.getInt("id"), resultSet.getString("name"));
        } catch (SQLException e) {
            log.info(e);
            throw new DifficultyLevelDAOException();
        } finally {
            connectionPool.closeConnection(connection);
        }
        return difficultyLevel;
    }
}


