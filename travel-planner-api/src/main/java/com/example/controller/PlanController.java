package com.example.controller;

import com.example.TravelPlan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/plans")
public class PlanController {

    private Map<Long, TravelPlan> plans = new HashMap<>();
    private Long id = 1L;

    @PostMapping("/create")
    public ResponseEntity<TravelPlan> createPlan(@RequestBody TravelPlan travelPlan){
        travelPlan.setDayPlanList(travelPlan.getStart_date(), travelPlan.getEnd_date());

        // travelPlan 저장 로직 추가 예정
        plans.put(id++, travelPlan);
        return new ResponseEntity<>(travelPlan, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<Long, TravelPlan>> getAllPlans(){

        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelPlan> getPlanById(@PathVariable Long id){

        TravelPlan travelPlan = plans.get(id);
        return new ResponseEntity<>(travelPlan, HttpStatus.OK);
    }

}