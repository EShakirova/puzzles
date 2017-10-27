package pojo;

import classesFromXSD.ObjectFactory;
import db.dao.StatisticItemDAO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class Puzzle {
    private int id;
    private boolean behavior;
    private String question;
    private String answer;
    private DifficultyLevel difficultyLevel;
    private final Set<StatisticItem> statisticItemSet;

    public Puzzle(int id, boolean behavior, String question, String answer,
                  DifficultyLevel difficultyLevel) {
        this.id = id;
        this.behavior = behavior;
        this.question = question;
        this.answer = answer;
        this.difficultyLevel = difficultyLevel;
        this.statisticItemSet = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBehavior() {
        return behavior;
    }

    public void setBehavior(boolean behavior) {
        this.behavior = behavior;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Set<StatisticItem> getStatisticItemSet() {
        return statisticItemSet;
    }


    public static classesFromXSD.Puzzle getBean(Puzzle puzzle){
        classesFromXSD.Puzzle puzzleBean = null;
        try {
            ObjectFactory objectFactory = new ObjectFactory();
            puzzleBean = objectFactory.createPuzzle();
            puzzleBean.setId(puzzle.getId());
            puzzleBean.setBehavior(puzzle.isBehavior());
            puzzleBean.setAnswer(puzzle.getAnswer());
            puzzleBean.setQuestion(puzzle.getQuestion());
            puzzleBean.setDifficultyLevel(/*puzzle.getDifficultyLevel()*/(byte)1);

            Set<StatisticItem> statisticItemSet = new HashSet<>();
            StatisticItemDAO statisticItemDAO = new StatisticItemDAO();
            statisticItemSet.addAll(statisticItemDAO.getAllByPuzzle(puzzle));
            for(StatisticItem statisticItem : statisticItemSet){
                puzzleBean.getPuzzleStatistic().add(StatisticItem.getBean(statisticItem));
            }
        } catch (StatisticItemDAO.StatisticItemDAOException e) {
            e.printStackTrace();
        }
        return puzzleBean;
    }

    public static Puzzle getPOJO(classesFromXSD.Puzzle puzzle){
        Puzzle puzzlePOJO = null;
        //try {
            puzzlePOJO = new Puzzle(
                puzzle.getId(),
                puzzle.isBehavior(),
                puzzle.getQuestion(),
                puzzle.getAnswer(),
                   null/*puzzle.getDifficultyLevel()*/
                );
            Set<classesFromXSD.StatisticItem> statisticItemSet = new HashSet<>();
            statisticItemSet.addAll((Collection)puzzle.getPuzzleStatistic());
            //Set<StatisticItem> statisticItemPOJOSet = new HashSet<>();
            for (classesFromXSD.StatisticItem si : statisticItemSet){
                StatisticItem statisticItem = StatisticItem.getPOJO(si, puzzlePOJO);
                //statisticItemPOJOSet.add(statisticItem);
                puzzlePOJO.statisticItemSet.add(statisticItem);
            }
            //puzzlePOJO.statisticItemSet.add(statisticItem)
            //puzzlePOJO.setStatisticItemSet(statisticItemPOJOSet);
        return puzzlePOJO;
    }
}

