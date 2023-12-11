package com.chartify.chartify.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectData {
    private Long id;
    private String name;
    private String description;
    private String userId;
    private String base64Image;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
