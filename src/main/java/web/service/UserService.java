package web.service;

import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    void starts();
    void createUsersTable() throws SQLException;

    void dropUsersTable() throws SQLException, ClassNotFoundException;

    User getUser(Long id);

    void saveUser(String name, String lastName, byte age) throws SQLException;

    void removeUserById(long id) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    void cleanUsersTable() throws SQLException;

}
