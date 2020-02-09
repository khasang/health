package org.health.controller;

import org.health.dto.response.ResponseSpecialty;
import org.health.entity.Specialty;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class SpecialtyControllerIntegrationTest {
    private static final String ROOT_USER = "http://localhost:8080/specialty";
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
        Specialty specialty = apiSpecialtyPost(new Specialty("TEST_1"));
        System.out.println(specialty);
    }

    private ResponseSpecialty apiSpecialtyGetById(int id) {
        return template.exchange(
                "http://localhost:8080/specialty/{id}",
                HttpMethod.GET,
                null,
                ResponseSpecialty.class,
                id
        ).getBody();
    }

    private Specialty apiSpecialtyPost(Specialty specialty) {
        return template.exchange(
                "http://localhost:8080/specialty",
                HttpMethod.POST,
                new HttpEntity<>(specialty, headers),
                Specialty.class
        ).getBody();
    }
}
