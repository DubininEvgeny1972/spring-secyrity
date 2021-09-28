package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class UserDaoHibernateImpl implements UserDao {
    @Autowired
    EntityManagerFactory entityManagerFactory;

    public UserDaoHibernateImpl() {
    }


    public void updateUser(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
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
        EntityManager em = entityManagerFactory.createEntityManager();
        User user = em.find(User.class, new Long(id));
        em.detach(user);
        return user;
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(new User(name, lastName, age));
        em.getTransaction().commit();
        System.out.println("User с именем – " + name + " добавлен в базу данных");

    }

    @Override
    public void removeUserById(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, new Long(id));
        em.remove(user);
        em.getTransaction().commit();
    }

    @Override
    public List getAllUsers() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List users = em.createQuery("from User ").getResultList();
        return users;
    }
}
