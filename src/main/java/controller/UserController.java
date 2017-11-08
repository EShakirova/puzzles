package controller;

import db.dao.UserDAO;
import entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import servlets.LoginServlet;

@Controller
public class UserController {
    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private UserDAO userDAO;
    private User user;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ModelAndView getUser(@RequestParam ("userID") Integer userID){
        ModelAndView modelAndView = new ModelAndView("user");
        try {
            User user = userDAO.getById(userID);
            modelAndView.addObject("user", user);
        } catch (UserDAO.UserDAOException e) {
            log.info(e);
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "User not found");
        }
        return modelAndView;
    }
}
