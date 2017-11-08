package db.dao;

import entity.DifficultyLevel;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static db.DBSettings.DIFFICULTYLEVEL_GET_ALL;

@Component
public class DifficultyLevelDAO {
    public static class DifficultyLevelDAOException extends Exception {
    }
    private static final Logger log = Logger.getLogger(DifficultyLevelDAO.class);

    @Autowired
    private SessionFactory factory;

    public DifficultyLevel getById(int id) throws DifficultyLevelDAOException {
        Session session = factory.openSession();
        DifficultyLevel difficultyLevel = (DifficultyLevel) session.get(DifficultyLevel.class, id);
        if (difficultyLevel == null){
            throw new DifficultyLevelDAOException();
        }
        return difficultyLevel;
    }

    public List<DifficultyLevel> getAll(){
        List<DifficultyLevel> difficultyLevels = new ArrayList<>();
        Session session = factory.openSession();
        Query query = session.createQuery(DIFFICULTYLEVEL_GET_ALL);
        difficultyLevels = query.list();
        return difficultyLevels;
    }
}


