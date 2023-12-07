package com.chartify.chartify.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartData {
    private Map<Integer, Integer> chartDate;    //用Map来映射X和Y轴
    private String xAxisName;// x轴的Name
    private String yAxisName;// y轴的Name
    private String title;
    // TODO: 2023/12/7  加上一个枚举，用来判断图的形式，比如线形图，柱状图。
}
