package com.example.demo.service.Impl;

import com.example.demo.dao.LineChart;
import com.example.demo.service.LineChartService;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class LineChartServiceImpl implements LineChartService {

    private final LineChart lineChart;

    public LineChartServiceImpl(LineChart lineChart) {
        this.lineChart = lineChart;
    }

    @Override
    public Map<String, Object> findNumTime(String mid, String sysTime) {
        return lineChart.findNumTime(mid,sysTime);
    }

    @Override
    public Map<String, Object> findNumGood(String mid, String sysTime) {
        return lineChart.findNumGood(mid,sysTime);
    }

    @Override
    public Map<String, Object> findNumBad(String mid, String sysTime) {
        return lineChart.findNumBad(mid,sysTime);
    }
}
