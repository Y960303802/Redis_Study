package com.xizi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class TestString {
    private Jedis jedis;
    @Before
    public void before(){
        this.jedis = new Jedis("192.168.153.100", 6379);
    }
    @After
    public void after(){
        jedis.close();
    }
    //测试String相关
    @Test
    public void testString(){
        //set
        jedis.set("name","戏子");
        //get
        String s = jedis.get("name");
        System.out.println(s);
        //mset 一次性设置多个
        jedis.mset("content","戏子zzz","address","南昌市");
        //mget 一次性获取多个
        List<String> mget = jedis.mget("name", "content", "address");
        mget.forEach(v-> System.out.println("v = " + v));
//        //getset 拿到原始的值设置新的值
        String set = jedis.getSet("name", "戏子222");
        System.out.println(set);

        //............
    }
}
