package com.example.controller;

import com.example.TravelPlan;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlanControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private TravelPlan firstPlan;
    private TravelPlan secondPlan;


    @BeforeEach
    public void setUp(){
        firstPlan = new TravelPlan();
        firstPlan.setName("first plan");
        firstPlan.setStartDate(LocalDate.of(2024, 6, 1));
        firstPlan.setEndDate(LocalDate.of(2024, 6, 3));

        secondPlan = new TravelPlan();
        secondPlan.setName("second plan");
        secondPlan.setStartDate(LocalDate.of(2024, 6, 10));
        secondPlan.setEndDate(LocalDate.of(2024, 6, 13));

    }

    @Test
    @DisplayName("일정 생성 테스트")
    public void testCreatePlan() throws Exception{
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(firstPlan), headers);
        ResponseEntity<String> response =  testRestTemplate.postForEntity("/plans/create", firstPlan, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        TravelPlan travelPlan = objectMapper.readValue(response.getBody(), TravelPlan.class);
        assertThat(travelPlan.getId()).isNotNull();
        assertThat(travelPlan.getDayPlanList()).hasSize(3);

    }

    @Test
    @DisplayName("Id 값으로 일정 조회")
    public void testGetPlanById() throws Exception {
        ResponseEntity<TravelPlan> response1 = testRestTemplate.postForEntity("/plans/create", firstPlan, TravelPlan.class);
        ResponseEntity<TravelPlan> response2 = testRestTemplate.postForEntity("/plans/create", secondPlan, TravelPlan.class);

        // id값으로 일정조회
        ResponseEntity<TravelPlan> getResponse1 = testRestTemplate.getForEntity("/plans/{id}", TravelPlan.class, 1);
        ResponseEntity<TravelPlan> getResponse2 = testRestTemplate.getForEntity("/plans/{id}", TravelPlan.class, 2);
        assertThat(getResponse1.getStatusCode()).isEqualTo(HttpStatus.OK);

        TravelPlan travelPlan1 = getResponse1.getBody();
        TravelPlan travelPlan2 = getResponse2.getBody();

        assertThat(travelPlan1.getName()).isEqualTo("first plan");
        assertThat(travelPlan1.getDayPlanList()).hasSize(3);
        assertThat(travelPlan2.getName()).isEqualTo("second plan");
        assertThat(travelPlan2.getDayPlanList()).hasSize(4);
    }
}