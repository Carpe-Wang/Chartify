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

    // TODO: 2023/12/7  加上一个枚举，用来判断图的形式，比如线形图，柱状图。

}
