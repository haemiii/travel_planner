package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PlanServiceTest {

    @InjectMocks
    private PlanService planService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    private TravelPlan createTravelPlan() {
        TravelPlan travelPlan = new TravelPlan();
        travelPlan.setStartDate(LocalDate.of(2024, 6, 1));
        travelPlan.setEndDate(LocalDate.of(2024, 6, 3));
        return travelPlan;
    }

    @Test
    public void testCreatePlan(){

        TravelPlan travelPlan = createTravelPlan();
        TravelPlan result = planService.createPlan(travelPlan);

        assertNotNull(result.getId());
        assertEquals(3, travelPlan.getDayPlanList().size());
    }

    @Test
    public void testGetAllPlans(){
        TravelPlan travelPlan = createTravelPlan();
        planService.createPlan(travelPlan);

        Map<Long, TravelPlan> allPlans = planService.getAllPlans();

        assertFalse(allPlans.isEmpty());
    }

    @Test
    public void testGetPlansById(){
        TravelPlan travelPlan = createTravelPlan();
        TravelPlan createdPlan = planService.createPlan(travelPlan);
        Long id = createdPlan.getId();

        TravelPlan plan = planService.getPlanById(travelPlan.getId());
        assertNotNull(plan);
        assertEquals(id, plan.getId());
    }

    @Test
    public void testADdPlace(){
        TravelPlan travelPlan = createTravelPlan();
        TravelPlan createdPlan = planService.createPlan(travelPlan);
        Long id = createdPlan.getId();
        String date = "2024-06-02";

        Location location = new Location();
        location.setName("경복궁");
        location.setLatitude(34.546);
        location.setLongitude(78.546);

        planService.addPlace(id, date, location);

        assertEquals(1, planService.getDayPlan(id, date).size());
    }
}