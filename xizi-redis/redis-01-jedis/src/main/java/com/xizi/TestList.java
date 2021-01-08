package com.xizi;

import redis.clients.jedis.Jedis;

public class TestList {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();
        System.out.println("============添加一个List=============");
        jedis.lpush("collections","ArrayList","Vector","Stack","HashMap","WeakHashMap","LinkedHashMap");
        jedis.lpush("collections","HashSet");
        jedis.lpush("collections","HashMap");
        jedis.lpush("collections","TreeSet");
        System.out.println("collections的内容"+jedis.lrange("collections",0,-1));
        System.out.println("collection0~3区间的内容"+jedis.lrange("collections",0,3));

        System.out.println("========================");
        //删除列表指定的值，第二个参数为删除的个数（重复时），后add进去的值先被删，类似于出栈
        System.out.println("删除指定元素的个数"+jedis.lrem("collections",2,"HashMap"));
        System.out.println("collections的内容"+jedis.lrange("collections",0,-1));
        System.out.println("删除下表0-3区间之外的元素"+jedis.ltrim("collections",0,3));
        System.out.println("collections的内容"+jedis.lrange("collections",0,-1));
        System.out.println("collections列表出栈(左)"+jedis.lpop("collections"));
        System.out.println("collections的内容"+jedis.lrange("collections",0,-1));
        System.out.println("collections添加元素，从列表右端与lpush相对应"+jedis.rpush("collections","EnumMap"));
        System.out.println("collections的内容"+jedis.lrange("collections",0,-1));
        System.out.println("collections列表出栈(右)"+jedis.rpop("collections"));
        System.out.println("collections的内容"+jedis.lrange("collections",0,-1));
        System.out.println("修改collections指定下标1的内容"+jedis.lset("collections",1,"LinkedArrayList"));
        System.out.println("collections的内容"+jedis.lrange("collections",0,-1));

        System.out.println("collections的长度"+jedis.llen("collections"));
        System.out.println("获取collections小标为2的元素"+jedis.lindex("collections",2));

        System.out.println("=====================");
        jedis.lpush("sortedList","3","6","2","0","7","4");
        System.out.println("sortedList排序前"+jedis.lrange("sortedList",0,-1));
        System.out.println(jedis.sort("sortedList"));
        System.out.println("sortedList排序后"+jedis.lrange("sortedList",0,-1));

    }


}
