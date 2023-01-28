package com.project.barista101.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseRequest {
    private String title;
    private String description;
}
