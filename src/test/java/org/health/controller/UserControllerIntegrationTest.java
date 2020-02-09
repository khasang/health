package org.health.controller;

import org.health.dto.RoleDto;
import org.health.dto.UserDto;
import org.health.entity.Role;
import org.health.entity.Specialty;
import org.health.entity.userdb.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

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
    public void addNewUser() {
        // получаем все роли
        Set<RoleDto> allRoles = getAllRoles();
        System.out.println("allRoles=" + allRoles);
        System.out.println();
//
//        User userTest = createUserTest();
//        System.out.println("userTest=" + userTest);
//        System.out.println();
//
//        UserDto userPost = userApiMethodPost(userTest);
//        System.out.println("userPost=" + userPost);
//        System.out.println();
//
//        Set<Role> roles = new HashSet<>();
//        allRoles.iterator().forEachRemaining(r -> roles.add(new Role(r)));
//        userPost.setRoleDtos(allRoles);
//        System.out.println("userPostAndRole=" + userPost);
//
//        userPost.setPassword("123");
//        User userPut = userApiMethodPut(userPost);
//        System.out.println("userPut=" + userPut);
    }

    private User createUserTest() {
        User user = new User();
        user.setFirstName("firstName_test");
        user.setLastName("lastName_test");
        user.setPatronymic("patronymic_test");
        user.setLogin("login_test");
        user.setPassword("password_test");

        return user;
    }

    public UserDto userApiMethodPost(User user) {
        return template.exchange(
                "http://localhost:8080/user",
                HttpMethod.POST,
                new HttpEntity<>(user, headers),
                UserDto.class
        ).getBody();
    }

    public User userApiMethodPut(User user) {
        return template.exchange(
                "http://localhost:8080/user",
                HttpMethod.PUT,
                new HttpEntity<>(user, headers),
                User.class
        ).getBody();
    }

    private Set<RoleDto> getAllRoles() {
        ResponseEntity<Set<RoleDto>> exchange = template.exchange(
                "http://localhost:8080/role/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Set<RoleDto>>() {
                },
                Collections.emptyList()
        );

        Set<RoleDto> roleList = exchange.getBody();

        assertNotNull(roleList);
        return roleList;
    }
}
