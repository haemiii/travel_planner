package com.example;

import java.time.LocalDate;
import java.util.List;

public class DayPlan {
    private Long id;
    private LocalDate date;
    private List<Location> locationList;

    public DayPlan(){}

    public DayPlan(LocalDate date){
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    @Override
    public String toString() {
        return "DayPlan{" +
                "id=" + id +
                ", date=" + date +
                ", locationList=" + locationList +
                '}';
    }
}
