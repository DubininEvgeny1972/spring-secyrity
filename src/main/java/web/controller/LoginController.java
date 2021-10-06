package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.service.CustomUserDetailsService;



@Controller
@RequestMapping
public class LoginController {

    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public LoginController(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping(value = "/")
    public String getLoginPage() {
        return "login";
    }
}