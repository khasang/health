package org.health.entity.userdb.extend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.health.entity.Specialty;
import org.health.entity.userdb.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Doctor", description = "Class representing the doctor.")
@Entity
@Table(name = "doctor")
public class Doctor {
    @ApiModelProperty(notes = "Unique identifier", required = true, example = "1", position = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(optional = false)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "doctor_specialty",
            uniqueConstraints = {@UniqueConstraint(columnNames = {"doctor_id", "specialty_id"})},
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private List<Specialty> specialties = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(Doctor doctor) {
        if (doctor != null) {
            this.id = doctor.getId();
            this.user = new User(doctor.user);
            this.specialties = doctor.specialties;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", user=" + user +
                ", specialties=" + specialties +
                '}';
    }
}
