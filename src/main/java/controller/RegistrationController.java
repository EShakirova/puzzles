package controller;

import db.dao.UserDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pojo.User;
import services.RegistrationService;
import servlets.LoginServlet;

@Controller
public class RegistrationController {
    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private RegistrationService registrationService;
    private UserDAO userDAO;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "registrationForm", method = RequestMethod.GET)
    public ModelAndView showForm(){
        ModelAndView modelAndView = new ModelAndView("registration");
        return modelAndView;
    }

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public ModelAndView addNewUser(@RequestParam("login") String login,
                                   @RequestParam("password") String password,
                                   @RequestParam("email") String email,
                                   @RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName){
        ModelAndView modelAndView = new ModelAndView("registration");
        try {
            if (registrationService.regUser(firstName, lastName, login, email, password)!=null){
               modelAndView.addObject("isAuth", true);
               modelAndView.setViewName("menu");
            }
        } catch (UserDAO.UserDAOException e) {
            log.info(e);
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Registration is failed");
            modelAndView.addObject("isAuth", false);
        }
        return modelAndView;
    }

    @RequestMapping(value = "editUser", method = RequestMethod.POST)
    public ModelAndView getUserByID(@RequestParam("userID") Integer userID){
        ModelAndView modelAndView = new ModelAndView("registration");
        User user = null;
        try {
            user = userDAO.getByID(userID);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("registration");
        } catch (UserDAO.UserDAOException e) {
            log.info(e);
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Editing is failed");
        }
        return modelAndView;
    }

    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public ModelAndView editUser(   @RequestParam("id") Integer id,
                                    @RequestParam("login") String login,
                                    @RequestParam("password") String password,
                                    @RequestParam("email") String email,
                                    @RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName,
                                    @RequestParam("isAdmin") Boolean isAdmin){
        ModelAndView modelAndView = new ModelAndView("registration");

        User user = new User(id, firstName, lastName, login, email, password, isAdmin);
        try {
            userDAO.update(user);
        } catch (UserDAO.UserDAOException e) {
            log.info(e);
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Editing is failed");
        }
        return modelAndView;
    }
}
