package com.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class TravelPlan{
    private Long id;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private Map<LocalDate, DayPlan> dayPlanList;

    public TravelPlan(){}

    public TravelPlan(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
//        this.dayPlanList = generateDayPlans(start_date, end_date);
    }
    @Override
    public String toString() {
        return "TravelPlan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", dayPlanList=" + dayPlanList +
                '}';
    }
}