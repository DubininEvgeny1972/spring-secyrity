package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.dao.UserDaoHibernateImpl;
import web.model.User;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserDao userDaoHiber;

    public UserServiceImpl(UserDao userDaoHiber) {
        this.userDaoHiber = userDaoHiber;
    }

    @Transactional
    public User getUser(Long id){
        return userDaoHiber.getUser(id);
    }
    @Transactional
    public void saveUser(String name, String lastName, byte age){
        userDaoHiber.saveUser(name, lastName, age);
    }
    @Transactional
    public void removeUserById(long id){
        userDaoHiber.removeUserById(id);
    }
    @Transactional
    public List<User> getAllUsers(){
        return userDaoHiber.getAllUsers();
    }
    @Transactional
    public void updateUser(User user) {
        userDaoHiber.updateUser(user);
    }
}
