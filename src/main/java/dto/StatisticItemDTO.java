package dto;

public class StatisticItemDTO {
    private Integer puzzleID;
    private Integer userID;
    private short unsuccessfulAttemptsCount;
    private long elapsedTime;
    private boolean isSolved;

    public StatisticItemDTO(Integer puzzleID,
                            Integer userID,
                            short unsuccessfulAttemptsCount,
                            long elapsedTime,
                            boolean isSolved) {
        this.puzzleID = puzzleID;
        this.userID = userID;
        this.unsuccessfulAttemptsCount = unsuccessfulAttemptsCount;
        this.elapsedTime = elapsedTime;
        this.isSolved = isSolved;
    }

    public Integer getPuzzleID() {
        return puzzleID;
    }

    public void setPuzzleID(Integer puzzleID) {
        this.puzzleID = puzzleID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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
