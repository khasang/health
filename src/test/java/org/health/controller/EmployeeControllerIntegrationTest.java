package org.health.controller;

import org.health.dto.EmployeeDto;
import org.health.entity.Car;
import org.health.entity.Employee;
import org.health.entity.Mare;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmployeeControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/employee";
    private static final String ADD = "/add";
    private static final String DELETE = "/delete";
    private static final String UPDATE = "/update";
    private static final String ALL = "/all";
    private static final String GET = "/get";

    @Test
    public void testAddAndGet() {
        Employee vano = createEmployee();

        RestTemplate template = new RestTemplate();
        ResponseEntity<EmployeeDto> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                EmployeeDto.class,
                vano.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        EmployeeDto receivedEmployee = responseEntity.getBody();
        assertNotNull(receivedEmployee);
    }

    private Employee createEmployee() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Employee employee = prefillEmployee();

        HttpEntity<Employee> entity = new HttpEntity<>(employee, headers);

        RestTemplate template = new RestTemplate();
        Employee createdEmployee = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Employee.class
        ).getBody();

        assertNotNull(createdEmployee);
        assertEquals("Vano", createdEmployee.getName());
        return createdEmployee;
    }

    private Employee prefillEmployee() {
        Employee employee = new Employee();
        employee.setName("Vano");
        employee.setTitle("Director");

        Car vaz = new Car();
        vaz.setYear(LocalDate.of(2001, 2, 11));
        vaz.setModel("LADA");

        Car bmw = new Car();
        bmw.setYear(LocalDate.of(2006, 1, 22));
        bmw.setModel("BMW");

        List<Car> list = new ArrayList<>();
        list.add(vaz);
        list.add(bmw);
        employee.setCarList(list);

        return employee;
    }
}
