package services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Component;
import db.dao.UserDAO;
import org.apache.log4j.Logger;
import pojo.User;

@Component(value = "AutorizationService")
public class AuthorizationServiceImpl implements AuthorizationService {

    private static UserDAO userDAO = new UserDAO();
    private static final Logger log = Logger.getLogger(AuthorizationServiceImpl.class);

    @Override
    public boolean getIsFullAccsessFromSecurityContext(){
        org.springframework.security.core.userdetails.User user =
                ((org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return  (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_admin")));
    }

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
