package com.example.demo.controller;

import com.example.demo.entity.LineChartVo;
import com.example.demo.service.Impl.LineChartServiceImpl;
import com.example.demo.service.LineChartService;
import com.example.demo.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LineChartController {
    private final LineChartService lineChartService;
    public LineChartController(LineChartService lineChartService){
        this.lineChartService = lineChartService;
    }
    //获取折线图的数据
    @GetMapping("/findLineChart")
    public String findLineChart(@RequestParam("mid") String mid,@RequestParam("sysTime") String sysTime){
        //获取数据的X轴数据
        Map<String, Object> numTime = lineChartService.findNumTime(mid, sysTime);
        //获取Y轴数据
        Map<String, Object> numGood = lineChartService.findNumGood(mid, sysTime);
        Map<String, Object> numBad = lineChartService.findNumBad(mid, sysTime);
        //数据返回封装
        LineChartVo lineChartVo = new LineChartVo();
        if (numBad.size()<=0 || numGood.size()<=0 || numTime.size()<=0){
            lineChartVo.setCode(201).setMsg("数据不完整，请确认检索条件");
        }else {
            lineChartVo.setTime(numTime)
                    .setGood(numGood)
                    .setBad(numBad)
                    .setCode(200)
                    .setMsg("数据查询成功");
        }
        return JsonUtil.getBeanToString(lineChartVo);
    }
}
