package controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
    public String login(){
        return "index";
    }

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public String login (@RequestParam String login, @RequestParam String password){
        log.info("Start LoginController login");
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return "redirect:/menu";
        }
        else{
            return "redirect:/auth";
        }
    }

}
