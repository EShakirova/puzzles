package entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_statistic", schema = "public", catalog = "puzzles")
public class StatisticItem {
    private int id;
    private int elasspedTime;
    private int attemptsCount;
    private Boolean isSolved;
    private Puzzle puzzle;
    private User user;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "elasspedTime", nullable = false)
    public int getElasspedTime() {
        return elasspedTime;
    }

    public void setElasspedTime(int elasspedTime) {
        this.elasspedTime = elasspedTime;
    }

    @Basic
    @Column(name = "attemptsCount", nullable = false)
    public int getAttemptsCount() {
        return attemptsCount;
    }

    public void setAttemptsCount(int attemptsCount) {
        this.attemptsCount = attemptsCount;
    }

    @Basic
    @Column(name = "is_solved", nullable = true)
    public Boolean getSolved() {
        return isSolved;
    }

    public void setSolved(Boolean solved) {
        isSolved = solved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatisticItem that = (StatisticItem) o;

        if (id != that.id) return false;
        if (elasspedTime != that.elasspedTime) return false;
        if (attemptsCount != that.attemptsCount) return false;
        if (isSolved != null ? !isSolved.equals(that.isSolved) : that.isSolved != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + elasspedTime;
        result = 31 * result + attemptsCount;
        result = 31 * result + (isSolved != null ? isSolved.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_puzzle", referencedColumnName = "id", nullable = false)
    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
