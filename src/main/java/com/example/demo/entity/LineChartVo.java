package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LineChartVo {
    private Map<String,Object> time;
    private Map<String,Object> good;
    private Map<String,Object> bad;
    private String msg;
    private Integer code;
}
