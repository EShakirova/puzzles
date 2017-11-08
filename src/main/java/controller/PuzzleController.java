package controller;

import db.dao.DifficultyLevelDAO;
import db.dao.PuzzleDAO;
import entity.DifficultyLevel;
import entity.Puzzle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import servlets.LoginServlet;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PuzzleController {

    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private PuzzleDAO puzzleDAO;
    private DifficultyLevelDAO difficultyLevelDAO;

    @Autowired
    public void setDifficultyLevelDAO(DifficultyLevelDAO difficultyLevelDAO) {
        this.difficultyLevelDAO = difficultyLevelDAO;
    }

    @Autowired
    public void setPuzzleDAO(PuzzleDAO puzzleDAO) {
        this.puzzleDAO = puzzleDAO;
    }

    @RequestMapping(value = "puzzle", method = RequestMethod.GET)
    public ModelAndView getPuzzle(@RequestParam("puzzleID") Integer puzzleID) {
        ModelAndView modelAndView = new ModelAndView("puzzle");
        try {
            Puzzle puzzle = puzzleDAO.getById(puzzleID);
            List<DifficultyLevel> diflevels = new ArrayList<>();
            diflevels.addAll(difficultyLevelDAO.getAll());
            modelAndView.addObject("diflevels", diflevels);
            modelAndView.addObject("puzzle", puzzle);
        } catch (PuzzleDAO.PuzzleDAOException e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Puzzle not found");
            log.info(e);
        }
        return modelAndView;
    }

    @RequestMapping(value = "editPuzzle", method = RequestMethod.POST)
    public ModelAndView editPuzzle(@RequestParam("puzzleID") Integer puzzleID,
                                   @RequestParam("behavior") Boolean behavior,
                                   @RequestParam("dlevel") Integer dlevel,
                                   @RequestParam("question") String question
                                   ) {
        ModelAndView modelAndView = new ModelAndView("puzzle");
        DifficultyLevel difficultyLevel = null;
        Puzzle puzzle = null;
        try {
            difficultyLevel = difficultyLevelDAO.getById(dlevel);
            puzzle = new Puzzle(puzzleID, behavior, question, difficultyLevel, null);
            modelAndView.setViewName("puzzle");
            modelAndView.addObject("puzzle", puzzle);
        } catch (DifficultyLevelDAO.DifficultyLevelDAOException e) {
            log.info(e);
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Puzzle not found");
            log.info(e);
        }
        return modelAndView;
    }

}
