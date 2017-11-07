package pojo;

import classesFromXSD.ObjectFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_statistic")
public class StatisticItem {
    private int id;
    private Puzzle puzzle;
    private short unsuccessfulAttemptsCount;
    private long elapsedTime;
    private boolean isSolved;
    private User user;

    public StatisticItem(int id, Puzzle puzzle, short unsuccessfulAttemptsCount,
                            long elapsedTime, boolean isSolved, User user) {
        this.id = id;
        this.puzzle = puzzle;
        this.unsuccessfulAttemptsCount = unsuccessfulAttemptsCount;
        this.elapsedTime = elapsedTime;
        this.isSolved = isSolved;
        this.user = user;
    }
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public short getUnsuccessfulAttemptsCount() {
        return unsuccessfulAttemptsCount;
    }

    public void setUnsuccessfulAttemptsCount(short unsuccessfulAttemptsCount) {
        this.unsuccessfulAttemptsCount = unsuccessfulAttemptsCount;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static classesFromXSD.StatisticItem getBean(StatisticItem statisticItem){
        ObjectFactory objectFactory = new ObjectFactory();
        classesFromXSD.StatisticItem statisticItembean = objectFactory.createStatisticItem();
        statisticItembean.setId(statisticItem.getId());
        statisticItembean.setElapsedTime(statisticItem.getElapsedTime());
        statisticItembean.setUnsuccessfulAttemptsCount(statisticItem.getUnsuccessfulAttemptsCount());
        statisticItembean.setIsSolved(statisticItem.isSolved());
        statisticItembean.setPuzzle(null);
        /*com.company.ClassesFromXSD.Puzzle puzzle = Puzzle.getBean(statisticItem.getPuzzle());
        statisticItembean.setPuzzle(puzzle);*/
        classesFromXSD.User user = User.getBean(statisticItem.getUser());
        statisticItembean.setUser(user);
        return statisticItembean;
    }

    public static StatisticItem getPOJO(classesFromXSD.StatisticItem statisticItem,
                                        Puzzle puzzle){
        StatisticItem statisticItemPOJO = new StatisticItem(
                statisticItem.getId(),
                puzzle,
                statisticItem.getUnsuccessfulAttemptsCount(),
                statisticItem.getElapsedTime(),
                statisticItem.isIsSolved(),
                null);
        User user = User.getPOJO(statisticItem.getUser());
        statisticItemPOJO.setUser(user);
        return statisticItemPOJO;
    }
}
