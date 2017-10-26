package pojo;

import org.springframework.stereotype.Component;

@Component
public class Menu {
    private String itemName;
    private String ahref;

    public Menu(String itemName, String ahref) {
        this.itemName = itemName;
        this.ahref = ahref;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAhref() {
        return ahref;
    }

    public void setAhref(String ahref) {
        this.ahref = ahref;
    }
}
