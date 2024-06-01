package com.example.controller;

import com.example.DayPlan;
import com.example.Location;
import com.example.TravelPlan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static com.example.controller.PlanController.plans;

@RestController
@RequestMapping("/plans/{id}/days/{date}")
public class LocationController {

    private AtomicLong locationId = new AtomicLong(1);

    @PostMapping("/locations")
    public ResponseEntity<List<Location>> addPlace(@PathVariable Long id, @PathVariable String date, @RequestBody Location location){
        LocalDate localDate = LocalDate.parse(date);
        // date에 해당하는 location 추가!
        List<Location> locationList = getLocations(id, localDate);
        location.setId(locationId.getAndIncrement());
        locationList.add(location);
        return new ResponseEntity<>(locationList, HttpStatus.CREATED);
    }

    private List<Location> getLocations(Long id, LocalDate date) {
        TravelPlan travelPlan = plans.get(id);
        Map<LocalDate, DayPlan> dayPlanList = travelPlan.getDayPlanList();
        DayPlan dayPlan = dayPlanList.get(date);
        return dayPlan.getLocationList();
    }
}
