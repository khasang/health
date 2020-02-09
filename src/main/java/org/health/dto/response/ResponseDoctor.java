package org.health.dto.response;

import org.health.entity.userdb.extend.Doctor;

/**
 * Класс описывающий ответ сервера на получение данных о пользователе
 */
public class ResponseDoctor {
    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;

    public ResponseDoctor(Doctor doctor) {
        this.id = doctor.getId();
        this.firstName = doctor.getUser().getFirstName();
        this.lastName = doctor.getUser().getLastName();
        this.patronymic = doctor.getUser().getPatronymic();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public String toString() {
        return "ResponseDoctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}
