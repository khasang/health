package org.health.controller;

import org.health.entity.Inspection;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class InspectionControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/inspection";
    private static final String ADD = "/add";
    private static final String UPDATE = "/update";
    private static final String GET = "/get";
    private static final String ALL = "/all";
    private static final String DELETE = "/delete";

    @Test
    public void testAddAndGet() {
        Inspection inspection = createInspection();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Inspection> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Inspection.class,
                inspection.getId()
        );
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity);
    }

    @Test
    public void testUpdate() {
        Inspection inspection = createInspection();
        inspection.setPrice(5000);
        inspection.setRoom(111);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate template = new RestTemplate();
        HttpEntity<Inspection> entity = new HttpEntity<>(inspection, headers);
        Inspection updatedInspection = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                Inspection.class
        ).getBody();
        Assert.assertEquals(updatedInspection.getId(), inspection.getId());
        Assert.assertEquals(updatedInspection.getDuration(), inspection.getDuration());
        Assert.assertEquals(updatedInspection.getPrice(), inspection.getPrice());
        Assert.assertEquals(updatedInspection.getRoom(), inspection.getRoom());
        Assert.assertEquals(updatedInspection.getTime(), inspection.getTime());
    }

    @Test
    public void testDelete() {
        Inspection inspection = createInspection();
        RestTemplate template = new RestTemplate();
        ResponseEntity<Inspection> responseEntity = template.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                null,
                Inspection.class,
                inspection.getId()
        );
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAll() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Inspection>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Inspection>>() {},
                Collections.emptyList()
        );
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    private Inspection createInspection() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Inspection inspection = fillInspection();
        HttpEntity<Inspection> entity = new HttpEntity<>(inspection, headers);
        RestTemplate template = new RestTemplate();
        Inspection createdInspection = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Inspection.class
        ).getBody();
        Assert.assertNotNull(createdInspection);
        Assert.assertEquals(199, createdInspection.getRoom());
        Assert.assertEquals(190000, createdInspection.getDuration());
        Assert.assertEquals(1200, createdInspection.getPrice());
        Assert.assertEquals(
                LocalDateTime.of(2019, 11, 6, 11, 0, 0),
                createdInspection.getTime()
        );
        return createdInspection;
    }

    private Inspection fillInspection() {
        Inspection inspection = new Inspection();
        inspection.setTime(LocalDateTime.of(2019,11, 6, 11, 0, 0));
        inspection.setRoom(199);
        inspection.setDuration(190000);
        inspection.setPrice(1200);
        return inspection;
    }
}