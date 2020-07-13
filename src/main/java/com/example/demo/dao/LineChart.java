package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

//折线图统一方法
@Mapper
public interface LineChart {
    //查询某机台时间线
    Map<String,Object> findNumTime(String mid,String sysTime);
    //查询良品
    Map<String,Object> findNumGood(String mid,String sysTime);
    //查询不良品
    Map<String,Object> findNumBad(String mid,String sysTime);
}
