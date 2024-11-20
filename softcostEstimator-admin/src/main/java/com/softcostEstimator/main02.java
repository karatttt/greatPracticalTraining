package com.softcostEstimator;

import com.softcostEstimator.evaluate.utils.ChartUtil;

import java.util.concurrent.atomic.AtomicReference;

public class main02 {
    public static void main(String[] args) throws InterruptedException{
        String json = "{ \"Category A\": 81, \"Category B\": 20, \"Category C\": 50,\"AB\": 100}";
        AtomicReference<String> path = new AtomicReference<>();
        ChartUtil.generateChart(json, path::set);
        Thread.sleep(6000L);
        System.out.println(path.get());
    }
}
