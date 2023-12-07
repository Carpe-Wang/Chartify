package com.chartify.chartify.controller;


import com.chartify.chartify.entity.ChartData;
import com.chartify.chartify.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("Basic")
public class ChartController {

    @Autowired
    private ChartService chartService;
    /**
     * 通过给定的数据生成
     * @return
     */
    @PostMapping("/makeChartWithDate")
    public String makeChartWithDate(@RequestBody ChartData chartData){
        return chartService.processChartData(chartData) == null? "生成失败":"生成成功";
    }
}
