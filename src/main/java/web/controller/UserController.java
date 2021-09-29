package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping()
    public String showUsers(ModelMap model){
        model.addAttribute("users", service.getAllUsers());
        return "index";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") long id, ModelMap model){
        service.removeUserById(id);
        model.addAttribute("users", service.getAllUsers());
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

    @PostMapping
    public String createNewUser(@ModelAttribute("user") User user){
        service.saveUser(user);
        return "redirect:/";
    }
}
