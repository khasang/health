package org.health.entity.userdb;

import io.swagger.annotations.ApiModel;
import org.health.entity.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "User", description = "Class representing the user.")
@Entity
@Table(name = "users")
public class User implements Cloneable {
//    @ApiModelProperty(notes = "Unique identifier", required = true, example = "1", position = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @ApiModelProperty(notes = "First name", required = true, example = "Ivan", position = 2)
    @Column(name = "first_name", nullable = false)
    private String firstName;

//    @ApiModelProperty(notes = "Last name", required = true, example = "Ivanov", position = 3)
    @Column(name = "last_name", nullable = false)
    private String lastName;

//    @ApiModelProperty(notes = "Patronymic", required = true, example = "Ivanovich", position = 4)
    @Column(name = "patronymic", nullable = false)
    private String patronymic;

//    @ApiModelProperty(notes = "Login", required = true, example = "Ivan123", position = 5)
    @Column(name = "login", unique = true)
    private String login;

//    @ApiModelProperty(notes = "Password", required = true, example = "tg64dGjf9Trs", position = 6)
    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Role currentRole;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    /**
     * Метод клонирует объект - копирование всех полей
     *
     * @param user клонируемый объект
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
            this.currentRole = user.currentRole;
            this.roles = user.roles;
        }
    }

    @Override
    protected User clone() throws CloneNotSupportedException {
        User clone = (User) super.clone();
        return new User(clone);
    }

    @Override
    public String toString() {
        StringBuilder stringRolesBuilder = new StringBuilder();
        roles.iterator().forEachRemaining((s) -> stringRolesBuilder.append(s + "\n"));


        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + stringRolesBuilder.toString() +
                '}';
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

    public Role getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(Role currentRole) {
        this.currentRole = currentRole;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
