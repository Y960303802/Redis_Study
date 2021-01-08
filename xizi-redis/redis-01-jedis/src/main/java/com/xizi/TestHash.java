package com.xizi;

import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class TestHash {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
        jedis.hmset("hash",map);


        jedis.hset("hash","key5","value5");
        System.out.println("hash的所有键值对"+jedis.hgetAll("hash"));
        System.out.println("hash的所有键"+jedis.hkeys("hash"));
        System.out.println("hash的所有值"+jedis.hvals("hash"));
        System.out.println("key6的值加上一个整数，不存在就添加key6:"+jedis.hincrBy("hash","key6",3));
        System.out.println(jedis.hgetAll("hash"));
        System.out.println("key6的值加上一个整数，不存在就添加key6:"+jedis.hincrBy("hash","key6",3));
        System.out.println(jedis.hgetAll("hash"));
        System.out.println("删除一个或者多个键值为："+jedis.hdel("hash","key2"));
        System.out.println("hash中键值对的个数"+jedis.hlen("hash"));
        System.out.println("判断是否存在key1"+jedis.exists("key1"));
        System.out.println("获取hash中的值"+jedis.hmget("hash","key3","key4"));

    }
}
