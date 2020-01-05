package org.health.dto;

import org.health.dao.RoleDao;
import org.health.entity.*;
import org.health.entity.userdb.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;
    private RoleDto currantRole;
    private List<RoleDto> listSetCurrantRoles = new ArrayList<>();

    private RoleDao roleDao;

    public UserDto() {
    }

    public UserDto getCloneUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.firstName = user.getFirstName();
        userDto.lastName = user.getLastName();
        userDto.patronymic = user.getPatronymic();
        userDto.login = user.getLogin();

//        Role role = user.getCurrentRole();
//        if(user.getCurrentRole() == null) {
//            role = roleDao.getEntity(1);
//        }
        userDto.currantRole = new RoleDto().getCloneRoleDto(user.getCurrentRole());
        userDto.listSetCurrantRoles = this.getListRolesDto(user);

        return userDto;
    }

    private List<RoleDto> getListRolesDto(User user) {
        List<RoleDto> roleDtos = new ArrayList<>();
        user.getRoles().iterator().forEachRemaining((p) -> roleDtos.add(new RoleDto().getCloneRoleDto(p)));
        return roleDtos;
    }

    public User update(User user) {
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPatronymic(patronymic);
        user.setLogin(login);
//        user.setCurrent_role_id(roleId);

        return user;
    }

    @Override
    public String toString() {
        StringBuilder stringRolesBuilder = new StringBuilder();
        listSetCurrantRoles.iterator().forEachRemaining((s) -> stringRolesBuilder.append("\n\t" + s));

        return "UserDto{" +
                "\n\tid=" + id +
                "\n\tfirstName='" + firstName + '\'' +
                "\n\tlastName='" + lastName + '\'' +
                "\n\tpatronymic='" + patronymic + '\'' +
                "\n\tlogin='" + login + '\'' +
                "\n\tcurrantRole=" + currantRole +
                "\n\tstringRolesBuilder=" + stringRolesBuilder +
                "\n}\n";
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

    public List<RoleDto> getListSetCurrantRoles() {
        return listSetCurrantRoles;
    }

    public void setListSetCurrantRoles(List<RoleDto> listSetCurrantRoles) {
        this.listSetCurrantRoles = listSetCurrantRoles;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}
