package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticController {
    //公共的跳转方法
    @RequestMapping("/href/{page}")
    public String hrefPage(@PathVariable("page") String page){
        return page;
    }
}
