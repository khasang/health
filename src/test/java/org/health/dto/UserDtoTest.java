package org.health.dto;

import org.health.entity.UserTest;
import org.health.entity.userdb.User;
import org.junit.Before;
import org.junit.Test;

public class UserDtoTest {
    private UserTest userTest;

    @Before
    public void init() {
        userTest = new UserTest();
    }

    @Test
    public void userTest() {
        User user = userTest.createUserTest(0);

        System.out.println(user);
    }
}
