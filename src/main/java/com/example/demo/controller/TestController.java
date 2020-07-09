package com.example.demo.controller;

import com.example.demo.dao.Info;
import com.example.demo.dao.UserDao;
import com.example.demo.service.RedisService;
import com.example.demo.util.JsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {
    private final UserDao userDao;
    private final RedisService redisService;
    private final Info info;

    public TestController(UserDao userDao, RedisService redisService, Info info) {
        this.userDao = userDao;
        this.redisService = redisService;
        this.info = info;
    }

   /* @RequestMapping("/sayHello")
    public String syaHello(){
        return "你好";
    }
    @RequestMapping("/findAll")
    public String findAll(){
        User all = userDao.findAll();
        System.out.println(all);
        return "ok";
    }*/

    //登陆
    /*@RequestMapping("/login")
    public String userLogin(User user){
        User userInfo = userDao.userLogin(user);
        if (null != userInfo){
            String userName = userInfo.getUserName();
            redisService.set(userName + "Key", userName, 3000);
            Object o = redisService.get(userName + "Key");
            System.out.println(o);
            return "true";
        }
        return "false";
    }

    //判断用户是否存在
    @RequestMapping("/isUser")
    public String isUser(String userName){
        User user = userDao.isUser(userName);
        if (null != user){
            return "true";
        }
        return "false";
    }*/
    //查询数据当天的
    @RequestMapping("/findAllInfo")
    public String getInfo(){
        Map<String, Object> infos =info.findInfo();
        String mid = infos.get("time_h")+"";
        String good_number_total = infos.get("good_number_total")+"";
        System.out.println(info);
        return JsonUtil.getListToJson(infos);
    }
}
