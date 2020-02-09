package org.health.dto;

import org.health.entity.Role;
import org.health.entity.RoleTest;
import org.health.entity.UserTest;
import org.health.entity.userdb.User;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class RoleUserDtoTest {
    private RoleUserDto roleUserDto;

    @Before
    public void init() {
        Role role = new RoleTest().createRoleTest(0);
        List<User> users = new UserTest().createUserListTest();

        roleUserDto = new RoleUserDto(role, users);

    }

    @Test
    public void create() {
        System.out.println(roleUserDto);
    }

    @Test
    public void getRoleDto() {
    }

    @Test
    public void setRoleDto() {
    }

    @Test
    public void getUserDtoSet() {
    }

    @Test
    public void setUserDtoSet() {
    }
}