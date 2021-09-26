package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        String id = hsr.getParameter("href");
        System.out.println(id);
//        if(counter == null){
//            counter = "0";
//        }
        service.removeUserById(Integer.parseInt(id));
        model.addAttribute("users", service.getAllUsers());
        return "deleteUser";
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
        return "addUser";
    }
    @GetMapping(value = "/editUser")
    public String editUser(ModelMap model) throws SQLException {
        model.addAttribute("users", service.getAllUsers());
        return "editUser";
    }
}
