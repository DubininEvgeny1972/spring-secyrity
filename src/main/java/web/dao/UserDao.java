package web.dao;

import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    User getUser(Long id);

    void removeUserById(long id) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    void saveUser(String name, String lastName, byte age);
}
