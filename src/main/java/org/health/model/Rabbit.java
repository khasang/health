package org.health.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
//@Service
//@Controller
//@Configuration
//@Repository
//@Scope("prototype")
public class Rabbit {
    private String name;

    public Rabbit() {
    }

    public Rabbit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
