package org.health.controller;

import org.health.model.Animal;
import org.health.model.Cat;
import org.health.model.Dog;
import org.health.model.Rabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
    private Cat cat;
    private Rabbit rabbit;
    private Dog dog;
    private Animal animal;

    public AppController(Cat cat, Dog dog) {
        this.cat = cat;
        this.dog = dog;
    }

    // http://localhost:8080/
    @RequestMapping("/hello/{name}")
    public String getHelloPage(@PathVariable("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping("/")
    public String getName(Model model) {
        model.addAttribute("name", cat.getName());
        model.addAttribute("name2", rabbit.getName());
        model.addAttribute("name3", dog.getName());
        model.addAttribute("description", dog.getDescription());
        model.addAttribute("name4", animal.getInfo());
        return "cat";
    }

    @Autowired
    @Value("Bagzzz")
    public void setRabbit(Rabbit rabbit) {
        this.rabbit = rabbit;
    }

    @Autowired
    @Qualifier(value = "croc")
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    // recommend since 2018
//    @Autowired
//    public void setCat(Cat cat) {
//        this.cat = cat;
//    }
}
