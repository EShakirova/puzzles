package db.dao;

import db.ConnectionManagerPostgresSQL;
import db.IConnectionManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import pojo.User;

import java.sql.*;
import java.util.*;

import static db.DBSettings.*;
import static db.DBSettings.USER_GET_ALL;

@Component
public class UserDAO /*implements IUserDAO, ICommonDAO<User>*/ {
    public static class UserDAOException extends Exception {
    };
    private static final Logger log = Logger.getLogger(UserDAO.class);

    @Autowired
    private SessionFactory factory;

    public User getById(int id) throws UserDAOException {
        Session session = factory.openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        if (user == null){
            throw new UserDAOException();
        }
        return user;
    }

    public void delete(User user){
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    public boolean insert(User user){
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return (user.getId() > 0);
    }

    public void update(User user) throws UserDAOException {
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        Session session = factory.openSession();
        Query query = session.createQuery(USER_GET_ALL);
        userList = query.list();
        return userList;
    }

    public User getUserByLoginAndPassword(String login, String password) throws UserDAOException {
        Session session = factory.openSession();
        Criteria userCriteria = session.createCriteria(User.class);
        userCriteria.add(Restrictions.eq("login",login));
        userCriteria.add(Restrictions.eq("password", password));
        User user = (User) userCriteria.uniqueResult();
        session.close();
        if (user == null){
            throw new UserDAOException();
        }
        return user;
    }
//???
    public UserDetailsWithId loadUserByName(String login) throws UserDAOException {
        Session session = factory.openSession();
        Criteria userDetailsCriteria = session.createCriteria(UserDetailsWithId.class);
        userDetailsCriteria.add(Restrictions.eq("login", login));
        UserDetailsWithId userDetailsWithId = (UserDetailsWithId) userDetailsCriteria.uniqueResult();
        /*
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(userDetailsCriteria.getString("role_name")));
*/
        session.close();
        if (userDetailsWithId == null) {
            throw new UserDAOException();
        }
        return userDetailsWithId;
    }

        /*
        public UserDetailsWithId loadUserByName(String login) throws UserDAOException {
        UserDetailsWithId user = null;
        Connection connection = null;
        try {
          //  connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(USER_GET_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(resultSet.getString("role_name")));
            user = new UserDetailsWithId( resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    authorities);

        } catch (SQLException e) {
            log.error(e);
            throw new UserDAOException();
        }
        finally {
           // connectionPool.closeConnection(connection);
        }
        return user;
    }*/

}