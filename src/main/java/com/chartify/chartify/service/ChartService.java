package com.chartify.chartify.service;

import com.chartify.chartify.entity.ChartData;
import com.chartify.chartify.model.Result;

public interface ChartService {
    Result processChartData(ChartData chartData);
}
