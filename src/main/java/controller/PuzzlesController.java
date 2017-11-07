package controller;

import db.dao.PuzzleDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import dto.PuzzleDTO;
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

    @RequestMapping(value = "puzzlesForAdmin", method = RequestMethod.GET)
    public ModelAndView getPuzzlesList(){
        List<PuzzleDTO> puzzleList = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView("puzzlesForAdmin");
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

    @RequestMapping(value = "puzzlesForUser", method = RequestMethod.GET)
    public ModelAndView getPuzzlesListWithoutStatistic(/*@RequestParam("userID") Integer userID*/){
        List<PuzzleDTO> puzzleList = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView("puzzlesForUser");
        try {
            int userID=1;
            puzzleList.addAll(puzzleDAO.getAllWithStatByUser(userID));
            modelAndView.addObject("puzzles", puzzleList);
        } catch (PuzzleDAO.PuzzleDAOException e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Puzzle list is empty");
            log.info(e);
        }
        return modelAndView;
    }
}
