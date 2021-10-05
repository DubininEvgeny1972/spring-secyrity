package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.CustomUserDetailsService;
import web.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService service;
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AdminController(UserService service, CustomUserDetailsService customUserDetailsService) {
        this.service = service;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping(value = "/adminpage")
    public String ShowAdminPage(ModelMap model) {
        model.addAttribute("users", service.getAllUsers());
        return "shouallwuser";
    }

    @GetMapping("/{id}/deleteUser")
    public String deleteUser(@PathVariable("id") long id){
        service.removeUserById(id);
        return "showAllUsers";
    }
    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") long id) {
        model.addAttribute("user", service.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user) {
        service.updateUser(user);
        return "showAllUsers";
    }

    @GetMapping("/admin/new")
    public String addUser(@ModelAttribute("user") User user){
        System.out.println(user);
        return "new";
    }

    @PostMapping
    public String createNewUser(@ModelAttribute("user") User user){
        System.out.println(user);
        service.saveUser(user);
        return "admin";
    }

}
