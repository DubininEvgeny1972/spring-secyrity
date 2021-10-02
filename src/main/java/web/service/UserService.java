package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import web.model.User;
import java.util.List;

public interface UserService {

    UserDetails getUserByUsername(String userName);

    User getUser(Long id);

    void saveUser(User user);

    void removeUserById(long id);

    List<User> getAllUsers();

    void updateUser(User user);
}
