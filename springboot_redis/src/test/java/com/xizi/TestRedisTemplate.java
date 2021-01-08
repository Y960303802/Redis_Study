package com.xizi;


import com.xizi.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedisTemplate {


    //注入RedisTemplate key Object  Value Object
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate() {

        /**
         * redisTemplate对象中 key 和 value 的序列化都是 JdkSerializationRedisSerializer
         *      key: string
         *      value: object
         *      修改默认key序列化方案 :  key  StringRedisSerializer
         */

        //修改key序列化方案   String类型序列
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //修改hashkey 序列化方案
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        User user = new User();
        user.setId(UUID.randomUUID().toString()).setName("戏子真帅").setAge(20).setBir(new Date());
        redisTemplate.opsForValue().set("user", user);//redis进行设置 对象需要经过序列化

        User user1 = (User) redisTemplate.opsForValue().get("user");
        System.out.println(user1);

        //list
        redisTemplate.opsForList().leftPush("list", user);
        System.out.println(redisTemplate.opsForList().leftPop("list"));
        //set
        redisTemplate.opsForSet().add("set", user);
        System.out.println(redisTemplate.opsForSet().pop("set"));
        //Zset
        redisTemplate.opsForZSet().add("zset", user, 10);
        System.out.println(redisTemplate.opsForZSet().getOperations());
        //hash
        redisTemplate.opsForHash().put("map", "name", user);
        System.out.println(redisTemplate.opsForHash().get("map", "name"));


    }


}
