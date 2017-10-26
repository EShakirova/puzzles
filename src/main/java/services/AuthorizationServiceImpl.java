package services;

import org.springframework.stereotype.Component;
import db.dao.UserDAO;
import org.apache.log4j.Logger;
import pojo.User;

@Component(value = "AutorizationService")
public class AuthorizationServiceImpl implements AuthorizationService {

    private static UserDAO userDAO = new UserDAO();
    private static final Logger log = Logger.getLogger(AuthorizationServiceImpl.class);

    @Override
    public User auth(String login, String password) {
        User result = null;
        try {
            if (login != null && password != null) {
                result = userDAO.getUserByLoginAndPassword(login, PasswordEncoder.encode(password));
            }
        } catch (UserDAO.UserDAOException e) {
            log.error(e);
            return null;
        }
        return result;
    }
}
