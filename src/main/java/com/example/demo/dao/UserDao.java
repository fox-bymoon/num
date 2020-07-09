package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("select * from user")
    User findAll();
    //模拟登陆
    @Select("Select * from user where user_name = #{userName} and password = #{password} limit 1")
    User userLogin(User user);
    //判断用户是否存在
    @Select("select * from user where user_name = #{userName}")
    User isUser(String userName);
}
