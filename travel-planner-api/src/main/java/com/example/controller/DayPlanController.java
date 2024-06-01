package com.example.controller;

import com.example.DayPlan;
import com.example.Location;
import com.example.PlanService;
import com.example.TravelPlan;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/plans/{id}/days")
@RequiredArgsConstructor
public class DayPlanController {

    private final PlanService planService;
    @GetMapping("/{date}")
    public ResponseEntity<List<Location>> getDayPlan(@PathVariable Long id, @PathVariable String date){
        List<Location> dayPlan = planService.getDayPlan(id, date);

        return new ResponseEntity<>(dayPlan, HttpStatus.OK);
    }
}
