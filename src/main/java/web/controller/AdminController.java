package web.controller;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService.RoleService;
import web.service.UserService.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    String referensePassword;

    private final UserService service;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService service, RoleService roleService) {
        this.service = service;
        this.roleService = roleService;
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
        model.addAttribute("user", service.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user) {
        service.updateUser(user);
        return "redirect:/admin/adminpage";
    }

    @GetMapping("/adduser")
    public String addUser(@ModelAttribute("user") User user, ModelMap model){
        Set<Role> tmp = new HashSet<>();
        user.setRoles(roleService.getAllRoles());
        model.addAttribute("user", user);
        model.addAttribute("roles", tmp);
        return "new";
    }

//    @PostMapping("/createuser")
    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public String createNewUser(@ModelAttribute("user") User user, ModelMap model, @ModelAttribute("roles") Set<Role> roles) {
        service.saveUser(user, user.getRoles());
        model.addAttribute("users", service.getAllUsers());
        return "redirect:/admin/adminpage";
    }

}
