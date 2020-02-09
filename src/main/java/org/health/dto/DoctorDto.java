package org.health.dto;

import org.health.entity.userdb.extend.Doctor;

public class DoctorDto {
    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;

    public DoctorDto(Doctor doctor) {
        id = doctor.getId();
        firstName = doctor.getUser().getFirstName();
        lastName = doctor.getUser().getLastName();
        patronymic = doctor.getUser().getPatronymic();
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    @Override
    public String toString() {
        return "DoctorDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}
