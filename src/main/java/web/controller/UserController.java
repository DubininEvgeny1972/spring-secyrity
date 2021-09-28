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

    @GetMapping()
    public String showUsers(ModelMap model){
        model.addAttribute("users", service.getAllUsers());
        return "index";
    }

    @GetMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("id") long id, ModelMap model){
        service.removeUserById(id);
        model.addAttribute("users", service.getAllUsers());
        return "redirect:/";
    }

    @GetMapping(value = "/edit")
    public String getUser(@RequestParam("id") long id, ModelMap model){
        model.addAttribute("user", service.getUser(id));
        return "edit";
    }

    @PatchMapping
    public String editUser(@ModelAttribute("user") User user) {
        service.updateUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/new")
    public String addUser(ModelMap model){
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping
    public String createNewUser(@ModelAttribute("user") User user){
        service.saveUser(user.getName(), user.getLastName(),user.getAge());
        return "redirect:/";
    }
}
