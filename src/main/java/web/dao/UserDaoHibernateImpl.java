package web.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.stereotype.Component;
import web.model.User;
import web.util.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class UserDaoHibernateImpl implements UserDao {

    private static long idd = 1;
    public UserDaoHibernateImpl() {

    }

    public static void setIdd(long idd) {
        UserDaoHibernateImpl.idd = idd;
    }

    public long getIdd(){
        return idd;
    }

    @Override
    public void createUsersTable() {
    }

    @Override
    public User getUser(Long id) {
        EntityManager em = Util.getSessionFactory().createEntityManager();
        User user = em.find(User.class, new Long(id));
        em.detach(user);
        return user;
    }

    @Override
    public void dropUsersTable() {
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

    @Override
    public void cleanUsersTable() {
    }
}
