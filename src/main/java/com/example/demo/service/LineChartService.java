package com.example.demo.service;

import java.util.Map;



public interface LineChartService {
    //查询某机台时间线
    Map<String,Object> findNumTime(String mid, String sysTime);
    //查询良品
    Map<String,Object> findNumGood(String mid,String sysTime);
    //查询不良品
    Map<String,Object> findNumBad(String mid,String sysTime);
}
