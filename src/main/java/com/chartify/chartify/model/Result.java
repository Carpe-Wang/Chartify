package com.chartify.chartify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {
    private Boolean isSuccess;
    private String message;
    private T data;

    //手动添加构造器，isSuccess为false情况下不需要data
    public Result(Boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = null; // 或者适合您使用的默认值
    }
}
