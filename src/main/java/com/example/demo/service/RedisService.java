package com.example.demo.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final RedisTemplate<String,Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    //指定缓存失效时间
    public boolean expire(String key,long time){
        try {
            if (time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //获取过期时间
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    //判断Key是否存在
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //删除缓存
    @SuppressWarnings("unchecked")
    public void del(String... key){
        if (key != null && key.length>0){
            if (key.length == 1){
                redisTemplate.delete(key[0]);
            }else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //删除缓存
    @SuppressWarnings("unchecked")
    public void del(Collection keys){
        if (org.apache.commons.collections.CollectionUtils.isEmpty(keys)){
            redisTemplate.delete(keys);
        }
    }

    //普通取值
    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    //普通缓存放入
    public boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //普通放入并设置时间
    public boolean set(String key,Object value,long time){
        try {
            if (time>0){
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            }else{
                set(key,value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //递增
    public long incr(String key ,long delta){
        if (delta < 0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }

    //递减
    public long decr(String key,long delta){
        if (delta<0){
            throw new RuntimeException("递减因子要大于0");
        }
        return redisTemplate.opsForValue().decrement(key, -delta);
    }

    //hashGet
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }
    //获取hashkey对应的所有值
    public Map<Object,Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    //hashSet
    public boolean hmset(String key,Map<String,Object> map){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //hashSet并设置失效时间
    public boolean hmset(String key,Map<String,Object> map,long time){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            if (time>0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //向一张hash表中存入数据  如果不存在将创建
    public boolean hset(String key,String item,Object value){
        try {
            redisTemplate.opsForHash().put(key,item,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //向一张hash表中存入数据，如果不存在将创建，并设置生效时间
    public boolean hset(String key,String item,Object value,long time){
        try {
            redisTemplate.opsForHash().put(key,item,value);
            if (time>0){
                expire(key, time);
            }
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //删除hash表中的值
    public void hdel(String key,Object... item){
        redisTemplate.opsForHash().delete(key, item);
    }
    //删除hash表中的值
    public void hdel(String key,Collection items){
        redisTemplate.opsForHash().delete(key,items.toArray());
    }

    //判断hash表中是否有该值
    public boolean hHasKey(String key ,String item){
        return redisTemplate.opsForHash().hasKey(key,item);
    }

    //hash递增 如果不存在将会创建，并把新增的值返回
    public double hincr(String key,String item,double delta){
        if (delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForHash().increment(key,item,delta);
    }
    //hash 递减
    public double hdecr(String key,String item,double delta){
        if (delta<0){
            throw  new RuntimeException("递减因子要大于0");
        }
        return redisTemplate.opsForHash().increment(key,item,-delta);
    }

    //根据key获取set中所有值
    public Set<Object> sGet(String key){
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //根据value从一个set钟查询是否存在
    public boolean sHasKey(String key,Object value){
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //数据放入set中
    public long sSet(String key,Object... value){
        try {
            return redisTemplate.opsForSet().add(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //数据放入set缓存中
    public long sSet(String key,Collection value){
        try {
            return redisTemplate.opsForSet().add(key,value.toArray());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //数据放入set缓存并设置时间
    public long sSetAndTime(String key,long time,Object... value){
        try {
            Long count = redisTemplate.opsForSet().add(key, value);
            if (time>0){
                expire(key,time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //获取Set的长度
    public long sGetSetSize(String key){
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //移除值为value的Set
    public long setRemove(String key,Object... value){
        try {
            Long count = redisTemplate.opsForSet().remove(key, value);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //获取list的所有值 end -1 为所有
    public List<Object> lGet(String key,long start,long end){
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //获取List的长度
    public long lGetListSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //获取list得值
    public Object lgetIndex(String key,long index){
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //list放入缓存
    public boolean lSet(String key,Object value){
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //list放入缓存带时间
    public boolean lSet(String key,Object value,long time){
        try {
            redisTemplate.opsForList().rightPush(key,value);
            if (time>0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //list放入缓存
    public boolean lSet(String key,List<Object> values){
        try {
            redisTemplate.opsForList().rightPushAll(key,values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //list放入缓存
    public boolean lSet(String key,List<Object> values,long time){
        try {
            redisTemplate.opsForList().rightPushAll(key,values);
            if (time>0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //根据索引修改list中的数据
    public boolean lUpdateIndex(String key,long index,Object value){
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //移除N个值为value的list
    public long lRemove (String key,long count,Object value){
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


}
