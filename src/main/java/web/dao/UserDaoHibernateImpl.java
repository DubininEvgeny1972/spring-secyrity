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
    public void saveUser(User user, String roleAdmin, String roleUser) {
        em.persist(user);

        Set<Role> roles = new HashSet<>();
        if (roleAdmin != null && roleUser != null){
            roles.add(em.createQuery("SELECT r FROM Role r WHERE r.name=:name", Role.class)
                    .setParameter("name", "ROLE_ADMIN")
                    .getSingleResult());
            roles.add(em.createQuery("SELECT r FROM Role r WHERE r.name=:name", Role.class)
                    .setParameter("name", "ROLE_USER")
                    .getSingleResult());
        } else if (roleAdmin != null)  {
            roles.add(em.createQuery("SELECT r FROM Role r WHERE r.name=:name", Role.class)
                    .setParameter("name", "ROLE_ADMIN")
                    .getSingleResult());
        } else {
            roles.add(em.createQuery("SELECT r FROM Role r WHERE r.name=:name", Role.class)
                    .setParameter("name", "ROLE_USER")
                    .getSingleResult());
        }
        user.setRoles(roles);
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
