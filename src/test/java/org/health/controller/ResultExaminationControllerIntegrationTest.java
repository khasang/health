package org.health.controller;

import org.health.dto.EmployeeDto;
import org.health.entity.ResultExamination;
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

public class ResultExaminationControllerIntegrationTest {
    private static final ResultExamination RESULT_EXAMINATION_1 = preFillResultExamination("Fluorography", "Fine", LocalDate.of(2018, 7, 12));
    private static final ResultExamination RESULT_EXAMINATION_2 = preFillResultExamination("Complete blood count", "No fine", LocalDate.of(2019, 11, 4));
    private static final ResultExamination RESULT_EXAMINATION_3 = preFillResultExamination("Blood sugar test", "No fine", LocalDate.of(2017, 2, 26));

    private static final String ROOT = "http://localhost:8080/resultExamination";
    private static final String ADD = "/add";
    private static final String DELETE = "/delete";
    private static final String UPDATE = "/update";
    private static final String ALL = "/all";
    private static final String GET = "/get";

    @Test
    public void testAllIntegration() {
        ResultExamination addExamination1 = this.addExchangeAssert(RESULT_EXAMINATION_1);
        ResultExamination addExamination2 = this.addExchangeAssert(RESULT_EXAMINATION_2);
        ResultExamination addExamination3 = this.addExchangeAssert(RESULT_EXAMINATION_3);

        ResultExamination getExamination = this.getExchangeAssert(addExamination2);
        ResultExamination updateExamination = this.updateExchangeAssert(getExamination, "Fine");

        ResultExamination deleteExamination = this.deleteExchangeAssert(addExamination1);
        List<ResultExamination> allExchange = this.allExchange();

        System.out.println("end");
    }

    private ResultExamination addExchangeAssert(ResultExamination resultExamination) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<ResultExamination> entity = new HttpEntity<>(resultExamination, headers);

        RestTemplate template = new RestTemplate();
        ResultExamination createdResultExamination = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                ResultExamination.class
        ).getBody();

        assertNotNull(createdResultExamination);
        assertEquals(resultExamination.getName(), createdResultExamination.getName());

        return createdResultExamination;
    }

    private ResultExamination getExchangeAssert(ResultExamination resultExamination) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ResultExamination> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                ResultExamination.class,
                resultExamination.getId()
        );

        ResultExamination receivedResultExamination = responseEntity.getBody();
        assertNotNull(receivedResultExamination);
        assertEquals(resultExamination.getName(), receivedResultExamination.getName());

        return receivedResultExamination;
    }

    private ResultExamination updateExchangeAssert(ResultExamination resultExamination, String answer) {
        resultExamination.setAnswer(answer);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<ResultExamination> entity = new HttpEntity<>(resultExamination, headers);

        RestTemplate template = new RestTemplate();
        ResultExamination createdResultExamination = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                ResultExamination.class
        ).getBody();

        assertNotNull(createdResultExamination);
        assertEquals(answer, createdResultExamination.getAnswer());

        return createdResultExamination;
    }

    private ResultExamination deleteExchangeAssert(ResultExamination resultExamination) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<ResultExamination> entity = new HttpEntity<>(resultExamination, headers);

        RestTemplate template = new RestTemplate();
        ResultExamination createdResultExamination = template.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                entity,
                ResultExamination.class,
                resultExamination.getId()
        ).getBody();

        assertNotNull(createdResultExamination);
        assertEquals(resultExamination.getName(), createdResultExamination.getName());

        return createdResultExamination;
    }

    private List<ResultExamination> allExchange() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ArrayList<ResultExamination>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<ResultExamination>>(){},
                Collections.emptyList()
        );

        List<ResultExamination> resultExaminations = responseEntity.getBody();
        assertNotNull(resultExaminations);

        return resultExaminations;
    }

    private static ResultExamination preFillResultExamination(String name, String answer, LocalDate localDate) {
        ResultExamination resultExamination = new ResultExamination();
        resultExamination.setName(name);
        resultExamination.setAnswer(answer);
        resultExamination.setLocalDate(localDate);

        return resultExamination;
    }
}
