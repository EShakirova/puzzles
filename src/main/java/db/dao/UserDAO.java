package db.dao;

import db.ConnectionManagerPostgresSQL;
import db.IConnectionManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import pojo.User;
import services.backupDB.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static db.DBSettings.*;
import static db.DBSettings.USER_GET_ALL;

@Component
public class UserDAO implements IUserDAO, ICommonDAO<User> {
    public static class UserDAOException extends Exception {
    };
    private static IConnectionManager manager;

    static {
        manager = ConnectionManagerPostgresSQL.getInstance();
    }

    public DBConnectionPool connectionPool = DBConnectionPool.getInstance();
    private static final Logger log = Logger.getLogger(UserDAO.class);
    
    public  List<User> getAll() throws UserDAOException {
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(USER_GET_ALL);
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        "",
                        resultSet.getBoolean("isadmin"));
                userList.add(user);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new UserDAOException();
        }
        finally {
            connectionPool.closeConnection(connection);
        }
        return userList;
    }

    public  User getByID(int in_id) throws UserDAOException {
        User user = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(USER_GET_BY_ID);
            preparedStatement.setInt(1, in_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User( resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("login"),
                    resultSet.getString("email"),
                    "");
        } catch (SQLException e) {
            log.error(e);
            throw new UserDAOException();
        }
        connectionPool.closeConnection(connection);
        return user;
    }

    public boolean insert(User user) {
        return createUser(user);
    }

    public Boolean createUser(User user)  {
        log.info("Start USER_INSERT ");
        boolean result = false;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            log.info("got connection");
            PreparedStatement preparedStatement = /*manager.getConnection().*/
                    connection.prepareStatement(USER_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getEmailaddress());
            preparedStatement.setString(5, user.getPassword());
            if (preparedStatement.executeUpdate()==1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                    log.info(user.getFirstName() + " " +user.getId());
                }
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

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws UserDAOException {
        log.info("Start USER_GET_LOGIN_PASSWORD ");
        User user = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(USER_GET_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        "",
                        resultSet.getString("email"),
                        "",
                        resultSet.getBoolean("is_admin"));
            }
        } catch(SQLException e) {
            log.error(e);
            throw new UserDAOException();
        }
        connectionPool.closeConnection(connection);
        return user;
    }
 /*   public static void insertAll(List<User> userList) {
        try {
            PreparedStatement preparedStatement = manager.getConnection().prepareStatement(
                    "insert into " + "\"user\"" +
                            " (first_name, last_name, login, email)" +
                            "values(?,?,?,?)");
            for (User user : userList) {
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getLogin());
                preparedStatement.setString(4, user.getEmailaddress());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e)

    {
        e.printStackTrace();
    }
    }*/

    public  void update(User user) throws UserDAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(USER_UPDATE);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getEmailaddress());
            preparedStatement.setInt(5, user.getId());
           /* int result =*/ preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            throw new UserDAOException();
        }
        connectionPool.closeConnection(connection);
    }

    public  void delete(User user) throws UserDAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(USER_DELETE);
            preparedStatement.setInt(1, user.getId());
            /*int result =*/ preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            throw new UserDAOException();
        }
    }
}