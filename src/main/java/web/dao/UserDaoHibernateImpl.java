package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import javax.persistence.PersistenceContext;

@Repository
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    public void updateUser(User user) {
        em.merge(user);
        em.detach(user);
    }

    @Override
    public User getUser(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        em.persist(new User(name, lastName, age));
        em.flush();
    }

    @Override
    public void removeUserById(long id) {
        User user = em.find(User.class, new Long(id));
        em.remove(user);
        em.flush();
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("from User ").getResultList();
    }
}
