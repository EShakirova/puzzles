package db.dao;

import db.ConnectionManagerPostgresSQL;
import db.IConnectionManager;
import org.apache.log4j.Logger;
import pojo.Puzzle;
import pojo.StatisticItem;
import pojo.User;
import services.backupDB.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static db.DBSettings.*;

public class StatisticItemDAO implements ICommonDAO<StatisticItem>{
    public static class StatisticItemDAOException extends Exception {
    };
    private static IConnectionManager manager;

    static {
        manager = ConnectionManagerPostgresSQL.getInstance();
    }

    public DBConnectionPool connectionPool = DBConnectionPool.getInstance();
    private static final Logger log = Logger.getLogger(StatisticItemDAO.class);

    public  List<StatisticItem> getAll() throws StatisticItemDAO.StatisticItemDAOException {
        List<StatisticItem> statisticItemList = new ArrayList<>();
        Connection connection = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(STATISTICITEM_GET_ALL);
           //JOIN
            while (resultSet.next()) {
               /* Puzzle puzzle = PuzzleDAO.getByID(resultSet.getInt("id_puzzle"));
                User user = UserDAO.getByID(resultSet.getInt("id_user"));

                StatisticItem statisticItem = new StatisticItem(
                        resultSet.getInt("id"),
                        puzzle,
                        resultSet.getShort("attempts_count"),
                        resultSet.getLong("elasped_time"),
                        resultSet.getBoolean("is_solved"),
                        user);
                statisticItemList.add(statisticItem);*/
            }
        } catch (SQLException e) {
            log.error(e);
            throw new StatisticItemDAOException();
        }
        connectionPool.closeConnection(connection);
        return statisticItemList;
    }


    public  List<StatisticItem> getAllByPuzzle(Puzzle puzzle) throws StatisticItemDAO.StatisticItemDAOException {
        List<StatisticItem> statisticItemList = new ArrayList<>();
        Connection connection = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(STATISTICITEM_GET_BY_PUZZLE);
            preparedStatement.setInt(1, puzzle.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //use JOIN here
                /*User user = UserDAO.getByID(resultSet.getInt("id_user"));
                StatisticItem statisticItem = new StatisticItem(
                        resultSet.getInt("id"),
                        puzzle,
                        resultSet.getShort("attempts_count"),
                        resultSet.getLong("elasped_time"),
                        resultSet.getBoolean("is_solved"),
                        user);
                statisticItemList.add(statisticItem);*/
            }
        } catch (SQLException e) {
            log.error(e);
            throw new StatisticItemDAOException();
        }
        connectionPool.closeConnection(connection);
        return statisticItemList;
    }

    public  List<StatisticItem> getAllByUser(User user) throws StatisticItemDAO.StatisticItemDAOException {
        List<StatisticItem> statisticItemList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select si.id, si.id_puzzle, si.elasped_time, si.attempts_count, si.is_solved" +
                            " from statistic_item si " +
                             " where si.id_user  = ?");
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            //JOIN
            while (resultSet.next()) {
               /* Puzzle puzzle = PuzzleDAO.getByID(resultSet.getInt("id_puzzle"));
                StatisticItem statisticItem = new StatisticItem(
                        resultSet.getInt("id"),
                        puzzle,
                        resultSet.getShort("attempts_count"),
                        resultSet.getLong("elasped_time"),
                        resultSet.getBoolean("is_solved"),
                        user);
                statisticItemList.add(statisticItem);*/
            }
        } catch (SQLException e) {
            log.error(e);
            throw new StatisticItemDAOException();
        }
        connectionPool.closeConnection(connection);
        return statisticItemList;
    }

    public  StatisticItem getByID(int in_id) throws StatisticItemDAOException {
        StatisticItem statisticItem = null;
        Connection connection = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(STATISTICITEM_GET_BY_ID);
            preparedStatement.setInt(1, in_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            //JOIN
        /*    Puzzle puzzle = PuzzleDAO.getByID(resultSet.getInt("id_puzzle"));
            User user = UserDAO.getByID(resultSet.getInt("id_user"));
            statisticItem = new StatisticItem(
                    resultSet.getInt("id"),
                    puzzle,
                    resultSet.getShort("attempts_count"),
                    resultSet.getLong("elasped_time"),
                    resultSet.getBoolean("is_solved"),
                    user);
            statisticItem.setUser(user);
*/
        } catch (SQLException e) {
            log.error(e);
            throw new StatisticItemDAOException();
        }
        connectionPool.closeConnection(connection);
        return statisticItem;
    }

    public boolean insert(StatisticItem statisticItem) {
        boolean result = false;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(STATISTICITEM_INSERT);
            preparedStatement.setInt(1, statisticItem.getPuzzle().getId());
            preparedStatement.setShort(2, statisticItem.getUnsuccessfulAttemptsCount());
            preparedStatement.setLong(3, statisticItem.getElapsedTime());
            preparedStatement.setBoolean(4, statisticItem.isSolved());
            preparedStatement.setInt(5, statisticItem.getUser().getId());
            /*int result =*/
            log.info(statisticItem.getUser().getId());
            if (preparedStatement.executeUpdate()==1){
                result = true;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.closeConnection(connection);
        }
        return result;
    }

    public void update(StatisticItem statisticItem) throws StatisticItemDAOException {
        //int result = 0;
        Connection connection = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(STATISTICITEM_UPDATE);
            preparedStatement.setInt(1, statisticItem.getPuzzle().getId());
            preparedStatement.setLong(2, statisticItem.getElapsedTime());
            preparedStatement.setShort(3, statisticItem.getUnsuccessfulAttemptsCount());
            preparedStatement.setBoolean(4, statisticItem.isSolved());
            preparedStatement.setInt(5, statisticItem.getUser().getId());
            preparedStatement.setInt(6, statisticItem.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            throw new StatisticItemDAOException();
        }
        connectionPool.closeConnection(connection);
    }

    public  void delete(StatisticItem statisticItem) throws StatisticItemDAOException {
        Connection connection = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(STATISTICITEM_DELETE);
            preparedStatement.setInt(1, statisticItem.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            throw new StatisticItemDAOException();
        }
        connectionPool.closeConnection(connection);
    }
}
