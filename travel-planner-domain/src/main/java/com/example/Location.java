package com.example;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Location {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;

    public Location(){}
    public Location(Long id, String name, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
