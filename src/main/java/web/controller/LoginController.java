package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class LoginController {
    private UserService service;

    public LoginController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/login")
    public String getLoginPage(HttpServletRequest hsr, Model model) {
        String user = hsr.getParameter("user");
        String pass = hsr.getParameter("pass");

        System.out.println("hello   "+user+ ",  your password  "+pass);
        model.addAttribute("title", "Форма входа");
        return "/login";
    }

    @PostMapping(value = "/perform-login")
    public String getValidate (@PathVariable("user") String user, @PathVariable("pass") String password) {
        System.out.println(user);
        System.out.println(password);
        User us = (User) service.getUserByUsername(user);
        if(us.getPassword().equals(password)){
            return "/admin";
        } else {
            return "/login";
        }
    }
}
