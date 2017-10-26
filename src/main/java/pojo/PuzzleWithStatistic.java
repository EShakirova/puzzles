package pojo;

import org.springframework.stereotype.Component;

@Component
public class PuzzleWithStatistic extends Puzzle {

    private short unsuccessfulAttemptsCount;
    private long elapsedTime;
    private boolean isSolved;
    private int statisticCount;

    public PuzzleWithStatistic(int id,
                               boolean behavior,
                               String question,
                               String answer,
                               String difficultyLevel,
                               int statisticCount) {
        super(id, behavior, question, answer, difficultyLevel);
        this.statisticCount = statisticCount;
    }

    public int getStatisticCount() {
        return statisticCount;
    }

    public void setStatisticCount(int statisticCount) {
        this.statisticCount = statisticCount;
    }

    public PuzzleWithStatistic(int id,
                               boolean behavior,
                               String question,
                               String answer,
                               String difficultyLevel,
                               short unsuccessfulAttemptsCount,
                               long elapsedTime,
                               boolean isSolved) {
        super(id, behavior, question, answer, difficultyLevel);
        this.unsuccessfulAttemptsCount = unsuccessfulAttemptsCount;
        this.elapsedTime = elapsedTime;
        this.isSolved = isSolved;
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
}
