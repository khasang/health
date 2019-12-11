package org.health.controller;

import static org.junit.Assert.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import org.health.dto.RoleDto;
import org.health.dto.UserDto;
import org.health.entity.*;
import org.junit.*;
import org.springframework.core.*;
import org.springframework.http.*;
import org.springframework.web.client.*;

public class UserControllerIntegrationTest {
    private static final String ROOT_USER = "http://localhost:8080/user";
    private static final String ROOT_ROLE = "http://localhost:8080/role";
    private static final String ADD = "/add";
    private static final String DELETE = "/delete";
    private static final String UPDATE = "/update";
    private static final String ALL = "/all";
    private static final String GET = "/get";

    private HttpHeaders headers;
    private RestTemplate template;

    @Before
    public void init() {
        template = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void testGetById() {
        RoleDto roleDto = getEntityById(6, ROOT_ROLE, RoleDto.class).getBody();
        UserDto userDto = getEntityById(67, ROOT_USER, UserDto.class).getBody();

        System.out.println(roleDto);
        System.out.println(userDto);
    }

    private <T>ResponseEntity<T> getEntityById(long id, String root, Class<T> aClass) {
        return new RestTemplate().exchange(
                root + "/get/{id}",
                HttpMethod.GET,
                null,
                aClass,
                id
        );
    }

    @Test
    public void testAddUsers() {
        User user = new User();
        user.setFirstName("Зверик");
        user.setLastName("Екатерина");
        user.setPatronymic("Анатольевна");
        user.setLogin("Ekaterina1000000");
        user.setPassword("Katya");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        RestTemplate template = new RestTemplate();
        UserDto userDto = template.exchange(
                ROOT_USER + ADD,
                HttpMethod.POST,
                entity,
                UserDto.class
        ).getBody();

        System.out.println(userDto);

        assertNotNull(userDto);
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getPatronymic(), userDto.getPatronymic());
        assertEquals(user.getLogin(), userDto.getLogin());
    }

    @Test
    public void testGetAllUsers() {
        template = new RestTemplate();
        ResponseEntity<List<UserDto>> exchange = template.exchange(
                ROOT_USER + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDto>>() {
                },
                Collections.emptyList()
        );

        List<UserDto> usersDtosExchange = exchange.getBody();

        assertNotNull(usersDtosExchange);
        usersDtosExchange.iterator().forEachRemaining(System.out::println);
    }

    @Test
    public void test() {
        User user_1 = createUser(
                "Ivan",
                "Ivanov",
                "Ivanovich",
                "Ivan123",
                "qwerty1");

        User user_2 = createUser(
                "Petr",
                "Petriv",
                "Petrovich",
                "Petr456",
                "qwerty2");

        UserDto userDto_1 = testAdd(user_1);
        UserDto userDto_2 = testAdd(user_2);

        testGetDto(userDto_1.getId());
        testGetDto(userDto_2.getId());

        testGetAll(userDto_1, userDto_2);

        testUpdate(userDto_1, "Sidorov");
        testUpdate(userDto_2, "Vasilev");

        testDelete(userDto_1);
        testDelete(userDto_2);
    }

    private void testDelete(UserDto userDto) {
        HttpEntity<UserDto> entity = new HttpEntity<>(userDto, headers);
        UserDto userDtoExchange = template.exchange(
                ROOT_USER + DELETE + "/{id}",
                HttpMethod.DELETE,
                entity,
                UserDto.class,
                userDto.getId()
        ).getBody();

        assertNotNull(userDtoExchange);
        assertEquals(userDto.getId(), userDtoExchange.getId());
    }

    private void testUpdate(UserDto userDto, String lastName) {
        userDto.setLastName(lastName);

        HttpEntity<UserDto> entity = new HttpEntity<>(userDto, headers);
        UserDto userDto1 = template.exchange(
                ROOT_USER + UPDATE,
                HttpMethod.PUT,
                entity,
                UserDto.class
        ).getBody();

        assertNotNull(userDto1);
        assertEquals(lastName, userDto1.getLastName());
    }

    private void testGetAll(UserDto... userDtos) {
        template = new RestTemplate();
        List<UserDto> usersDtosExchange = template.exchange(
                ROOT_USER + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDto>>() {
                },
                Collections.emptyList()
        ).getBody();

        assertNotNull(usersDtosExchange);

        AtomicInteger i = new AtomicInteger(userDtos.length);
        List<UserDto> userServiceDtos = new ArrayList<>(Arrays.asList(userDtos));
        userServiceDtos.iterator().forEachRemaining(p -> {
            for (UserDto userDto : usersDtosExchange) {
                if (p.getId() == userDto.getId()) {
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
                ROOT_USER + GET + "/{id}",
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

    private UserDto testAdd(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        RestTemplate template = new RestTemplate();
        UserDto userDto = template.exchange(
                ROOT_USER + ADD,
                HttpMethod.POST,
                entity,
                UserDto.class
        ).getBody();

        assertNotNull(userDto);
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getPatronymic(), userDto.getPatronymic());
        assertEquals(user.getLogin(), userDto.getLogin());

        return userDto;
    }

    private User createUser(String firstName, String lastName, String patronymic, String login, String pass) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPatronymic(patronymic);
        user.setLogin(login);
        user.setPassword(pass);

        return user;
    }
}
