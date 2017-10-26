package controller;

import db.dao.UserDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.RegistrationService;
import servlets.LoginServlet;

@Controller
public class RegistrationController {
    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private RegistrationService registrationService;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
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
}
