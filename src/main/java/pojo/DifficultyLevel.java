package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_diflevel")
public class DifficultyLevel {
    private int id;
    private String name;

    public DifficultyLevel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @Column(name= "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
