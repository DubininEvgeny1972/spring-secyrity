package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @GetMapping()
    public String showUsers(HttpServletRequest hsr, ModelMap model) throws SQLException {
        model.addAttribute("users", service.getAllUsers());
        return "index";
    }

    @GetMapping(value = "/deleteUser")
    public String deleteUser(HttpServletRequest hsr, ModelMap model) throws SQLException {
        String id = hsr.getParameter("id");
        System.out.println("Delete  " + id);
        service.removeUserById(Integer.parseInt(id));
        model.addAttribute("users", service.getAllUsers());
        return "index";
    }

//    @GetMapping(value = "/edit")
//    public String getUser(HttpServletRequest hsr, ModelMap model) {
//        String id = hsr.getParameter("id");
//        System.out.println("Edit  " + id);
//        User tmp = service.getUser(Long.parseLong(id));
//        model.addAttribute("user", tmp);
//        return "edit";
//    }
//    @PostMapping
//    public String editUser(@ModelAttribute("user") User user) {
//        User tmp = service.getUser((user.getId()));
//        tmp.setName(user.getName());
//        tmp.setLastName(user.getLastName());
//        tmp.setAge(user.getAge());
//        return "redirect:/";
//    }

    @GetMapping(value = "/new")
    public String addUser(ModelMap model) throws SQLException {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping
    public String createNewUser(@ModelAttribute("user") User user) throws SQLException {
        service.saveUser(user.getName(), user.getLastName(),user.getAge());
        return "redirect:/";
    }
}
