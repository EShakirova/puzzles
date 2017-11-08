package dto;

import entity.Answer;
import entity.DifficultyLevel;
import entity.Puzzle;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PuzzleDTO extends Puzzle {

    private short unsuccessfulAttemptsCount;
    private long elapsedTime;
    private boolean isSolved;
    private int statisticCount;

    public PuzzleDTO(int id,
                     boolean behavior,
                     String question,
                     DifficultyLevel difficultyLevel,
                     Set<Answer> answerSet,
                     short unsuccessfulAttemptsCount,
                     long elapsedTime,
                     boolean isSolved) {
        super(id, behavior, question, difficultyLevel, answerSet);
        this.unsuccessfulAttemptsCount = unsuccessfulAttemptsCount;
        this.elapsedTime = elapsedTime;
        this.isSolved = isSolved;
    }

    public PuzzleDTO(int id,
                     boolean behavior,
                     String question,
                     DifficultyLevel difficultyLevel,
                     Set<Answer> answerSet,
                     int statisticCount) {
        super(id, behavior, question, difficultyLevel, answerSet);
        this.statisticCount = statisticCount;
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

    public int getStatisticCount() {
        return statisticCount;
    }

    public void setStatisticCount(int statisticCount) {
        this.statisticCount = statisticCount;
    }
}
