package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

public class JsonUtil {
    //将json格式的数据转换为javabean
   public static <T> T getJsonToBean(String jsonDate,Class<T> classZ){
       return JSON.parseObject(jsonDate,classZ);
   }
   //将object格式类型的数据转换成javabean
    public static <T> T  getObjectToBean(String jsonDate,Class<T> tClass){
       return JSON.parseObject(Object2String(jsonDate),tClass);
    }
    //将javabean转换成String
    public static String getBeanToString(Object object){
       return JSON.toJSONString(object);
    }
    //将制定的json数据转换成制定的java对象 返回list<T>
    public static <T>List<T> getJsonToList(String jsonDate,Class<T> classZ){
       return JSON.parseArray(jsonDate,classZ);
    }
    //把json数据转换成比较复杂的List<Map<String,Object>> 格式的数据。
    public static List<Map<String,Object>> getJsonToListMap(String jsonDate){
       return JSON.parseObject(jsonDate,new TypeReference<List<Map<String,Object>>>(){});
    }
    //List转换成Json
    public static String getListToJson(Object object){
       return JSONArray.toJSONString(object,true);
    }
    //Object转list(不需要实体类)
    public static JSONArray getListToJsonNoBean(Object object){
       return JSONArray.parseArray(Object2String(object));
    }
    //Object转Json
    public static JSONObject Object2Json(Object object){
       return JSONObject.parseObject(Object2String(object));
    }
    //Object转json字符串
    public static String Object2String(Object jsonStr){
       return JSONObject.toJSONString(jsonStr);
    }
}
