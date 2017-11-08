package controller;

import db.dao.UserDAO;
import entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import servlets.LoginServlet;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {

    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private UserDAO userDAO;
    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ModelAndView getUserList(){
        ModelAndView modelAndView = new ModelAndView("users");
        List<User> userList = new ArrayList<>();
        userList.addAll(userDAO.getAll());
        modelAndView.addObject("users", userList);
        return modelAndView;
    }
}
