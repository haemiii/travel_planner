package com.example.controller;

import com.example.TravelPlan;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PlanController.class)
class PlanControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
        secondPlan.setEndDate(LocalDate.of(2024, 6, 12));

    }

    @Test
    @DisplayName("일정 생성 테스트")
    public void testCreatePlan() throws Exception{
        mockMvc.perform(post("/plans/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(firstPlan)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.dayPlanList", hasSize(3)));
    }

    @Test
    @DisplayName("Id 값으로 일정 조회")
    public void testGetPlanById() throws Exception{
        String first = mockMvc.perform(post("/plans/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstPlan)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        String second = mockMvc.perform(post("/plans/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(secondPlan)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        TravelPlan secondPlan = objectMapper.readValue(second, TravelPlan.class);

        mockMvc.perform(get("/plans/"+secondPlan.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("second plan"));


    }
}