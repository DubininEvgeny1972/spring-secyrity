package web.service;

import web.model.User;
import java.util.List;

public interface UserService {
    User getUser(Long id);

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void updateUser(User user);
}
