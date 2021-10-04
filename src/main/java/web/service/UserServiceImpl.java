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
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDao userDaoHiber;

    public UserServiceImpl(UserDao userDaoHiber) {
        this.userDaoHiber = userDaoHiber;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        return userDaoHiber.getUserByUsername(username);
    }

    @Override
    public void saveRole(Role role) {
        userDaoHiber.saveRole(role);
    }

    @Override
    public User getUserByUsername(String userName){
        return userDaoHiber.getUserByUsername(userName);
    }

    @Override
    public User getUser(Long id){
        return userDaoHiber.getUser(id);
    }

    @Override
    public void saveUser(User user){
        userDaoHiber.saveUser(user);
    }

    @Override
    public void removeUserById(long id){
        userDaoHiber.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers(){
        return userDaoHiber.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        userDaoHiber.updateUser(user);
    }


}
