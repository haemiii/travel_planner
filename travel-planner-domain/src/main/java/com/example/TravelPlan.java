package com.example;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TravelPlan{
    private Long id;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;

    private List<DayPlan> dayPlanList;

    public TravelPlan(){}

    public TravelPlan(String name, LocalDate start_date, LocalDate end_date) {
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.dayPlanList = generateDayPlans(start_date, end_date);
    }

    private List<DayPlan> generateDayPlans(LocalDate startDate, LocalDate endDate) {
        List<DayPlan> dayPlans = new ArrayList<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)){
            DayPlan dayPlan = new DayPlan(currentDate);
            dayPlans.add(dayPlan);
            currentDate = currentDate.plusDays(1);
        }
        return dayPlans;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }
    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }


    public void setDayPlanList(LocalDate startDate, LocalDate endDate) {
        this.dayPlanList = generateDayPlans(startDate, endDate);
    }

    @Override
    public String toString() {
        return "TravelPlan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", dayPlanList=" + dayPlanList +
                '}';
    }
}