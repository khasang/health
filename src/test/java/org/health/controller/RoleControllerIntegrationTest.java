package org.health.controller;

import org.health.dto.RoleDto;
import org.health.dto.UserDto;
import org.health.entity.Role;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RoleControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/role/";
    private static final String ADD = "add";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String GET = "get";
    private static final String ALL = "all";

    @Test
    public void testGetAllRoles() {
        getAllRoles().iterator().forEachRemaining(System.out::println);
    }

    @Test
    public void testDelAddGet() {
        Role role = createRole();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Role> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Role.class,
                role.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Role receivedRole = responseEntity.getBody();
        assertNotNull(receivedRole);

        getAllRoles();

        deleteRolebyId(role.getId());
    }

    private Role createRole() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Role role = prefillRole();
        HttpEntity<Role> entity = new HttpEntity<>(role, headers);
        RestTemplate template = new RestTemplate();
        Role createdRole = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Role.class
        ).getBody();

        assertNotNull(createdRole);
        assertEquals("ROLE_TEST", createdRole.getName());

        return createdRole;
    }

    private Role prefillRole() {
        Role role = new Role();
        role.setName("ROLE_TEST");
        role.setDescription("test role");
        return role;
    }

    private List<RoleDto> getAllRoles() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<RoleDto>> exchange = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RoleDto>>() {
                },
                Collections.emptyList()
        );

        List<RoleDto> roleList = exchange.getBody();

        assertNotNull(roleList);
        return roleList;
    }

    private void deleteRolebyId(long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Role role = prefillRole();
        HttpEntity<Role> entity = new HttpEntity<>(role, headers);
        RestTemplate template = new RestTemplate();
        Role roleDel = template.exchange(
                ROOT + DELETE + "/" + id,
                HttpMethod.DELETE,
                entity,
                Role.class
        ).getBody();

        assertNotNull(roleDel);
    }
}
