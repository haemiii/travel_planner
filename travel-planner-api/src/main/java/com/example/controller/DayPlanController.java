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
@RequestMapping("/plans/{id}/days")
public class DayPlanController {

    @GetMapping("/{date}")
    public ResponseEntity<List<Location>> getDayPlan(@PathVariable Long id, @PathVariable String date){
        LocalDate localDate = LocalDate.parse(date);
        // date에 해당하는 dayPlan 반환
        List<Location> locationList = getLocations(id, localDate);
        return new ResponseEntity<>(locationList, HttpStatus.OK);
    }

    private List<Location> getLocations(Long id, LocalDate date) {
        TravelPlan travelPlan = plans.get(id);
        Map<LocalDate, DayPlan> dayPlanList = travelPlan.getDayPlanList();
        DayPlan dayPlan = dayPlanList.get(date);
        return dayPlan.getLocationList();
    }
}
