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
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/plans/{id}/days/{date}")
@RequiredArgsConstructor
public class LocationController {

    private final PlanService planService;


    @PostMapping("/locations")
    public ResponseEntity<List<Location>> addPlace(@PathVariable Long id, @PathVariable String date, @RequestBody Location location){

        List<Location> locations = planService.addPlace(id, date, location);
        return new ResponseEntity<>(locations, HttpStatus.CREATED);

    }

}
