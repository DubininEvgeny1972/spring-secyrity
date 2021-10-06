package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.UserService;
import javax.annotation.PostConstruct;

@Component
public class StartProject {
    @Autowired
    private UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void startProject() {
        System.out.println("Hello!");
        service.saveRole(new Role("ROLE_USER"));
        service.saveRole(new Role("ROLE_ADMIN"));

        User user1 = new User();
        user1.setName("Bob");
        user1.setLastName("Dillan");
        user1.setAge((byte) 48);
        user1.setLogin("111");
        user1.setPassword(passwordEncoder.encode("111"));
        service.saveUser(user1, null, "ROLE_USER");

        User user2 = new User();
        user2.setName("Susana");
        user2.setLastName("Marpl");
        user2.setAge((byte) 35);
        user2.setLogin("222");
        user2.setPassword(passwordEncoder.encode("222"));
        service.saveUser(user2, "ROLE_ADMIN", "ROLE_USER");

    }
}
