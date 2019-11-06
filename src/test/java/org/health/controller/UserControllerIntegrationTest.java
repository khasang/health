package org.health.controller;

import static org.junit.Assert.*;

import org.health.dto.*;
import org.health.entity.*;
import org.junit.*;
import org.springframework.http.*;
import org.springframework.web.client.*;

public class UserControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/user";
    private static final String ADD = "/add";
    private static final String DELETE = "/delete";
    private static final String UPDATE = "/update";
    private static final String ALL = "/all";
    private static final String GET = "/get";

    @Test
    public void testAddAndGet() {
        User user = createUser();

        RestTemplate template = new RestTemplate();
        ResponseEntity<UserDto> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                UserDto.class,
                user.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        UserDto receivedUser = responseEntity.getBody();
        assertNotNull(receivedUser);
    }

    private User createUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        User user = new User();
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setPatronymic("Ivanovich");

        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        RestTemplate template = new RestTemplate();

        User createdUser = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                User.class
        ).getBody();

        assertNotNull(createdUser);
        assertEquals(user.getFirstName(), createdUser.getFirstName());
        assertEquals(user.getLastName(), createdUser.getLastName());
        assertEquals(user.getPatronymic(), createdUser.getPatronymic());

        return createdUser;
    }
}
