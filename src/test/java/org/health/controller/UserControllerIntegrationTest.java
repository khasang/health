package org.health.controller;

import static org.junit.Assert.*;

import java.util.*;

import org.health.dto.ResponseUserServiceDto;
import org.health.dto.UserDto;
import org.health.entity.*;
import org.junit.*;
import org.springframework.core.*;
import org.springframework.http.*;
import org.springframework.web.client.*;

public class UserControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/user";
    private static final String ADD = "/add";
    private static final String DELETE = "/delete";
    private static final String UPDATE = "/update";
    private static final String ALL = "/all";
    private static final String GET = "/get";

    private HttpHeaders headers;
    private RestTemplate template;
    private HttpEntity<User> entity;

    @Before
    public void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void test() {
        User user_1 = createUser(
                "Ivan",
                "Ivanov",
                "Ivanovich",
                "Ivan123",
                "qwerty1",
                2);
        User user_2 = createUser(
                "Petr",
                "Petriv",
                "Petrovich",
                "Petr456",
                "qwerty2",
                1);

        ResponseUserServiceDto responseUserServiceDto_1 = testAdd(user_1);
        ResponseUserServiceDto responseUserServiceDto_2 = testAdd(user_2);

        testGetDto(responseUserServiceDto_1.getUserDto().getId());
        testGetDto(responseUserServiceDto_2.getUserDto().getId());

        testGetAll();

        // TODO не проходит тест, т.к. предположение не полные данные получение UserDto
        testUpdate(user_1, "Sidorov");
        testUpdate(user_2, "Vasilev");

        testDelete(user_1);
        testDelete(user_2);
    }

    private void testDelete(User user) {
        entity = new HttpEntity<>(user, headers);
        template = new RestTemplate();

        User remoteUser = template.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                entity,
                User.class,
                user.getId()
        ).getBody();

        assertNotNull(remoteUser);
        assertEquals(user.getId(), remoteUser.getId());
    }

    private void testUpdate(User user, String lastName) {
        user.setLastName(lastName);

        entity = new HttpEntity<>(user, headers);
        template = new RestTemplate();

        User updateUser = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                User.class
        ).getBody();

        assertNotNull(updateUser);
        assertEquals(lastName, updateUser.getLastName());
    }

    private void testGetAll() {
        template = new RestTemplate();
        List<User> users = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                },
                Collections.emptyList()
        ).getBody();

        assertNotNull(users);
        assertTrue(users.size() > 2); // admin and user
    }

    private void testGetDto(long id) {
        template = new RestTemplate();
        ResponseEntity<UserDto> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                UserDto.class,
                id
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        UserDto userDto = responseEntity.getBody();
        assertNotNull(userDto);
        assertEquals(id, userDto.getId());
    }

    private ResponseUserServiceDto testAdd(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        RestTemplate template = new RestTemplate();
        ResponseUserServiceDto responseUserServiceDto = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                ResponseUserServiceDto.class
        ).getBody();

        assertNotNull(responseUserServiceDto);
        assertEquals(user.getFirstName(), responseUserServiceDto.getUserDto().getFirstName());
        assertEquals(user.getLastName(), responseUserServiceDto.getUserDto().getLastName());
        assertEquals(user.getPatronymic(), responseUserServiceDto.getUserDto().getPatronymic());
        assertEquals(user.getLogin(), responseUserServiceDto.getUserDto().getLogin());
        assertTrue(responseUserServiceDto.isRequestSave());

        return responseUserServiceDto;
//        return new ResponseUserServiceDto();
    }

    private User createUser(String firstName, String lastName, String patronymic, String login, String pass, long roleId) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPatronymic(patronymic);
        user.setLogin(login);
        user.setPassword(pass);
        user.setRoleId(roleId);

        return user;
    }
}
