package com.softcostEstimator.evaluate.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class ChartUtil {
    public static void generateChart(String jsonData, Consumer<String> callback){
        AtomicReference<String> outputPath = new AtomicReference<>();
        SwingUtilities.invokeLater(() -> {
        try {

            // 解析 JSON 数据
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Integer> data = mapper.readValue(jsonData, Map.class);
            String[] subJsons = jsonData.split("\n");
            String subJson = subJsons[0];
            String[] titleJsons = subJson.split(" ");
            String title = "test";
            // 创建饼图数据集
            DefaultPieDataset dataset = new DefaultPieDataset();
            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                dataset.setValue(entry.getKey(), entry.getValue());
            }

            // 创建饼状图
            JFreeChart pieChart = ChartFactory.createPieChart(
                    title,    // 图表标题
                    dataset,                   // 数据集
                    true,                      // 是否显示图例
                    true,                      // 是否生成工具提示
                    false                      // 是否生成 URL
            );

            // 美化饼状图
            PiePlot plot = (PiePlot) pieChart.getPlot();
            Random random = new Random();
            for(Map.Entry<String,Integer> entry:data.entrySet()){
                plot.setSectionPaint(entry.getKey(), new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255))); // 自定义颜色
            }

            // 显示图表
            JFrame frame = new JFrame("Pie Chart Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new ChartPanel(pieChart));
            frame.pack();
            frame.setVisible(true);
            // 保存饼状图到本地
            String path = "C:\\Users\\86173\\Desktop\\shixun\\greatPracticalTraining\\img\\"+title+".png";
            File outputFile = new File(path);
            outputPath.set(path);
            ChartUtils.saveChartAsPNG(outputFile, pieChart, 800, 600); // 指定宽高
            callback.accept(outputPath.get());

        } catch (Exception e) {
            e.printStackTrace();
        }});

    }
}
