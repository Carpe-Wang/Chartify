package com.chartify.chartify.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartData {
    private Map<String, Integer> x;    //存储X轴
    private Map<String, Integer> y;    //存储Y轴

}
