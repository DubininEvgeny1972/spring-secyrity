package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import web.model.User;
import java.util.List;

public interface UserDao {

    User getUser(Long id);

    UserDetails getUserByUsername(String userName);

    void removeUserById(long id);

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(User user);
}
