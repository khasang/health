package org.health.controller;

import org.health.model.SQLRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ImportResource(value = "classpath:ioc.xml")
public class AppController {
    // WRONG!!!
    // private Cat cat = new Cat();
    private SQLRequest sqlRequest;

    // http://localhost:8080/
    @RequestMapping("/hello/{name}")
    public String getHelloPage(@PathVariable("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping("/create")
    public String createTable(Model model) {
        model.addAttribute("status", sqlRequest.getTableCreationStatus());
        return "table";
    }

    @RequestMapping(value = "/dogs/get/count/{name}/description/{description}")
    public String getDogsCount(@PathVariable("name") String name, @PathVariable("description") String description,
                               Model model) {
        model.addAttribute("info", sqlRequest.getInfo(name, description));
        return "dogs";
    }

    @RequestMapping("/")
    public String getName() {
        return "entry";
    }

    @Autowired
    public void setSqlRequest(SQLRequest sqlRequest) {
        this.sqlRequest = sqlRequest;
    }


    // recommend since 2018
//    @Autowired
//    public void setCat(Cat cat) {
//        this.cat = cat;
//    }
}
