package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @GetMapping(value = "/")
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

    @GetMapping(value = "/editUser")
    @PostMapping("/edit")
    public String editUser(@RequestParam String name, Model model) throws SQLException {

        System.out.println(name);
//        User tmp = service.getUser(Long.parseLong(id));
//        tmp.setName(name);
        model.addAttribute("users", service.getAllUsers());
        return "index";
    }

    @GetMapping(value = "/addUser")
    public String addUser(HttpServletRequest hsr, ModelMap model) throws SQLException {
        String name = hsr.getParameter("name");
        String lastName = hsr.getParameter("lastName");
        String age = hsr.getParameter("age");
        System.out.println(name + "   " + lastName + "   " + age);
        if(name == null || lastName == null || age == null){
            model.addAttribute("users", service.getAllUsers());
        } else {
            service.saveUser(name, lastName, Byte.parseByte(age));
        }
        model.addAttribute("users", service.getAllUsers());
        return "index";
    }
}
