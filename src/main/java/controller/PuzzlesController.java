package controller;

import db.dao.PuzzleDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pojo.Puzzle;
import pojo.PuzzleWithStatistic;
import servlets.LoginServlet;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PuzzlesController {

    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private PuzzleDAO puzzleDAO;
    @Autowired
    public void setPuzzleDAO(PuzzleDAO puzzleDAO) {
        this.puzzleDAO = puzzleDAO;
    }

    @RequestMapping(value = "puzzles", method = RequestMethod.GET)
    public ModelAndView getPuzzlesList(){
        List<PuzzleWithStatistic> puzzleList = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView("puzzles");
        try {
            puzzleList.addAll(puzzleDAO.getAllwithStat());
            modelAndView.addObject("puzzles", puzzleList);
        } catch (PuzzleDAO.PuzzleDAOException e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Puzzle list is empty");
            log.info(e);
        }
        return modelAndView;
    }
}
