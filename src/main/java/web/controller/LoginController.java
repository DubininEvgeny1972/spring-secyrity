package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping
public class LoginController {
    private UserService service;

    public LoginController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

//    @PostConstruct
//    public void startProject() {
//        Role role1 = new Role("ADMIN");
//        Role role2 = new Role("USER");
//        User user1 = new User();
//        user1.setName("Jon");
//        user1.setLastName("Lennon");
//        user1.setAge((byte) 35);
//        user1.setUserName("111");
//        user1.setPassword("111");
//        Set<Role> roleFromUser1 = new HashSet<>();
//        roleFromUser1.add(role1);
//        user1.setRoles(roleFromUser1);
//        service.saveUser(user1);
//        User user2 = new User();
//        user2.setName("Kirk");
//        user2.setLastName("Duglas");
//        user2.setAge((byte) 45);
//        user2.setUserName("222");
//        user2.setPassword("222");
//        Set<Role> roleFromUser2 = new HashSet<>();
//        roleFromUser1.add(role1);
//        roleFromUser2.add(role2);
//        user2.setRoles(roleFromUser1);
//        service.saveUser(user2);
//    }
}
