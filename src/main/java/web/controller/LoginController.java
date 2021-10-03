package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

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
}
