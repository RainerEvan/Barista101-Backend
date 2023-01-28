package com.project.barista101.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BrewRequest {
    private String title;
    private String description;
    private Integer coffee;
    private Integer water;
    private Integer ratio;
    private Integer time;
    private String grindSize;
    private String preparations;
    private String steps;
}
