package web.dao;

import web.model.Role;
import web.model.User;
import java.util.List;

public interface UserDao {

    User getUser(Long id);

    User getUserByUsername(String userName);

    void removeUserById(long id);

    List<User> getAllUsers();

    void saveUser(User user, String roleAdmin, String roleUser);

    void updateUser(User user);

    void saveRole(Role role);
}
