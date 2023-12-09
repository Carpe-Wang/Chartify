package com.chartify.chartify.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserData {
    private String userName;
    private String password;
    private String email;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String userId;
}
