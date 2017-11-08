package controller;

import db.dao.PuzzleDAO;
import db.dao.StatisticItemDAO;
import db.dao.UserDetailsWithId;
import dto.StatisticItemDTO;
import entity.Puzzle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import servlets.LoginServlet;

@Controller
public class StatisticController {

    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private StatisticItemDAO statisticItemDAO;
    private PuzzleDAO puzzleDAO;

    @Autowired
    public void setStatisticItemDAO(StatisticItemDAO statisticItemDAO) {
        this.statisticItemDAO = statisticItemDAO;
    }
    @Autowired
    public void setPuzzleDAO(PuzzleDAO puzzleDAO) {
        this.puzzleDAO = puzzleDAO;
    }

    //получение пазла для ответа
    @RequestMapping(value = "statistic", method = RequestMethod.GET)
    private ModelAndView getPuzzleForAnswer(@RequestParam("puzzleID") Integer puzzleID) {
        ModelAndView modelAndView = new ModelAndView("statistic");
        try {
            Puzzle puzzle = puzzleDAO.getById(puzzleID);
            modelAndView.addObject("puzzle", puzzle);
        } catch (PuzzleDAO.PuzzleDAOException e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Puzzle not found");
            log.info(e);
        }
        return modelAndView;
    }

    //сохранение ответа на пазл
    @RequestMapping(value = "statisticSave", method = RequestMethod.POST)
    private ModelAndView setStatisticForPuzzle(@RequestParam("puzzleID") Integer puzzleID,
                                               @RequestParam("elapsedTime") Long elapsedTime,
                                               @RequestParam("attemptsCount") short attemptsCount,
                                               @RequestParam("isSolved") Boolean isSolved){
        ModelAndView modelAndView = new ModelAndView("statistic");
        UserDetailsWithId userDetailsWithId = (UserDetailsWithId) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StatisticItemDTO statisticItemDTO = new StatisticItemDTO(
                puzzleID,
                userDetailsWithId.getId(),
                attemptsCount,
                elapsedTime,
                isSolved);
      //???
      //  statisticItemDAO.insert(statisticItemDTO);

        return modelAndView;
    }


}
