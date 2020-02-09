package org.health.controller;

import org.health.config.jpa.HibernateConfig;
import org.health.entity.Role;
import org.health.entity.Specialty;
import org.health.entity.userdb.User;
import org.health.entity.userdb.extend.Admin;
import org.health.entity.userdb.extend.Doctor;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DoctorControllerIntegrationTest {
    private static final String ROOT_USER = "http://localhost:8080/doctor";
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
        System.out.println("apiSpecialtyPost=" + apiSpecialtyPost(new Specialty("TEST")));
        RequestApiDoctorPostAll requestApiDoctorPostAll = new RequestApiDoctorPostAll();
//        requestApiDoctorPostAll.add("Гастроэнтеролог",
//                "АВАЛУЕВА Елена Борисовна",
//                "БАХВАЛОВА Наталья Юрьевна",
//                "КАРПЕЕВА Юлия Сергеевна",
//                "САМЕДОВ Бейбала Ханбалаевич",
//                "ШЕСТАКОВА Наталия Владимировна");

        requestApiDoctorPostAll.add("Гастроэнтеролог",
                "АВАЛУЕВА Елена Борисовна",
                "КАРПЕЕВА Юлия Сергеевна");

//        requestApiDoctorPostAll.add("Терапевт",
//                "АВАЛУЕВА Елена Борисовна",
//                "КАРПЕЕВА Юлия Сергеевна");
//
        requestApiDoctorPostAll.makeRequest();
    }

    private Doctor apiDoctorDelete(long id) {
        return template.exchange(
                "http://localhost:8080/doctor/{id}",
                HttpMethod.DELETE,
                null,
                Doctor.class,
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

    private Specialty createSpecialty(String nameSpecialty) {
        Specialty specialty = new Specialty();
        specialty.setTitle(nameSpecialty);
        return specialty;
    }

    private Doctor createDoctor(String fullNameDoctor, Specialty specialty) {
        User user = new User();
        String[] strings = fullNameDoctor.split("\\s");

        user.setLastName(formatText(strings[0]));
        user.setFirstName(formatText(strings[1]));
        user.setPatronymic(formatText(strings[2]));
        user.setLogin(strings[0]);
        user.setPassword("123");

        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.getSpecialties().add(specialty);

        return doctor;
    }

    private Doctor apiDoctorPost(Doctor doctor) {
        return template.exchange(
                "http://localhost:8080/doctor",
                HttpMethod.POST,
                new HttpEntity<>(doctor, headers),
                Doctor.class
        ).getBody();
    }

    private String formatText(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
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

    private Specialty apiSpecialtyGetById(int id) {
        return template.exchange(
                "http://localhost:8080/specialty/{id}",
                HttpMethod.GET,
                null,
                Specialty.class,
                id
        ).getBody();
    }

    private class RequestApiDoctorPostAll {
        private ArrayList<RequestApiDoctorPost> requestApiDoctorPosts = new ArrayList<>();

        private void add(String specialtyTitle, String... nameDoctors) {
            RequestApiDoctorPost requestApiDoctorPost = new RequestApiDoctorPost(specialtyTitle);
            for (String nameDoctor : nameDoctors)
                requestApiDoctorPost.addCreateDoctor(nameDoctor);

            requestApiDoctorPosts.add(requestApiDoctorPost);
        }

        private void makeRequest() {
            this.requestApiDoctorPosts.iterator().forEachRemaining(RequestApiDoctorPost::makeRequest);
        }
    }

    private class RequestApiDoctorPost {
        private final String specialtyTitle;

        private ArrayList<String> nameDoctors = new ArrayList<>();

        private RequestApiDoctorPost(String specialtyTitle) {
            this.specialtyTitle = specialtyTitle;
        }

        private void addCreateDoctor(String nameDoctor) {
            this.nameDoctors.add(nameDoctor);
        }

        private void makeRequest() {
            Specialty specialtyApi = apiSpecialtyPost(createSpecialty(specialtyTitle));
            this.nameDoctors.iterator().forEachRemaining(name -> apiDoctorPost(createDoctor(name, specialtyApi)));
        }

    }
}
