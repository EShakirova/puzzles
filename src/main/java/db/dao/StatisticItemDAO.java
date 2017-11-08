package db.dao;

import entity.Puzzle;
import entity.StatisticItem;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        session.close();
        return statisticItem;
    }

    public List<StatisticItem> getAll(){
        List<StatisticItem> statisticItemList = new ArrayList<>();
        Session session = factory.openSession();
        Query query = session.createQuery(STATISTICITEM_GET_ALL);
        statisticItemList = query.list();
        session.close();
        return statisticItemList;
    }

    public List<StatisticItem> getAllByPuzzle(Puzzle puzzle){
        List<StatisticItem> statisticItemList = new ArrayList<>();
        Session session = factory.openSession();
        Query query = session.createQuery(STATISTICITEM_GET_BY_PUZZLE).setInteger("id_puzzle", puzzle.getId());
        statisticItemList = query.list();
        session.close();
        return statisticItemList;
    }

    public List<StatisticItem> getAllByPuzzle(User user){
        List<StatisticItem> statisticItemList = new ArrayList<>();
        Session session = factory.openSession();
        Query query = session.createQuery(STATISTICITEM_GET_BY_USER).setInteger("id_user", user.getId());
        statisticItemList = query.list();
        session.close();
        return statisticItemList;
    }
}
