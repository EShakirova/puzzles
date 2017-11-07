package pojo;

import classesFromXSD.ObjectFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user")
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String emailaddress;
    private String password;
    private boolean isAdmin;

    public User(int id, String firstName, String lastName, String login,
                    String emailaddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.emailaddress = emailaddress;
        this.id = id;
        this.password = password;
    }

    public User(int id, String firstName, String lastName, String login,
                        String emailaddress, String password, boolean isAdmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.emailaddress = emailaddress;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public static classesFromXSD.User getBean(User user){
        ObjectFactory objectFactory = new ObjectFactory();
        classesFromXSD.User userBean  = objectFactory.createUser();
        userBean.setId(user.getId());
        userBean.setFirstName(user.getFirstName());
        userBean.setLastName(user.getLastName());
        userBean.setLogin(user.getLogin());
        userBean.setEmailAddress(user.getEmailaddress());
        return userBean;
    }

    public static User getPOJO(classesFromXSD.User user){
        User userPOJO = new User(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getLogin(),
                user.getEmailAddress(),
                "");
        return userPOJO;
    }
}
