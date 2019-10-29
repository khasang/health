package org.health.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CarDto {
    private long id;
    private String model;
    private LocalDate year;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }
}
