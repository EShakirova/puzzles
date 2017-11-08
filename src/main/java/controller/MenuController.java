package controller;

import db.dao.MenuDAO;
import entity.Menu;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.AuthorizationService;
import servlets.LoginServlet;

import java.util.ArrayList;
import java.util.List;

@Controller
//@SessionAttributes("isAdmin")
public class MenuController {

    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private MenuDAO menuDAO;
    private AuthorizationService as;

    @Autowired
    public void setAs(AuthorizationService as) {
        this.as = as;
    }

    @Autowired
    public void setMenuDAO(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    @RequestMapping(value = "menu", method = RequestMethod.GET)
    public ModelAndView showMenu(/*@ModelAttribute("isAdmin") Boolean isAdmin*/){
        List<Menu> menuList = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView("menu");
        menuList.addAll(menuDAO.getAllByUser(/*isAdmin*/ as.getIsFullAccsessFromSecurityContext()));
        modelAndView.addObject("menu", menuList);
        return modelAndView;
    }
}
