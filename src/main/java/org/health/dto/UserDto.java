package org.health.dto;

import org.health.entity.userdb.User;
import org.springframework.stereotype.Component;

@Component
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;
    private RoleDto currantRole;

    public UserDto() {
    }

    public UserDto(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        patronymic = user.getPatronymic();
        login = user.getLogin();

        if (user.getCurrentRole() != null)
            currantRole = new RoleDto(user.getCurrentRole());
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public RoleDto getCurrantRole() {
        return currantRole;
    }

    public void setCurrantRole(RoleDto currantRole) {
        this.currantRole = currantRole;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", login='" + login + '\'' +
                ", currantRole=" + currantRole +
                '}';
    }
}
