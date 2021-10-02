package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping
public class LoginController {
    private UserService service;

    public LoginController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "/validate";
    }

    @GetMapping(value = "/validate")
    public String getValidate (@PathVariable("name") String nameUser, @PathVariable("pass") String password) {
        System.out.println(nameUser);
        System.out.println(password);
        User user = (User) service.getUserByUsername(nameUser);
        if(user.getPassword().equals(password)){
            return "/admin";
        } else {
            return "/login";
        }
    }
}
