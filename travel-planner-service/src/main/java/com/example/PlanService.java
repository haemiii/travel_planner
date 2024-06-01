package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class PlanService {

    static Map<Long, TravelPlan> plans = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong(1);
    private AtomicLong dayPlanId = new AtomicLong(1);
    private AtomicLong locationId = new AtomicLong(1);

    public TravelPlan createPlan(TravelPlan travelPlan) {
        travelPlan.setId(id.getAndIncrement());

        Map<LocalDate, DayPlan> dayPlans = generateDayPlans(travelPlan.getStartDate(), travelPlan.getEndDate());
        travelPlan.setDayPlanList(dayPlans);
        // travelPlan 저장 로직 추가 예정
        plans.put(travelPlan.getId(), travelPlan);
        return travelPlan;
    }

    public Map<Long, TravelPlan> getAllPlans() {
        return plans;
    }

    public TravelPlan getPlanById(Long id) {
        return plans.get(id);
    }

    public Map<LocalDate, DayPlan> generateDayPlans(LocalDate startDate, LocalDate endDate) {
        Map<LocalDate, DayPlan> dayPlans = new ConcurrentHashMap<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)){
            DayPlan dayPlan = new DayPlan();
            dayPlan.setId(dayPlanId.getAndIncrement());
            dayPlan.setDate(currentDate);
            dayPlan.setLocationList(new CopyOnWriteArrayList<>());

            dayPlans.put(dayPlan.getDate(), dayPlan);
            currentDate = currentDate.plusDays(1);
        }
        dayPlanId.set(1);
        return dayPlans;
    }

    public List<Location> getDayPlan(Long id, String date) {
        LocalDate localDate = LocalDate.parse(date);

        // date에 해당하는 dayPlan 반환
        return getLocations(id, localDate);
    }

    public List<Location> addPlace(Long id, String date, Location location) {
        LocalDate localDate = LocalDate.parse(date);
        // date에 해당하는 location 추가!
        List<Location> locationList = getLocations(id, localDate);
        location.setId(locationId.getAndIncrement());
        locationList.add(location);

        return locationList;
    }

    public List<Location> getLocations(Long id, LocalDate date) {
        TravelPlan travelPlan = plans.get(id);
        Map<LocalDate, DayPlan> dayPlanList = travelPlan.getDayPlanList();
        DayPlan dayPlan = dayPlanList.get(date);
        return dayPlan.getLocationList();
    }
}
