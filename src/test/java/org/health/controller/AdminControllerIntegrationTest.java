package org.health.controller;

import org.health.dto.UserDto;
import org.health.entity.Role;
import org.health.entity.userdb.User;
import org.health.entity.userdb.extend.Admin;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.Assert.assertNull;

public class AdminControllerIntegrationTest {
    private static final String ROOT_USER = "http://localhost:8080/admin";

    private HttpHeaders headers;
    private RestTemplate template;

    @Before
    public void init() {
        template = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void restApiPost() {
        User user = new User();
        user.setFirstName("Роман_1");
        user.setLastName("Зверик_1");
        user.setPatronymic("Станиславович_1");
        user.setLogin("Roman1");
        user.setPassword("Roman1");
        Role role = this.getRole(3);
        user.setCurrentRole(role);

        Set<Role> roleList = new HashSet<>();
        roleList.add(role);
        user.setRoles(roleList);

//        Admin admin = new Admin();
//        admin.setUser(user);
//        admin.setDescription("Admin_1");

        UserDto responseUser = template.exchange(
                "http://localhost:8080/user/add",
                HttpMethod.POST,
                new HttpEntity<>(user, headers),
                UserDto.class
        ).getBody();

        System.out.println(responseUser);
    }

    @Test
    public void restApiGetAll() {
        List<Admin> adminList = template.exchange(
                ROOT_USER,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Admin>>() {
                },
                Collections.emptyList()
        ).getBody();

        System.out.println(adminList);
    }

    @Test
    public void restApiGetId() {
        Admin responseAdmin = template.exchange(
                ROOT_USER + "/" + 1,
                HttpMethod.GET,
                null,
                Admin.class
        ).getBody();

        System.out.println(responseAdmin);
    }

    @Test
    public void restApiPut() {
        Admin responseAdmin = template.exchange(
                ROOT_USER,
                HttpMethod.PUT,
                new HttpEntity<>(new Admin(), headers),
                Admin.class
        ).getBody();

        System.out.println(responseAdmin);
    }

    @Test
    public void restApiDelete() {
        Admin adminDel = template.exchange(
                ROOT_USER + "/" + 1,
                HttpMethod.DELETE,
                null,
                Admin.class
        ).getBody();

        System.out.println(adminDel);
    }

    private Role getRole(int id) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<Role> responseEntity = template.exchange(
                "http://localhost:8080/role/get/{id}",
                HttpMethod.GET,
                null,
                Role.class,
                id
        );

        return responseEntity.getBody();
    }
}
