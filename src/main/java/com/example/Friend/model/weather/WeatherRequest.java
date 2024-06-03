package com.example.Friend.model.weather;

import lombok.Data;

@Data
public class WeatherRequest {
    private String token;
    private double latitude;
    private double longitude;
}
