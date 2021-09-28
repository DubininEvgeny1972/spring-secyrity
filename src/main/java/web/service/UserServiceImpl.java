package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.dao.UserDaoHibernateImpl;
import web.model.User;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDaoHiber;

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
