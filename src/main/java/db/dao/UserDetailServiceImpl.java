package db.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger log = Logger.getLogger(UserDAO.class);

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails details = null;
        try {
            details = userDAO.loadUserByName(username);
            /*Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_user"));

            details =  new UserDetailsWithId(user.getId(), user.getLogin(), user.getPassword(),
                        true, true, true, true,
                                authorities);*/
        } catch (UserDAO.UserDAOException e) {
            log.info(e);
        }
        return details;
    }

}
