package org.health.dto;

import org.health.entity.Role;
import org.health.entity.userdb.User;

import java.util.LinkedList;
import java.util.List;

public class RoleUserDto {
    private RoleDto roleDto;
    private List<UserDto> userDtos = new LinkedList<>();

    public RoleUserDto(Role role, List<User> users) {
        roleDto = new RoleDto(role);
        if (users != null)
            users.forEach(u -> {
                UserDto uDto = new UserDto(u);
                userDtos.add(uDto);
            });

        userDtos.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public List<UserDto> getUserDtos() {
        return userDtos;
    }

    @Override
    public String toString() {
        return "RoleUserDto{" +
                "roleDto=" + roleDto +
                ", userDtos=" + userDtos +
                '}';
    }
}
