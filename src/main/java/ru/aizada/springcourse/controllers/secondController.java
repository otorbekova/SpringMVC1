package ru.aizada.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.annotation.MultipartConfig;
import javax.xml.ws.Action;

@Controller
public class secondController {

    @GetMapping ("/exit")
    public String exist(@RequestParam("a") int a,
                        @RequestParam("b")int b,
                        @RequestParam("action") String w,
                        Model model){
        double ff;
        switch (w){
            case "plus":

                ff=a+b;
              ///  model.addAttribute("its",f);
                break;
            case "minus":
               // double ;
                ff=a-b;
                break;
            default:
                ff=0;
                break;
        }
        model.addAttribute("its",ff);
        return "second/exit";
    }
}
