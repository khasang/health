package org.health.controller;

import org.health.entity.Examination;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExaminationControllerIntegrationTest {
    private static final Examination EXAMINATION_1 = preFillExamination("Initial examination", "No fine", LocalDate.of(2018, 7, 12));
    private static final Examination EXAMINATION_2 = preFillExamination("Comprehensive inspection", "No fine", LocalDate.of(2019, 11, 4));
    private static final Examination EXAMINATION_3 = preFillExamination("Extraordinary inspection", "No fine", LocalDate.of(2017, 2, 26));

    private static final String ROOT = "http://localhost:8080/examination";
    private static final String ADD = "/add";
    private static final String DELETE = "/delete";
    private static final String UPDATE = "/update";
    private static final String ALL = "/all";
    private static final String GET = "/get";

    @Test
    public void testAllIntegration() {
        Examination addExamination1 = this.addExchangeAssert(EXAMINATION_1);
        Examination addExamination2 = this.addExchangeAssert(EXAMINATION_2);
        Examination addExamination3 = this.addExchangeAssert(EXAMINATION_3);

        Examination getExamination = this.getExchangeAssert(addExamination2);
        Examination updateExamination = this.updateExchangeAssert(getExamination, "Fine");

        Examination deleteExamination = this.deleteExchangeAssert(addExamination1);
        List<Examination> allExchange = this.allExchange();

        System.out.println("end");
    }

    private Examination addExchangeAssert(Examination examination) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Examination> entity = new HttpEntity<>(examination, headers);

        RestTemplate template = new RestTemplate();
        Examination createdExamination = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Examination.class
        ).getBody();

        assertNotNull(createdExamination);
        assertEquals(examination.getName(), createdExamination.getName());

        return createdExamination;
    }

    private Examination getExchangeAssert(Examination examination) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<Examination> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Examination.class,
                examination.getId()
        );

        Examination receivedExamination = responseEntity.getBody();
        assertNotNull(receivedExamination);
        assertEquals(examination.getName(), receivedExamination.getName());

        return receivedExamination;
    }

    private Examination updateExchangeAssert(Examination examination, String conclusion) {
        examination.setConclusion(conclusion);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Examination> entity = new HttpEntity<>(examination, headers);

        RestTemplate template = new RestTemplate();
        Examination createdExamination = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                Examination.class
        ).getBody();

        assertNotNull(createdExamination);
        assertEquals(conclusion, createdExamination.getConclusion());

        return createdExamination;
    }

    private Examination deleteExchangeAssert(Examination examination) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Examination> entity = new HttpEntity<>(examination, headers);

        RestTemplate template = new RestTemplate();
        Examination createdExamination = template.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                entity,
                Examination.class,
                examination.getId()
        ).getBody();

        assertNotNull(createdExamination);
        assertEquals(examination.getName(), createdExamination.getName());

        return createdExamination;
    }

    private List<Examination> allExchange() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ArrayList<Examination>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<Examination>>(){},
                Collections.emptyList()
        );

        List<Examination> resultExaminations = responseEntity.getBody();
        assertNotNull(resultExaminations);

        return resultExaminations;
    }

    private static Examination preFillExamination(String name, String conclusion, LocalDate localDate) {
        Examination examination = new Examination();
        examination.setName(name);
        examination.setConclusion(conclusion);
        examination.setLocalDate(localDate);

        return examination;
    }
}
