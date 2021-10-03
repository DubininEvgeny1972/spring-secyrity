package web.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan("web")
@PropertySource("classpath:db.properties")
public class AppConfig {
    @Autowired
    UserService userService;

    private Environment env;

    public AppConfig(Environment env) {
        this.env = env;
    }
    @PostConstruct
    public void startProject() {
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");
        User user1 = new User();
        user1.setName("Jon");
        user1.setLastName("Lennon");
        user1.setAge((byte) 35);
        user1.setUserName("111");
        user1.setPassword("111");
        Set<Role> roleFromUser1 = new HashSet<>();
        roleFromUser1.add(role1);
        user1.setRoles(roleFromUser1);
        userService.saveUser(user1);
        User user2 = new User();
        user2.setName("Kirk");
        user2.setLastName("Duglas");
        user2.setAge((byte) 45);
        user2.setUserName("222");
        user2.setPassword("222");
        Set<Role> roleFromUser2 = new HashSet<>();
        roleFromUser1.add(role1);
        roleFromUser2.add(role2);
        user2.setRoles(roleFromUser1);
        userService.saveUser(user2);
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("db.driver"));
        dataSource.setJdbcUrl(env.getProperty("db.url"));
        dataSource.setUser(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean(); // HibernateExceptions, PersistenceExceptions... to DataAccessException
        em.setDataSource(dataSource());
        em.setPackagesToScan("web.model");
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

}
