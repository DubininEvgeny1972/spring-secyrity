package web.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.PersistenceContext;

@Repository
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    public void updateUser(User user) {
        em.merge(user);
    }

    @PostConstruct
    public void startProject() {
        System.out.println("Hello!");
//        Role role1 = new Role("ROLE_VIP");
//        saveRole(role1);
//        Role role2 = new Role("ROLE_USER");
//        saveRole(role2);
//        User user1 = new User();
//        user1.setName("Jon");
//        user1.setLastName("Lennon");
//        user1.setAge((byte) 35);
//        user1.setLogin("111");
//        user1.setPassword("111");
//
//        Set<Role> roleFromUser1 = new HashSet<>();
//        roleFromUser1.add(role1);
//        user1.setRoles(roleFromUser1);
//        System.out.println(user1);
//
//        User user2 = new User();
//        user2.setName("Kirk");
//        user2.setLastName("Duglas");
//        user2.setAge((byte) 45);
//        user2.setLogin("222");
//        user2.setPassword("222");
//        Set<Role> roleFromUser2 = new HashSet<>();
//        roleFromUser1.add(role2);
//        roleFromUser2.add(role1);
//        user2.setRoles(roleFromUser1);
//        System.out.println(user2);
//        userDaoHiber.saveUser(user2);
//        userDaoHiber.saveUser(user1);

    }
    @Override
    public User getUserByUsername(String userName) {
        return em.createQuery("select userByUsername from User userByUsername where userByUsername.login = :usName", User.class)
                .setParameter("usName", userName)
                .getSingleResult();
    }

    @Override
    public User getUser(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        Set<Role> tmp = new HashSet<>();
        Role rol = em.find(Role.class, "ROLE_USER");
        tmp.add(rol);
        user.setRoles(tmp);
        em.persist(user);
    }

    @Override
    public void saveRole(Role role) {
        em.persist(role);
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
