package com.chartify.chartify.entity.sqlEntity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DatabaseCredentials {
    private String address;
    private String username;
    private String password;
}
