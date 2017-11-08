package services;

import db.dao.UserDAO;
import org.springframework.stereotype.Component;

@Component(value = "RegistrationService")
public class RegistrationServiceImpl implements RegistrationService {
    public  static UserDAO userDAO = new UserDAO() ;

    @Override
    public Boolean regUser (String firstName, String lastName,
                            String login, String email, String password) throws UserDAO.UserDAOException {
        if (login == null || password == null){
            return false;
        }
        return null;
        /*return userDAO.insert(new User(-1,
                                            firstName,
                                            lastName,
                                            login,
                                            email,
                                            PasswordEncoder.encode(password)));*/
    }
}
