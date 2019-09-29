package org.health.model.impl;

import org.health.model.Animal;
import org.springframework.stereotype.Service;

@Service("croc")
public class Crocodile implements Animal {
    @Override
    public String getInfo() {
        return "Gena";
    }
}
