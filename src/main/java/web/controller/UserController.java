package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.User;
import web.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl service;

//    @GetMapping("/cars")
//    public String showCars(HttpServletRequest hsr, Model model) {
//        String counter = hsr.getParameter("count");
//        if(counter == null){
//            counter = "0";
//        }
//        model.addAttribute("autos", carService.getCar(Integer.parseInt(counter)));
//        return "cars";
//    }

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) throws SQLException {
        service = new UserServiceImpl();
        List<User> messages1 = service.getAllUsers();
        List<String> messages = new ArrayList<>();
        messages.add(messages1.get(0).getName());
        messages.add(messages1.get(1).getName());
        messages.add("Hello!");
        messages.add("I'm Spring MVC application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "index";
    }
}
