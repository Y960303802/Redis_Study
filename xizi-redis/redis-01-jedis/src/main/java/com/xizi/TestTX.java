package com.xizi;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTX {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        jedis.flushDB();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        jsonObject.put("name","xizi");

        //开启事务

        String result = jsonObject.toJSONString();


        Transaction multi = jedis.multi();
        try {
            int i=1/0;
            multi.set("user1",result);
            multi.set("user2",result);

            multi.exec();   //执行事务
        } catch (Exception e) {
            e.printStackTrace();
            multi.discard();// 放弃事务
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();
        }




    }
}
