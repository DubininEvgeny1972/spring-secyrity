package web.service;

import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User getUser(Long id);

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id) throws SQLException;

    List<User> getAllUsers() throws SQLException;
}
