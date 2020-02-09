package org.health.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class RoleTest {

    @Before
    public void init() {

    }

    @Test
    public void roleTest() {
        Role role = createRoleTest(0);

        System.out.println(role);
    }

    public Role createRoleTest(int i) {
        return createListRolesTest().get(i);
    }

    public List<Role> createListRolesTest() {
        List<Role> roles = new LinkedList<>();
        roles.add(new Role("name_0", "description_0"));
        roles.add(new Role("name_1", "description_1"));
        roles.add(new Role("name_2", "description_2"));
        roles.add(new Role("name_3", "description_3"));

        return roles;
    }
}
