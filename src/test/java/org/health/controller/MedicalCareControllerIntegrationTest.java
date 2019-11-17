package org.health.controller;

import static org.junit.Assert.*;

import java.util.*;

import org.health.entity.*;
import org.junit.*;
import org.springframework.core.*;
import org.springframework.http.*;
import org.springframework.web.client.*;

public class MedicalCareControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080//medicalCare";
    private static final String ADD = "/add";
    private static final String DELETE = "/delete";
    private static final String UPDATE = "/update";
    private static final String ALL = "/all";
    private static final String GET = "/get";

    private HttpHeaders headers;
    private RestTemplate template;
    private HttpEntity<MedicalCare> entity;

    @Before
    public void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void test() {
        MedicalCare medicalCare_1 = testAdd(createMedicalCare(
                1001,
                "Initial consultation",
                "Primary consultation of the therapist"));
        MedicalCare medicalCare_2 = testAdd(createMedicalCare(
                1002,
                "Subsequent consultations",
                "Subsequent consultations therapist"));

        testGet(medicalCare_1.getId());
        testGet(medicalCare_2.getId());

        testGetAll();

        testUpdate(medicalCare_1, 2001);

        testDelete(medicalCare_1);
        testDelete(medicalCare_2);
    }

    private void testDelete(MedicalCare medicalCare) {
        entity = new HttpEntity<>(medicalCare, headers);
        template = new RestTemplate();

        MedicalCare remoteMedicalCare = template.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                entity,
                MedicalCare.class,
                medicalCare.getId()
        ).getBody();

        assertNotNull(remoteMedicalCare);
        assertEquals(medicalCare.getId(), remoteMedicalCare.getId());
    }

    private void testUpdate(MedicalCare medicalCare, long code) {
        medicalCare.setCod(code);

        entity = new HttpEntity<>(medicalCare, headers);
        template = new RestTemplate();

        MedicalCare updateMedicalCare = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                MedicalCare.class
        ).getBody();

        assertNotNull(updateMedicalCare);
        assertEquals(code, updateMedicalCare.getCod());
    }

    private void testGetAll() {
        template = new RestTemplate();
        List<MedicalCare> medicalCares = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MedicalCare>>() {},
                Collections.emptyList()
        ).getBody();

        assertNotNull(medicalCares);
    }

    private void testGet(long id) {
        template = new RestTemplate();
        ResponseEntity<MedicalCare> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                MedicalCare.class,
                id
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        MedicalCare medicalCare = responseEntity.getBody();
        assertNotNull(medicalCare);
        assertEquals(id, medicalCare.getId());
    }

    private MedicalCare testAdd(MedicalCare medicalCare) {
        entity = new HttpEntity<>(medicalCare, headers);
        template = new RestTemplate();

        MedicalCare createdMedicalCare = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                MedicalCare.class
        ).getBody();

        assertNotNull(createdMedicalCare);
        assertEquals(medicalCare.getCod(), createdMedicalCare.getCod());
        assertEquals(medicalCare.getName(), createdMedicalCare.getName());
        assertEquals(medicalCare.getDescription(), createdMedicalCare.getDescription());

        return createdMedicalCare;
    }

    private MedicalCare createMedicalCare(long code, String name, String description) {
        MedicalCare medicalCare = new MedicalCare();
        medicalCare.setCod(code);
        medicalCare.setName(name);
        medicalCare.setDescription(description);
        return medicalCare;
    }
}
