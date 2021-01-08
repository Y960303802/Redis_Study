package com.xizi;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class TestRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.153.100",6379);

        //选择使用一个库
        jedis.select(0);

        //获取redis中所有的key信息
        Set<String> keys = jedis.keys("*");
        keys.forEach(key-> System.out.println(key));

        //操作库相关
        jedis.flushDB(); //清空当前库
        jedis.flushAll(); //清空所有库

        //释放资源
        jedis.close();
    }
}
