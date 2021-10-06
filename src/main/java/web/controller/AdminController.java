package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    static Set<Role> setRole = new HashSet<>();
    private UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService service) {
        this.service = service;
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
        user.setRoles(setRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        service.updateUser(user);
        return "redirect:/admin/adminpage";
    }

    @GetMapping("/adduser")
    public String addUser(@ModelAttribute("user") User user){
        return "new";
    }

    @PostMapping("/createuser")
    public String createNewUser(@ModelAttribute("user") User user, ModelMap model, @RequestParam(required=false) String roleAdmin,
                                @RequestParam(required=false) String roleUser) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        service.saveUser(user, roleAdmin, roleUser);
        model.addAttribute("users", service.getAllUsers());
        return "redirect:/admin/adminpage";
    }

}
