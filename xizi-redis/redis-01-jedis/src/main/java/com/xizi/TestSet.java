package com.xizi;

import redis.clients.jedis.Jedis;

public class TestSet {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();
        System.out.println("===========向集合中添加元素（不重复）");
        System.out.println(jedis.sadd("eleSet","e1","e2","e3","e4","e5","e6","e7"));
        System.out.println(jedis.sadd("eleSet","e6"));
        System.out.println("elSet的所有元素为"+jedis.smembers("eleSet"));
        System.out.println("删除一个元素e0为"+jedis.srem("eleSet","e0"));
        System.out.println("elSet的所有元素为"+jedis.smembers("eleSet"));
        System.out.println("删除俩个元素e6 e7为"+jedis.srem("eleSet","e6","e7"));
        System.out.println("elSet的所有元素为"+jedis.smembers("eleSet"));
        System.out.println("随机的移除集合中的一个元素"+jedis.spop("eleSet"));
        System.out.println("随机的移除集合中的一个元素"+jedis.spop("eleSet"));
        System.out.println("elSet的所有元素为"+jedis.smembers("eleSet"));
        System.out.println("eleSet的所有元素个数为"+jedis.scard("eleSet"));
        System.out.println("e1是否在elesSet中"+jedis.sismember("eleSet","e1"));
        System.out.println("e3是否在elesSet中"+jedis.sismember("eleSet","e3"));

        System.out.println(jedis.sadd("eleSet1","e1","e2","e3","e4","e5","e6","e7"));
        System.out.println(jedis.sadd("eleSet2","e1","e2","e3","e4","e5"));
        System.out.println("将eleSet1中删除e1并存入eleSet3中"+jedis.smove("eleSet1","eleSet3","e1"));
        System.out.println("将eleSet2中删除e2并存入eleSet3中"+jedis.smove("eleSet2","eleSet3","e2"));
        System.out.println("eleSet1中的元素"+jedis.smembers("eleSet1"));
        System.out.println("eleSet2中的元素"+jedis.smembers("eleSet2"));
        System.out.println("eleSet3中的元素"+jedis.smembers("eleSet3"));
        System.out.println("===========集合运算============");
        System.out.println("eleSet1中的元素"+jedis.smembers("eleSet1"));
        System.out.println("eleSet2中的元素"+jedis.smembers("eleSet2"));
        System.out.println("交集"+jedis.sinter("eleSet1","eleSet2"));
        System.out.println("并集"+jedis.sunion("eleSet1","eleSet2"));
        System.out.println("差集"+jedis.sdiff("eleSet1","eleSet2"));

        jedis.sinterstore("eleSet4","eleSet1","eleSet2");//求交集并将交集保存到eleSet4的集合中
        System.out.println("eleSet4中的元素"+jedis.smembers("eleSet4"));
    }
}
