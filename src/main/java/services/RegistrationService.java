package services;

import db.dao.UserDAO;

public interface RegistrationService {
    Boolean regUser (String firstName, String lastName,
                     String login, String email, String password) throws UserDAO.UserDAOException;

}
