package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;
import web.util.Util;
import javax.persistence.EntityManager;
import java.util.List;

@Component
public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }


    public void updateUser(User user) {
        EntityManager em = Util.getSessionFactory().createEntityManager();
        User tmp = getUser(user.getId());
        em.detach(tmp);
        tmp.setName(user.getName());
        tmp.setLastName(user.getLastName());
        tmp.setAge(user.getAge());
        em.getTransaction().begin();
        em.merge(tmp);
        em.getTransaction().commit();
    }


    @Override
    public User getUser(Long id) {
        EntityManager em = Util.getSessionFactory().createEntityManager();
        User user = em.find(User.class, new Long(id));
        em.detach(user);
        return user;
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        EntityManager em = Util.getSessionFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(new User(name, lastName, age));
        em.getTransaction().commit();
        System.out.println("User с именем – " + name + " добавлен в базу данных");

    }

    @Override
    public void removeUserById(long id) {
        EntityManager em = Util.getSessionFactory().createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, new Long(id));
        em.remove(user);
        em.getTransaction().commit();
    }

    @Override
    public List getAllUsers() {
        EntityManager em = Util.getSessionFactory().createEntityManager();
        em.getTransaction().begin();
        List users = em.createQuery("from User ").getResultList();
        return users;
    }
}
