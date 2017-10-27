package controller;

import db.dao.MenuDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pojo.Menu;
import pojo.User;
import servlets.LoginServlet;

import java.util.ArrayList;
import java.util.List;

@Controller
//@SessionAttributes("isAdmin")
public class MenuController {

    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private MenuDAO menuDAO;

    @Autowired
    public void setMenuDAO(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    @RequestMapping(value = "menu", method = RequestMethod.GET)
    public ModelAndView showMenu(/*@ModelAttribute("isAdmin") Boolean isAdmin*/){
        List<Menu> menuList = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView("menu");
        try {
            menuList.addAll(menuDAO.getAllByUser(/*isAdmin*/true));
            modelAndView.addObject("menu", menuList);
        } catch (MenuDAO.MenuDAOException e) {
            log.info(e);
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Menu is not available");
        }
        return modelAndView;
    }
}
