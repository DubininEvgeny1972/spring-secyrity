package web.service;

import org.springframework.stereotype.Component;
import web.dao.UserDaoHibernateImpl;
import web.model.User;

import java.sql.SQLException;
import java.util.List;

@Component
public class UserServiceImpl {
    static final UserDaoHibernateImpl userDaoHiber = new UserDaoHibernateImpl();

    public List<User> starts(){
        userDaoHiber.createUsersTable();
        userDaoHiber.saveUser("Ivan", "Ivanov", (byte) 25);
        userDaoHiber.saveUser("Jon", "Lennon", (byte) 35);
        userDaoHiber.saveUser("Bob", "Dillan", (byte) 52);
        userDaoHiber.saveUser("Kirk", "Duglas", (byte) 48);
        return userDaoHiber.getAllUsers();
    }

    public void createUsersTable() throws SQLException {
        userDaoHiber.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        userDaoHiber.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoHiber.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        userDaoHiber.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDaoHiber.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        userDaoHiber.cleanUsersTable();
    }

}
