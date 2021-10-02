package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.PersistenceContext;

@Repository
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public UserDetails getUserByUsername(String userName) {
        System.out.println(userName);
        User user = em.createQuery("select userByUsername from User userByUsername where userByUsername.userName = :usName", User.class)
                .setParameter("usName", userName)
                .getSingleResult();
        System.out.println(user);
        System.out.println(user.getName()+"  "+user.getLastName()+"  "+user.getPassword()+"  "+user.getRoles());
        return em.createQuery("select userByUsername from User userByUsername where userByUsername.userName = :usName", User.class)
                .setParameter("usName", userName)
                .getSingleResult();
    }

    @Override
    public User getUser(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void removeUserById(long id) {
        em.remove(em.find(User.class, new Long(id)));
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("from User ").getResultList();
    }
}
