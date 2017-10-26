package controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pojo.User;
import services.AuthorizationService;
import servlets.LoginServlet;


@Controller
@SessionAttributes(value = "isAdmin")
public class LoginController {

    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private AuthorizationService as;

    @Autowired
    public void setAs(AuthorizationService as) {
        this.as = as;
    }

    @RequestMapping(value = {"auth", "/"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    @ModelAttribute("isAdmin")
    public ModelAndView login (@RequestParam String login, @RequestParam String password){
        log.info("Start LoginController login");

        User user = as.auth(login, password);
        ModelAndView modelAndView = new ModelAndView();
        if (user!=null){
            modelAndView.addObject("isAdmin", user.isAdmin());
            modelAndView.setViewName("redirect:/menu");
        }
        else{
            modelAndView.setViewName("redirect:/auth");
        }
        return modelAndView;
    }

}
