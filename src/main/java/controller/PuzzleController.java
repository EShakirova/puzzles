package controller;

import db.dao.PuzzleDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pojo.Puzzle;
import servlets.LoginServlet;

@Controller
public class PuzzleController {

    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private PuzzleDAO puzzleDAO;

    @Autowired
    public void setPuzzleDAO(PuzzleDAO puzzleDAO) {
        this.puzzleDAO = puzzleDAO;
    }

    @RequestMapping(value = "puzzle", method = RequestMethod.GET)
    public ModelAndView getPuzzle(@RequestParam("puzzleID") Integer puzzleID){
        ModelAndView modelAndView = new ModelAndView("puzzle");
        try {
            Puzzle puzzle = puzzleDAO.getByID(puzzleID);
            modelAndView.addObject("puzzle", puzzle);
        } catch (PuzzleDAO.PuzzleDAOException e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Puzzle not found");
            log.info(e);
        }
        return modelAndView;
    }
}
