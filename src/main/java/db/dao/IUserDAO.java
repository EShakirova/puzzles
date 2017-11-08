package db.dao;

import entity.User;

public interface IUserDAO {
    User getUserByLoginAndPassword(String login, String password) throws UserDAO.UserDAOException;
    Boolean createUser(User user) throws UserDAO.UserDAOException;
}
