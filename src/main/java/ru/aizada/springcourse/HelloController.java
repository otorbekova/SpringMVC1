package ru.aizada.springcourse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping(value = {"/hello-world"})
    public String sayHello() {
        return "hello_world";
    }
}
