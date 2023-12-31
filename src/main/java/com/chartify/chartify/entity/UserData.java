package com.chartify.chartify.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserData {
    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

}
