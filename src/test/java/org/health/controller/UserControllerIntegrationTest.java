package org.health.controller;

import static org.junit.Assert.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.health.dto.ResponseUserServiceDto;
import org.health.dto.UserDto;
import org.health.entity.*;
import org.health.model.ResponseServiceUser;
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

        testGetAll(responseUserServiceDto_1, responseUserServiceDto_2);

        testUpdate(responseUserServiceDto_1.getUserDto(), "Sidorov");
        testUpdate(responseUserServiceDto_2.getUserDto(), "Vasilev");

        testDelete(responseUserServiceDto_1.getUserDto());
        testDelete(responseUserServiceDto_2.getUserDto());
    }

    private void testDelete(UserDto userDto) {
        HttpEntity<UserDto> entity = new HttpEntity<>(userDto, headers);
        ResponseServiceUser remoteResponseServiceUser = template.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                entity,
                ResponseServiceUser.class,
                userDto.getId()
        ).getBody();

        assertNotNull(remoteResponseServiceUser);
        assertEquals(userDto.getId(), remoteResponseServiceUser.getUser().getId());
    }

    // TODO можно поменять персональные данные без учета пароля
    private void testUpdate(UserDto userDto, String lastName) {
        userDto.setLastName(lastName);

        HttpEntity<UserDto> entity = new HttpEntity<>(userDto, headers);
        ResponseUserServiceDto updateResponseUserServiceDto = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                ResponseUserServiceDto.class
        ).getBody();

        assertNotNull(updateResponseUserServiceDto);
        assertEquals(lastName, updateResponseUserServiceDto.getUserDto().getLastName());
    }

    private void testGetAll(ResponseUserServiceDto... responseUserServiceDtos) {
        template = new RestTemplate();
        List<UserDto> usersDtos = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDto>>() {
                },
                Collections.emptyList()
        ).getBody();

        assertNotNull(usersDtos);

        AtomicInteger i = new AtomicInteger(responseUserServiceDtos.length);
        List<ResponseUserServiceDto> userServiceDtos = new ArrayList<>(Arrays.asList(responseUserServiceDtos));
        userServiceDtos.iterator().forEachRemaining(p -> {
            for (UserDto userDto : usersDtos) {
                if (p.getUserDto().getId() == userDto.getId()) {
                    i.getAndDecrement();
                    break;
                }
            }
        });

        assertEquals(0, i.get()); // admin and user
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
