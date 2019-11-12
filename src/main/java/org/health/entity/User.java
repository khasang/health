package org.health.entity;

import javax.persistence.*;

import io.swagger.annotations.*;

@ApiModel(value = "User", description = "Class representing the user.")
@Entity
@Table(name = "users")
public class User implements Cloneable {
    @ApiModelProperty(notes = "Unique identifier", required = true, example = "1", position = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty(notes = "First name", required = true, example = "Ivan", position = 2)
    @Column(name = "first_name")
    private String firstName;

    @ApiModelProperty(notes = "Last name", required = true, example = "Ivanov", position = 3)
    @Column(name = "last_name")
    private String lastName;

    @ApiModelProperty(notes = "Patronymic", required = true, example = "Ivanovich", position = 4)
    private String patronymic;

    @ApiModelProperty(notes = "Login", required = true, example = "Ivan123", position = 5)
    private String login;

    private String password;

    @Column(name = "role_id")
    private long roleId;

    public User() {
    }

    /**
     * Метод клонирует объект - копирование всех полей
     *
     * @param user
     * @author ZverikRS
     */
    public User(User user) {
        if (user != null) {
            this.id = user.getId();
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.patronymic = user.patronymic;
            this.login = user.login;
            this.password = user.password;
            this.roleId = user.roleId;
        }
    }

    @Override
    protected User clone() throws CloneNotSupportedException {
        User clone = (User) super.clone();
        return new User(clone);
    }

    /**
     * Проверяем наличие заполненных всех полей, если хотябя одно поле пустое, отмена записи
     *
     * @return
     */
    public boolean checkingOnEmptinessFields() {
        try {
            this.checkingOnEmptiness(this.firstName);
            this.checkingOnEmptiness(this.lastName);
            this.checkingOnEmptiness(this.patronymic);
            this.checkingOnEmptiness(this.login);
            this.checkingOnEmptiness(this.password);
            this.checkingOnEmptiness(String.valueOf(this.roleId));

        } catch (RuntimeException e) {
            return false;
        }

        return true;
    }

    private void checkingOnEmptiness(String text) throws RuntimeException {
        if (text.equals("")) {
            throw new RuntimeException();
        }
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
