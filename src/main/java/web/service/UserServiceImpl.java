package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserDao userDaoHiber;

    public UserServiceImpl(UserDao userDaoHiber) {
        this.userDaoHiber = userDaoHiber;
    }

    @Transactional
    @Override
    public User getUser(Long id){
        return userDaoHiber.getUser(id);
    }

    @Transactional
    @Override
    public void saveUser(String name, String lastName, byte age){
        userDaoHiber.saveUser(name, lastName, age);
    }

    @Transactional
    @Override
    public void removeUserById(long id){
        userDaoHiber.removeUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers(){
        return userDaoHiber.getAllUsers();
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDaoHiber.updateUser(user);
    }
}
