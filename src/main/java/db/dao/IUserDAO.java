package db.dao;

import pojo.User;

public interface IUserDAO {
    User getUserByLoginAndPassword(String login, String password) throws UserDAO.UserDAOException;
    Boolean createUser(User user) throws UserDAO.UserDAOException;
}
