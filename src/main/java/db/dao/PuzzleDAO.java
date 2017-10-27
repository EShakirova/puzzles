package db.dao;

import db.ConnectionManagerPostgresSQL;
import db.IConnectionManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import pojo.*;
import services.backupDB.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static db.DBSettings.*;

@Component
public class PuzzleDAO implements ICommonDAO<Puzzle> {

    public static class PuzzleDAOException extends Exception {
    }

    private static IConnectionManager manager;

    static {
        manager = ConnectionManagerPostgresSQL.getInstance();
    }

    public DBConnectionPool connectionPool = DBConnectionPool.getInstance();

    private static final Logger log = Logger.getLogger(PuzzleDAO.class);

    //пазлы со статистикой для списка пазлов по пользователю(роль "пользователь")
    public List<Puzzle> getAllWithStatByUser(User user) throws PuzzleDAOException {
        log.info("Start PZZLE_GET_ALL_WITH_STAT_BY_USER ");
        List<Puzzle> puzzleList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(PZZLE_GET_ALL_WITH_STAT_BY_USER);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PuzzleWithStatistic puzzle = new PuzzleWithStatistic(
                        resultSet.getInt("pid"),
                        resultSet.getBoolean("behavior"),
                        resultSet.getString("question"),
                        resultSet.getString("answer"),
                        null,
                        resultSet.getShort("attempts_count"),
                        resultSet.getLong("elasped_time"),
                        resultSet.getBoolean("is_solved"));
                DifficultyLevel difficultyLevel = new DifficultyLevel(
                        resultSet.getInt("did"),
                        resultSet.getString("dname"));
                puzzle.setDifficultyLevel(difficultyLevel);
            }
        } catch (SQLException e) {
            log.info(e);
        }
        finally {
            connectionPool.closeConnection(connection);
        }
        return puzzleList;
    }
//список пазлов со статистикой ответов по пользователям для роли "Админ"
        public List<PuzzleWithStatistic> getAllwithStat() throws PuzzleDAOException {
        log.info("Start PUZZLE_GET_ALL ");
        List<PuzzleWithStatistic> puzzleList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(PUZZLE_GET_ALL_WITH_STAT);
            while (resultSet.next()) {
                PuzzleWithStatistic puzzle = new PuzzleWithStatistic(
                        resultSet.getInt("id"),
                        resultSet.getBoolean("behavior"),
                        resultSet.getString("question"),
                        "",
                        null,
                        resultSet.getInt("cntStatistics"));
                DifficultyLevel difficultyLevel = new DifficultyLevel(
                        resultSet.getInt("did"),
                        resultSet.getString("dname"));
                puzzle.setDifficultyLevel(difficultyLevel);
                puzzleList.add(puzzle);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new PuzzleDAOException();
        } finally {
            connectionPool.closeConnection(connection);
        }
        return puzzleList;
    }

    public List<Puzzle> getAll() throws PuzzleDAOException {
        log.info("Start PUZZLE_GET_ALL ");
        List<Puzzle> puzzleList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(PUZZLE_GET_ALL);

            while (resultSet.next()) {
                Puzzle puzzle = new Puzzle(
                        resultSet.getInt("pid"),
                        resultSet.getBoolean("behavior"),
                        resultSet.getString("question"),
                        resultSet.getString("answer"),
                        null);
                puzzleList.add(puzzle);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new PuzzleDAOException();
        } finally {
            connectionPool.closeConnection(connection);
        }
        return puzzleList;
    }

    public Puzzle getByID(int in_id) throws PuzzleDAOException {
        Puzzle puzzle = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(PUZZLE_GET_BY_ID);
            preparedStatement.setInt(1, in_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            puzzle = new Puzzle(resultSet.getInt("id"),
                                resultSet.getBoolean("behavior"),
                                resultSet.getString("question"),
                                "",
                                null);
            DifficultyLevel difficultyLevel = new DifficultyLevel(
                    resultSet.getInt("did"),
                    resultSet.getString("dname"));
            puzzle.setDifficultyLevel(difficultyLevel);

        } catch (SQLException e) {
            log.error(e);
            throw new PuzzleDAOException();
        }
        finally {
            connectionPool.closeConnection(connection);
        }
        return puzzle;
    }

    public boolean insert(Puzzle puzzle) {
        boolean result = false;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(PUZZLE_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBoolean(1, puzzle.isBehavior());
            preparedStatement.setString(2, puzzle.getQuestion());
            preparedStatement.setInt(3, puzzle.getDifficultyLevel().getId());
            if (preparedStatement.executeUpdate() == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    puzzle.setId(generatedKeys.getInt(1));
                }
                result = true;
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return result;
    }

    public void update(Puzzle puzzle) throws PuzzleDAOException {
        //int result = 0;
        Connection connection = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(PUZZLE_UPDATE);
            preparedStatement.setBoolean(1, puzzle.isBehavior());
            preparedStatement.setString(2, puzzle.getQuestion());
            preparedStatement.setInt(3, puzzle.getDifficultyLevel().getId());
            preparedStatement.setInt(4, puzzle.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            throw new PuzzleDAOException();
        }
        connectionPool.closeConnection(connection);
    }

    public void delete(Puzzle puzzle) throws PuzzleDAOException {
        Connection connection = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(PUZZLE_DELETE);
            preparedStatement.setInt(1, puzzle.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            throw new PuzzleDAOException();
        }
        finally {
            connectionPool.closeConnection(connection);
        }
    }
}
