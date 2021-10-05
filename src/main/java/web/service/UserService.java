package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import web.model.Role;
import web.model.User;
import java.util.List;

public interface UserService {

    User getUserByUsername(String userName);

    User getUser(Long id);

    boolean saveUser(User user);

    void removeUserById(long id);

    List<User> getAllUsers();

    void updateUser(User user);

    void saveRole(Role role);
}
