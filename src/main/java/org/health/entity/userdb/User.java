package org.health.entity.userdb;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.health.entity.Role;

import javax.persistence.*;
import java.util.*;

@ApiModel(value = "User", description = "Class representing the user.")
@Entity
@Table(name = "users")
public class User implements Cloneable {
    @ApiModelProperty(notes = "Unique identifier", required = true, example = "1", position = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ApiModelProperty(notes = "First name", required = true, example = "Ivan", position = 2)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @ApiModelProperty(notes = "Last name", required = true, example = "Ivanov", position = 3)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ApiModelProperty(notes = "Patronymic", required = true, example = "Ivanovich", position = 4)
    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @ApiModelProperty(notes = "Login", required = true, example = "Ivan123", position = 5)
    @Column(name = "login", unique = true)
    private String login;

    @ApiModelProperty(notes = "Password", required = true, example = "tg64dGjf9Trs", position = 6)
    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Role currentRole;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(name = "users_roles",
            uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "roleId"})},
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roles = new LinkedList<>();

    public User() {
    }

    public User(String firstName, String lastName, String patronymic, String login, String password) {
        super();

        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.login = login;
        this.password = password;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }

    @Override
    public String toString() {
        StringBuilder stringRolesBuilder = new StringBuilder();
        roles.iterator().forEachRemaining((s) -> stringRolesBuilder.append(s + "\n"));

        return "User{" +
                "\nid=" + id +
                ",\nfirstName='" + firstName + '\'' +
                ",\nlastName='" + lastName + '\'' +
                ",\npatronymic='" + patronymic + '\'' +
                ",\nlogin='" + login + '\'' +
                ",\npassword='" + password + '\'' +
                ",\ncurrentRole='" + currentRole + '\'' +
                ",\nroles=" + stringRolesBuilder.toString() +
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
        List<Role> returnRoles = new LinkedList<>();
        roles.iterator().forEachRemaining(r -> {
            returnRoles.add(new Role(r.getId(), r.getName(), r.getDescription()));
        });

        return returnRoles;
//        return roles;
    }

    public void setRoles(List<Role> roles) {
        List<Role> returnRoles = new LinkedList<>();
        roles.iterator().forEachRemaining(r -> {
            returnRoles.add(new Role(r.getId(), r.getName(), r.getDescription()));
        });

        this.roles = returnRoles;
//        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
