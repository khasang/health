package org.health.entity;

import org.health.entity.userdb.User;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class UserTest {

    @Before
    public void init() {

    }

    @Test
    public void userTest() {
        User user = createUserTest(3);
        System.out.println(user);
    }

    public User createUserTest(int i) {
        return createUserListTest().get(i);
    }

    public List<User> createUserListTest() {
        List<User> users = new LinkedList<>();
        users.add(new User("firstName_0", "lastName_0", "patronymic_0", "login_0", "password_0"));
        users.add(new User("firstName_1", "lastName_1", "patronymic_1", "login_1", "password_1"));
        users.add(new User("firstName_2", "lastName_2", "patronymic_2", "login_2", "password_2"));
        users.add(new User("firstName_3", "lastName_3", "patronymic_3", "login_3", "password_3"));

        return users;
    }
}
