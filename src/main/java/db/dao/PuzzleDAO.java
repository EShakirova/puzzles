package db.dao;

import dto.PuzzleDTO;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import entity.*;

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

    public Puzzle getById(int id) throws PuzzleDAOException {
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

    public List<PuzzleDTO> getAllWithStat(){
        List<PuzzleDTO> puzzleDTOList = new ArrayList<>();
        Session session = factory.openSession();
        Query query = session.createQuery(PUZZLE_GET_ALL_WITH_STAT);
        puzzleDTOList = query.list();
        session.close();
        return puzzleDTOList;
    }
}
