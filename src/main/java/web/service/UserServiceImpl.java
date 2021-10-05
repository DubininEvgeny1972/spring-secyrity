package web.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.List;


@Service

public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDao userDaoHiber;

    public UserServiceImpl(UserDao userDaoHiber) {
        this.userDaoHiber = userDaoHiber;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        return userDaoHiber.getUserByUsername(username);
    }

    @Transactional
    @Override
    public void saveRole(Role role) {
        userDaoHiber.saveRole(role);
    }

    @Transactional
    @Override
    public User getUserByUsername(String userName){
        return userDaoHiber.getUserByUsername(userName);
    }

    @Transactional
    @Override
    public User getUser(Long id){
        return userDaoHiber.getUser(id);
    }

    @Transactional
    @Override
    public void saveUser(User user){
        userDaoHiber.saveUser(user);
    }

    @Transactional
    @Override
    public void removeUserById(long id){
        userDaoHiber.removeUserById(id);
    }

    @Transactional
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
