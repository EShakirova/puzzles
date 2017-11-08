package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tbl_puzzle", schema = "public", catalog = "puzzles")
public class Puzzle {
    private int id;
    private String question;
    private boolean behavior;
    private Collection<TblAnswer> answers;
    private Difficultylevel difLevel;
    private Collection<StatisticItem> statistics;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "question", nullable = false, length = 200)
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "behavior", nullable = false)
    public boolean isBehavior() {
        return behavior;
    }

    public void setBehavior(boolean behavior) {
        this.behavior = behavior;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Puzzle puzzle = (Puzzle) o;

        if (id != puzzle.id) return false;
        if (behavior != puzzle.behavior) return false;
        if (question != null ? !question.equals(puzzle.question) : puzzle.question != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (behavior ? 1 : 0);
        return result;
    }

    @OneToMany(mappedBy = "puzzle")
    public Collection<TblAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<TblAnswer> answers) {
        this.answers = answers;
    }

    @ManyToOne
    @JoinColumn(name = "id_diflevel", referencedColumnName = "id", nullable = false)
    public Difficultylevel getDifLevel() {
        return difLevel;
    }

    public void setDifLevel(Difficultylevel difLevel) {
        this.difLevel = difLevel;
    }

    @OneToMany(mappedBy = "puzzle")
    public Collection<StatisticItem> getStatistics() {
        return statistics;
    }

    public void setStatistics(Collection<StatisticItem> statistics) {
        this.statistics = statistics;
    }
}
