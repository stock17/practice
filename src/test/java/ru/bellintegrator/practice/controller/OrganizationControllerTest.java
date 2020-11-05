package ru.bellintegrator.practice.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import ru.bellintegrator.practice.view.OrganizationView;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrganizationControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static OrganizationView organizationView;

    @BeforeEach
    void beforeEach() {
        organizationView = new OrganizationView();
        organizationView.setName("test name");
        organizationView.setFullName("test full name");
        organizationView.setInn("1234567890");
        organizationView.setKpp("123456789");
        organizationView.setAddress("test address");
        organizationView.setPhone("123-456-789");
        organizationView.setActive(true);
    }

    @Test
    void getOrganizationById() {
        String response = restTemplate.getForObject("http://localhost:" + port + "/api/organization/1", String.class);
        assertFalse(response.isEmpty());
        assertFalse(response.contains("error"));
    }

    @Test
    void getOrganizationsByName() {
        Map<String, String> request = new HashMap<>();
        request.put("name", "test name");
        String response = restTemplate.postForObject ("http://localhost:" + port + "/api/organization/list",
                                                        request, String.class);
        assertFalse(response.isEmpty());
        assertFalse(response.contains("error"));
    }

    @Test
    void save() {
        String response = restTemplate.postForObject ("http://localhost:" + port + "/api/organization/save",
                                                        organizationView, String.class);
        assertFalse(response.isEmpty());
        assertEquals("{\"result\":\"success\"}", response);
    }

    @Test
    void update() {
        organizationView.setId(1);
        String response = restTemplate.postForObject (
                "http://localhost:" + port + "/api/organization/update",
                organizationView, String.class);
        assertFalse(response.isEmpty());
        assertEquals("{\"result\":\"success\"}", response);
    }
}