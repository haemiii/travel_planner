package com.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
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
//        this.dayPlanList = generateDayPlans(start_date, end_date);
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