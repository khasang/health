package org.health.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.health.entity.userdb.extend.Doctor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Specialty", description = "Class representing the specialty.")
@Entity
@Table(name = "specialty")
public class Specialty {
    @ApiModelProperty(notes = "Unique identifier", required = true, example = "1", position = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ApiModelProperty(notes = "title", required = true, example = "Gastroenterologist", position = 2)
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "doctor_specialty",
            uniqueConstraints = {@UniqueConstraint(columnNames = {"doctor_id", "specialty_id"})},
            joinColumns = @JoinColumn(name = "specialty_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private List<Doctor> doctors = new ArrayList<>();

    public Specialty() {
    }

    public Specialty(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Specialty(String title) {
        this.title = title;
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

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", doctors=" + doctors +
                '}';
    }
}
