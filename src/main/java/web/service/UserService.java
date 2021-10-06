package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import web.model.Role;
import web.model.User;
import java.util.List;
import java.util.Set;

public interface UserService {

    User getUserByUsername(String userName);

    User getUser(Long id);

    void saveUser(User user, String roleAdmin, String roleUser);

    void removeUserById(long id);

    List<User> getAllUsers();

    void updateUser(User user);

    void saveRole(Role role);
}
