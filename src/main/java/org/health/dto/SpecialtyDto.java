package org.health.dto;

import org.health.entity.Specialty;

public class SpecialtyDto {
    private long id;
    private String title;
    private int countDoctor;

    public SpecialtyDto(Specialty s, int countDoctor) {
        id = s.getId();
        title = s.getTitle();
        this.countDoctor = countDoctor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCountDoctor() {
        return countDoctor;
    }

    @Override
    public String toString() {
        return "SpecialtyDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", countDoctor=" + countDoctor +
                '}';
    }
}
