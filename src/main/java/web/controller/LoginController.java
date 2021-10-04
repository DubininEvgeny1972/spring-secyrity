package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

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

    
    
//    @GetMapping("admin")
//    public String showUsers(ModelMap model){
//        model.addAttribute("users", service.getAllUsers());
//        return "admin/admin";
//    }
////
////    @GetMapping("/{id}/deleteUser")
////    public String deleteUser(@PathVariable("id") long id){
////        service.removeUserById(id);
////        return "redirect:/admin";
////    }
//    @GetMapping("/admin/{id}/edit")
//    public String edit(ModelMap model, @PathVariable("id") long id) {
//        model.addAttribute("user", service.getUser(id));
//        return "admin/edit";
//    }
//
//    @PatchMapping("/admin/{id}")
//    public String editUser(@ModelAttribute("user") User user) {
//        service.updateUser(user);
//        return "admin/admin";
//    }
//
//    @GetMapping("/admin/new")
//    public String addUser(@ModelAttribute("user") User user){
//        return "admin/new";
//    }
//
//    @PostMapping
//    public String createNewUser(@ModelAttribute("user") User user){
//        service.saveUser(user);
//        return "admin/admin";
//    }
}
