package entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_menu", schema = "public", catalog = "puzzles")
public class Menu {
    private int id;
    private String itemName;
    private String ahref;
    private Boolean isAdmin;

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
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String name) {
        this.itemName = name;
    }

    @Basic
    @Column(name = "ahref", nullable = true, length = 200)
    public String getAhref() {
        return ahref;
    }

    public void setAhref(String ahref) {
        this.ahref = ahref;
    }

    @Basic
    @Column(name = "is_admin", nullable = true)
    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (id != menu.id) return false;
        if (itemName != null ? !itemName.equals(menu.itemName) : menu.itemName != null) return false;
        if (ahref != null ? !ahref.equals(menu.ahref) : menu.ahref != null) return false;
        if (isAdmin != null ? !isAdmin.equals(menu.isAdmin) : menu.isAdmin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (ahref != null ? ahref.hashCode() : 0);
        result = 31 * result + (isAdmin != null ? isAdmin.hashCode() : 0);
        return result;
    }
}
