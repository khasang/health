package org.health.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    // http://localhost:8080/
    @RequestMapping("/hello/{name}")
    public String getHelloPage(@PathVariable("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }
}
