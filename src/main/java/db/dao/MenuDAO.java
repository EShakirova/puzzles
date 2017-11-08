package db.dao;

import entity.Menu;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static db.DBSettings.MENU_GET_ALL_BY_USER;

@Component
public class MenuDAO {

    public static class MenuDAOException extends Exception {
    }

    private static final Logger log = Logger.getLogger(MenuDAO.class);
    @Autowired
    private SessionFactory factory;

    public List<Menu> getAllByUser(Boolean isAdmin) {
        List<Menu> menuList = new ArrayList<>();
        Session session = factory.openSession();
        Query query = session.createQuery("from Menu where admin = :isAdmin").setBoolean("isAdmin", isAdmin);
        menuList = query.list();
        return menuList;
    }

/*    public List<Menu> getAllByUser(Boolean isAdmin) throws MenuDAOException {
        log.info("Start MENU_GET_ALL_BY_USER ");
        List<Menu> menuList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(MENU_GET_ALL_BY_USER);
            statement.setBoolean(1, isAdmin);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Menu menu = new Menu(
                        resultSet.getString("name"),
                        resultSet.getString("ahref"));
                menuList.add(menu);
            }
        } catch (SQLException e) {
            log.info(e);
        }
        finally {
            connectionPool.closeConnection(connection);
        }
        return menuList;
    }
*/
}
