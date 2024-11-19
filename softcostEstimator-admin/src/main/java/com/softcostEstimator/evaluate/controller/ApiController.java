package com.softcostEstimator.evaluate.controller;

import com.softcostEstimator.evaluate.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.softcostEstimator.evaluate.aiTry.BigModelNew;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


@RestController
@RequestMapping("/evaluate/analysis/api")
public class ApiController {
    double[] frontView={};

    @Autowired
    private ApiService apiService;

    @PostMapping("/process")
    public String processInput(@RequestBody String userInput) {
        return apiService.callApi(userInput);
    }

    @PreAuthorize("@ss.hasPermi('api:edit')")
    @CrossOrigin
    @PostMapping("/edit")
    public ResponseEntity<String> receiveData(@RequestBody String payload) throws Exception {
        System.out.println("Received data: " + payload);
        BigModelNew bigModelNew=new BigModelNew("111",false);
        bigModelNew.getFrontText(payload);
        Thread.sleep(6000);
        frontView= BigModelNew.values1;
//        System.out.println(bigModelNew.values1[0]);
        return ResponseEntity.ok("Data received successfully");
    }

    @CrossOrigin
    @GetMapping("/new")
    public double[] getData() throws Exception {
        return this.frontView;
    }



}