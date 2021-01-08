package com.xizi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

import java.util.List;

public class TestList {
    private Jedis jedis;
    @Before
    public void before(){
        this.jedis = new Jedis("192.168.153.100", 6379);
    }
    @After
    public void after(){
        jedis.close();
    }

    //测试List相关
    @Test
    public void testList(){

        //lpush
        jedis.lpush("names","戏子1","戏子2","戏子3","戏子4");

        //rpush
        jedis.rpush("names","xaioyin");

        //lrange
        List<String> names1 = jedis.lrange("names", 0, -1);
        names1.forEach(name-> System.out.println("name = " + name));

        //lpop rpop
        String names = jedis.lpop("names");
        System.out.println(names);


        //llen
        jedis.linsert("names", ListPosition.AFTER,"xiaoyin","xiaoyin");



    }
}
