package com.example.demo;

import com.example.demo.service.RedisService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class DemoApplicationTests {
    @Autowired
    RedisService redisService;
    @Test
    void contextLoads() {

    }
    @Test
    void tests(){
        boolean aa = redisService.set("aa", 123);
        System.out.println(aa);
    }

}
