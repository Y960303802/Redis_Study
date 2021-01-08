package com.xizi;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        //1. new Jedis 对象
        Jedis jedis = new Jedis("47.100.76.123",6379);
        //jedis 所有的命令就是我们之前学习的所有指令
        System.out.println(jedis.ping());
    }
}
