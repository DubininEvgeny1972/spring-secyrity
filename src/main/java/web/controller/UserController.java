package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

//    @PostMapping("/admin")
//    public String fingUserById(ModelMap model, @PathVariable("name") String name, @PathVariable("name") String password) {
//        model.addAttribute("user", service.getUserByUsername(name));
//        return "showuser";
//    }
}
