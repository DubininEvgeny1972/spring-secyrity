package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.CustomUserDetailsService;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    static Set<Role> setRole = new HashSet<>();
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
        return "redirect:/admin/adminpage";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") long id) {
        setRole = service.getUser(id).getRoles();
        model.addAttribute("user", service.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user) {
        System.out.println("edit user  " + user);
        user.setRoles(setRole);
        service.updateUser(user);
        return "redirect:/admin/adminpage";
    }

    @GetMapping("/adduser")
    public String addUser(@ModelAttribute("user") User user){
        return "new";
    }

    @PostMapping("/createuser")
    public String createNewUser(@ModelAttribute("user") User user, ModelMap model) {
//        setRole.add(new Role("ROLE_USER"));
//        user.setRoles(setRole);
        System.out.println("Save user  " + user);
        service.saveUser(user);
//        user.setRoles(setRole);
        System.out.println("Ok, saving user!  " + user);
//        service.updateUser(user);
        model.addAttribute("users", service.getAllUsers());
        return "redirect:/admin/adminpage";
    }

}
