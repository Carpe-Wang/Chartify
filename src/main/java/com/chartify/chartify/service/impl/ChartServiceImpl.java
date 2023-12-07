package com.chartify.chartify.service.impl;

import com.chartify.chartify.entity.ChartData;
import com.chartify.chartify.service.ChartService;
import org.springframework.stereotype.Service;

@Service
public class ChartServiceImpl implements ChartService {
    @Override
    public ChartData processChartData(ChartData chartData){

        return chartData;
    }
    private Boolean validateChartData(ChartData chartData){
        if (chartData.getX().isEmpty() || chartData.getY().isEmpty()) {
            throw new IllegalArgumentException("X轴或Y轴数据不能为空");
        }
        if (chartData.getX().size() != chartData.getY().size()){
            throw new IllegalArgumentException("X轴或Y轴数据不一致");
        }
        return true;
    }
}
