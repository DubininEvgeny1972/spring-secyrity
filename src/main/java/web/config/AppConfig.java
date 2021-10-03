package web.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Objects;
import java.util.Properties;


@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan("web")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public JpaTransactionManager transactionManager() throws PropertyVetoException {
        JpaTransactionManager transactionManager =
                new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory().getObject()));
        transactionManager.setDataSource(dataSource());

        return transactionManager;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean(); // HibernateExceptions, PersistenceExceptions... to DataAccessException
        em.setDataSource(dataSource());
        em.setPackagesToScan("web.model");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }


    private Environment env;

    public AppConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("db.driver"));
        dataSource.setJdbcUrl(env.getProperty("db.url"));
        dataSource.setUser(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto = create-drop", env.getRequiredProperty("hibernate.hbm2ddl.auto = create-drop"));
        dataSource.setProperties(properties);
        return dataSource;
    }


//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(emf);
//        return transactionManager;
//    }

}
