package web.service;

import org.springframework.stereotype.Component;
import web.dao.UserDaoHibernateImpl;
import web.model.User;

import java.sql.SQLException;
import java.util.List;

@Component
public class UserServiceImpl {
    static final UserDaoHibernateImpl userDaoHiber = new UserDaoHibernateImpl();

    public static void setIdd(long idd) {
        userDaoHiber.setIdd(idd);
    }

    public long getIdd(){
        return userDaoHiber.getIdd();
    }


    public void createUsersTable() throws SQLException {
        userDaoHiber.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        userDaoHiber.dropUsersTable();
    }

    public User getUser(Long id){
        return userDaoHiber.getUser(id);
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
