package com.example.controller;

import com.example.PlanService;
import com.example.TravelPlan;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping
    public ResponseEntity<TravelPlan> createPlan(@RequestBody TravelPlan travelPlan){

        TravelPlan plan = planService.createPlan(travelPlan);
        return new ResponseEntity<>(plan, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TravelPlan>> getAllPlans(){

        Map<Long, TravelPlan> plans = planService.getAllPlans();
        return new ResponseEntity<>(new ArrayList<>(plans.values()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelPlan> getPlanById(@PathVariable Long id){

        TravelPlan plan = planService.getPlanById(id);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }


}
