package entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_answer", schema = "public", catalog = "puzzles")
public class TblAnswer {
    private int id;
    private String answer;
    private Boolean isCorrect;
    private Puzzle puzzle;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "answer", nullable = false, length = -1)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "is_correct", nullable = true)
    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblAnswer tblAnswer = (TblAnswer) o;

        if (id != tblAnswer.id) return false;
        if (answer != null ? !answer.equals(tblAnswer.answer) : tblAnswer.answer != null) return false;
        if (isCorrect != null ? !isCorrect.equals(tblAnswer.isCorrect) : tblAnswer.isCorrect != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (isCorrect != null ? isCorrect.hashCode() : 0);
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
}
