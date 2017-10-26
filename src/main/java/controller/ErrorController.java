package controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import servlets.LoginServlet;

@Controller
public class ErrorController {
    private static final Logger log = Logger.getLogger(LoginServlet.class);

    @RequestMapping(value = "error", method = RequestMethod.GET)
    public void showError(){

    }
}

