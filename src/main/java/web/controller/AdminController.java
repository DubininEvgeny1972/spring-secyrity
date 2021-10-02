package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping
public class AdminController {
    private UserService service;

    public AdminController(UserService service) {
        this.service = service;
    }

    @GetMapping("admin")
    public String showUsers(ModelMap model){
        model.addAttribute("users", service.getAllUsers());
        return "admin";
    }

    @GetMapping("/{id}/deleteUser")
    public String deleteUser(@PathVariable("id") long id){
        service.removeUserById(id);
        return "redirect:/";
    }
    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") long id) {
        model.addAttribute("user", service.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user) {
        service.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String addUser(@ModelAttribute("user") User user){
        return "new";
    }

//    @PostMapping
//    public String createNewUser(@ModelAttribute("user") User user){
//        service.saveUser(user);
//        return "redirect:/";
//    }
}
