package db.dao;

import dto.PuzzleDTO;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.*;

import java.util.ArrayList;
import java.util.List;

import static db.DBSettings.*;

@Component
public class PuzzleDAO /*implements ICommonDAO<Puzzle> */{

    public static class PuzzleDAOException extends Exception {
    }
    private static final Logger log = Logger.getLogger(PuzzleDAO.class);

    @Autowired
    private SessionFactory factory;

    public void insert(Puzzle puzzle){
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(puzzle);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Puzzle puzzle){
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(puzzle);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Puzzle puzzle){
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(puzzle);
        session.getTransaction().commit();
        session.close();
    }

    public Puzzle getPuzzleById(int id) throws PuzzleDAOException {
        Session session = factory.openSession();
        Puzzle puzzle = (Puzzle) session.get(Puzzle.class, id);
        session.close();
        if (puzzle == null){
            throw new PuzzleDAOException();
        }
        return puzzle;
    }

    public List<Puzzle> getAll(){
        List<Puzzle> puzzleList = new ArrayList<>();
        Session session = factory.openSession();
        Query query = session.createQuery(PUZZLE_GET_ALL);
        puzzleList = query.list();
        session.close();
        return puzzleList;
    }

    public List<PuzzleDTO>  getAllWithStatByUser(Integer userID){
        List<PuzzleDTO> puzzleDTOList = new ArrayList<>();
        Session session = factory.openSession();
        Query query = session.createQuery(PZZLE_GET_ALL_WITH_STAT_BY_USER).setInteger("id_user", userID);
        puzzleDTOList = query.list();
        session.close();
        return puzzleDTOList;
    }

    public List<PuzzleDTO> getAllwithStat(){
        List<PuzzleDTO> puzzleDTOList = new ArrayList<>();
        Session session = factory.openSession();
        Query query = session.createQuery(PUZZLE_GET_ALL_WITH_STAT);
        puzzleDTOList = query.list();
        session.close();
        return puzzleDTOList;
    }

    //пазлы со статистикой для списка пазлов по пользователю(роль "пользователь")
 /*   public List<PuzzleDTO> getAllWithStatByUser(Integer userID) throws PuzzleDAOException {
        log.info("Start PZZLE_GET_ALL_WITH_STAT_BY_USER ");
        List<PuzzleDTO> puzzleList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(PZZLE_GET_ALL_WITH_STAT_BY_USER);
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PuzzleDTO puzzle = new PuzzleDTO(
                        resultSet.getInt("pid"),
                        resultSet.getBoolean("behavior"),
                        resultSet.getString("question"),
                        null,
                        null,
                        resultSet.getShort("attempts_count"),
                        resultSet.getLong("elapsed_time"),
                        resultSet.getBoolean("is_solved"));
                DifficultyLevel difficultyLevel = new DifficultyLevel(
                        resultSet.getInt("did"),
                        resultSet.getString("dname"));
                puzzle.setDifficultyLevel(difficultyLevel);
                puzzleList.add(puzzle);
            }
        } catch (SQLException e) {
            log.info(e);
        }
        finally {
            connectionPool.closeConnection(connection);
        }
        return puzzleList;
    }*/
//список пазлов со статистикой ответов по пользователям для роли "Админ"
     /*   public List<PuzzleDTO> getAllwithStat() throws PuzzleDAOException {
        log.info("Start PUZZLE_GET_ALL ");
        List<PuzzleDTO> puzzleList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(PUZZLE_GET_ALL_WITH_STAT);
            while (resultSet.next()) {
                PuzzleDTO puzzle = new PuzzleDTO(
                        resultSet.getInt("id"),
                        resultSet.getBoolean("behavior"),
                        resultSet.getString("question"),
                        null,
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
    }*/
/*
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
                        null,
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
    }*/
/*
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
                                null,
                                null);
            DifficultyLevel difficultyLevel = new DifficultyLevel(
                    resultSet.getInt("did"),
                    resultSet.getString("dname"));
            puzzle.setDifficultyLevel(difficultyLevel);
            Set<Answer> answers = new HashSet<>();
            Answer answer = new Answer(
                    resultSet.getInt("aid"),
                    resultSet.getString("answer"),
                    resultSet.getBoolean("is_correct"));
            answers.add(answer);
            while (resultSet.next()){
                        answer = new Answer(
                        resultSet.getInt("aid"),
                        resultSet.getString("answer"),
                        resultSet.getBoolean("is_correct"));
                answers.add(answer);
            }
            puzzle.setAnswerSet(answers);
        } catch (SQLException e) {
            log.error(e);
            throw new PuzzleDAOException();
        }
        finally {
            connectionPool.closeConnection(connection);
        }
        return puzzle;
    }*/
}
