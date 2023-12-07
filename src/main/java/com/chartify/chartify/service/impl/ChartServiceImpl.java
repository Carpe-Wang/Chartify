package com.chartify.chartify.service.impl;

import com.chartify.chartify.entity.ChartData;
import com.chartify.chartify.model.Result;
import com.chartify.chartify.service.ChartService;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.springframework.stereotype.Service;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Service
public class ChartServiceImpl implements ChartService {
    @Override
    public Result processChartData(ChartData chartData){
        Result result = validateChartData(chartData);
        if (!result.getIsSuccess()) {
            throw new IllegalArgumentException(result.getMessage());
        }
        XYDataset dataset = createDataset(chartData);

        return new Result<>(true,"生成成功",dataset);
    }

    private Result validateChartData(ChartData chartData){
        if (chartData.getX().isEmpty() || chartData.getY().isEmpty()) {
            return new Result<>(false,"X轴或Y轴数据不能为空");
        }
        if (chartData.getX().size() != chartData.getY().size()){
            return new Result<>(false,"X轴或Y轴数据不一致");
        }
        return new Result<>(true,"校验通过");
    }

    public Result generateChart(ChartData chartData) {
        XYDataset dataset = createDataset(chartData);
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Sample Chart",
                "X-Axis",
                "Y-Axis",
                dataset);

        // 图表样式定制...
        customizeChart(chart);
        return convertChartToBase64Image(chart);
    }
    private void customizeChart(JFreeChart chart) {
        // 自定义样式
        // 例如，改变线条颜色、形状、背景等
        XYPlot plot = chart.getXYPlot();

        // 设置线条颜色和样式
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE); // 设置系列颜色
        renderer.setSeriesStroke(0, new BasicStroke(2.0f)); // 设置线条粗细

        // 设置背景颜色
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRenderer(renderer);

        // 设置轴标签的字体以改善可读性
        chart.getXYPlot().getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 12));
        chart.getXYPlot().getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 12));
    }

    private XYDataset createDataset(ChartData chartData) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Series1");

        for (Map.Entry<String, Integer> entry : chartData.getX().entrySet()) {
            series.add(entry.getValue(), chartData.getY().get(entry.getKey()));
        }
        dataset.addSeries(series);
        return dataset;
    }
    private Result convertChartToBase64Image(JFreeChart chart) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ChartUtils.writeChartAsPNG(baos, chart, 500, 300);
            return new Result<>(true,"生成成功"
                    ,Base64.getEncoder().encodeToString(baos.toByteArray()));
        } catch (IOException e) {
            return new Result<>(false,"生成失败，具体原因如下:\n" + e.getMessage());
        }
    }
}
