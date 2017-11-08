package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tbl_diflevel", schema = "public", catalog = "puzzles")
public class Difficultylevel {
    private int id;
    private String name;
    private Collection<Puzzle> puzzles;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Difficultylevel that = (Difficultylevel) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "difLevel")
    public Collection<Puzzle> getPuzzles() {
        return puzzles;
    }

    public void setPuzzles(Collection<Puzzle> puzzles) {
        this.puzzles = puzzles;
    }
}
