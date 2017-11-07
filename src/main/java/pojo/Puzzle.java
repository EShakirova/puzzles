package pojo;

import classesFromXSD.ObjectFactory;
import db.dao.StatisticItemDAO;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="tbl_puzzle")
public class Puzzle {
    private int id;
    private boolean behavior;
    private String question;
    private DifficultyLevel difficultyLevel;
    private Set<Answer> answerSet;

    public Puzzle(int id, boolean behavior, String question, DifficultyLevel difficultyLevel, Set<Answer> answerSet) {
        this.id = id;
        this.behavior = behavior;
        this.question = question;
        this.difficultyLevel = difficultyLevel;
        this.answerSet = answerSet;
    }

@Id
@Column(name = "id")
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

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id_puzzle")
    public Set<Answer> getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(Set<Answer> answerSet) {
        this.answerSet = answerSet;
    }

    public static classesFromXSD.Puzzle getBean(Puzzle puzzle){
        classesFromXSD.Puzzle puzzleBean = null;
        try {
            ObjectFactory objectFactory = new ObjectFactory();
            puzzleBean = objectFactory.createPuzzle();
            puzzleBean.setId(puzzle.getId());
            puzzleBean.setBehavior(puzzle.isBehavior());
            puzzleBean.setAnswer(null);
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
               null, null
                );
            Set<classesFromXSD.StatisticItem> statisticItemSet = new HashSet<>();
            statisticItemSet.addAll((Collection)puzzle.getPuzzleStatistic());
            Set<StatisticItem> statisticItemPOJOSet = new HashSet<>();
            for (classesFromXSD.StatisticItem si : statisticItemSet){
                StatisticItem statisticItem = StatisticItem.getPOJO(si, puzzlePOJO);
                statisticItemPOJOSet.add(statisticItem);
                //puzzlePOJO.statisticItemSet.add(statisticItem);
            }
            //puzzlePOJO.statisticItemSet.add(statisticItem)
            //puzzlePOJO.setStatisticItemSet(statisticItemPOJOSet);
        return puzzlePOJO;
    }
}

