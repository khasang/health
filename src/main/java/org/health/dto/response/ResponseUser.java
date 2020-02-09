package org.health.dto.response;

import org.health.entity.userdb.User;

/**
 * Класс описывающий ответ сервера на получение данных о пользователе
 */
public class ResponseUser {
    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;
    private long currentRoleId;

    public ResponseUser(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.patronymic = user.getPatronymic();
        this.login = user.getLogin();
        this.currentRoleId = currentRoleId;
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

    public String getLogin() {
        return login;
    }

    public long getCurrentRoleId() {
        return currentRoleId;
    }

    @Override
    public String toString() {
        return "ResponseUserCreate{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", login='" + login + '\'' +
                ", currentRoleId=" + currentRoleId +
                '}';
    }
}
