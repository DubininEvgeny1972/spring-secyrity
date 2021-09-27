package web.service;

import org.springframework.stereotype.Component;
import web.dao.UserDaoHibernateImpl;
import web.model.User;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserServiceImpl {
    static final UserDaoHibernateImpl userDaoHiber = new UserDaoHibernateImpl();

    public User getUser(Long id){
        return userDaoHiber.getUser(id);
    }

    public void saveUser(String name, String lastName, byte age){
        userDaoHiber.saveUser(name, lastName, age);
    }

    public void removeUserById(long id){
        userDaoHiber.removeUserById(id);
    }

    public List<User> getAllUsers(){
        return userDaoHiber.getAllUsers();
    }
    public void updateUser(User user) {
        userDaoHiber.updateUser(user);
    }
}
