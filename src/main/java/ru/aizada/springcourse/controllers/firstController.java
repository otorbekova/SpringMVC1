package ru.aizada.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping("/first") //->first/hello
public class firstController {
    @GetMapping("/hello")
    //HttpServletRequest request
    // String name=request.getParameter("name");
    //        String surname=request.getParameter("surname");
    public String helloPage(@RequestParam("name")String name,
                            @RequestParam("surname")String surname,
    Model model){

       // System.out.println("Name "+name+" Surname "+surname);
        model.addAttribute("message","Name "+name+" Surname "+surname);
        return "first/hello"; // simpl -> hello
    }

    @GetMapping("/coodbye")
    public String goodBay(){
        return "first/goodbye";
    }
}
