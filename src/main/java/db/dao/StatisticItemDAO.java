package db.dao;

import db.ConnectionManagerPostgresSQL;
import db.IConnectionManager;
import dto.StatisticItemDTO;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.Puzzle;
import pojo.StatisticItem;
import pojo.User;
import services.backupDB.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static db.DBSettings.*;

@Component
public class StatisticItemDAO {
    public static class StatisticItemDAOException extends Exception {
    };
    private static final Logger log = Logger.getLogger(StatisticItemDAO.class);

    @Autowired
    private SessionFactory factory;

    public void insert(StatisticItem statisticItem){
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(statisticItem);
        session.getTransaction().commit();
        session.close();
    }

    public void update(StatisticItem statisticItem){
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(statisticItem);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(StatisticItem statisticItem){
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(statisticItem);
        session.getTransaction().commit();
        session.close();
    }

    public StatisticItem getById(int id){
        Session session = factory.openSession();
        StatisticItem statisticItem =(StatisticItem)session.get(StatisticItem.class,id);
        return statisticItem;
    }

    public  List<StatisticItem> getAll() throws StatisticItemDAOException {
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


}
