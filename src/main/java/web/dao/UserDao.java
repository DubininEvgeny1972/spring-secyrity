package web.dao;

import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    User getUser(Long id);

    void removeUserById(long id);

    List<User> getAllUsers();

    void saveUser(String name, String lastName, byte age);

    void updateUser(User user);
}
