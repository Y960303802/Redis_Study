package com.xizi;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;


//启动springboot应用
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestStringRedisTemplate {


    //注入StringRedisTemplate
    @Autowired
    private StringRedisTemplate stringRedisTemplate;  //key  value 都是字符串


    //操作redis中key相关
    @Test
    public void testKey() {

        //删除一个key
        stringRedisTemplate.delete("name");
        stringRedisTemplate.opsForValue().set("name", "放假");
        //判断某个key是否存在
        Boolean hasKey = stringRedisTemplate.hasKey("name");
        System.out.println(hasKey);

        //判断key所对应值的类型
        DataType name = stringRedisTemplate.type("name");
        System.out.println(name);

        //获取redis中所有key
        Set<String> keys = stringRedisTemplate.keys("*");
        keys.forEach(key -> {
            System.out.print("  key = " + key);
        });

        stringRedisTemplate.opsForValue().set("age", "20", 20, TimeUnit.SECONDS);
        //获取key超时时间 -1 永不超时  -2  key不存在  >=0 过期时间
        Long expire = stringRedisTemplate.getExpire("age");
        System.out.println(expire);

        //在redis中随机获取一个key
        stringRedisTemplate.randomKey();

        //修改key名字 要求key必须存在 不存在 报错
        stringRedisTemplate.rename("age", "age1");

        //修改key名字  判断key是否存在
        stringRedisTemplate.renameIfAbsent("name", "name1");

        //移动key到指定库
        stringRedisTemplate.move("name1", 2);
    }

    //操作redis中字符串 opsForValue 实际操作就是redis中String类型
    @Test
    public void testString() {
        stringRedisTemplate.opsForValue().set("name", "小尹"); //set 用来设置一个key value

        String value = stringRedisTemplate.opsForValue().get("name"); //用来获取一个key对应value
        System.out.println("value = " + value);

        stringRedisTemplate.opsForValue().set("code", "2357", 120, TimeUnit.SECONDS);//设置一个key 超时时间

        stringRedisTemplate.opsForValue().append("name", "他是是一个好人,单纯少年!");//追加

    }

    //操作redis中list类型   opsForList 实际操作就是redis中list类型
    @Test
    public void testList() {
        //创建一个列表  并放入一个元素
        stringRedisTemplate.opsForList().leftPush("names", "小明");
        //创建一个列表 放入多个元素
        stringRedisTemplate.opsForList().leftPushAll("names", "小尹", "小肖", "小王");
        List<String> names = new ArrayList<>();
        names.add("小明");
        names.add("小黑");
        stringRedisTemplate.opsForList().leftPushAll("nameAll", names);//创建一个列表 放入多个元素

        List<String> stringList = stringRedisTemplate.opsForList().range("names", 0, -1); //遍历list
        stringList.forEach(value -> System.out.println("value = " + value));

        stringRedisTemplate.opsForList().trim("names", 1, 3); //截取指定区间的list
    }

    //操作redis中set类型   opsForSet 实际操作就是redis中set类型
    @Test
    public void testSet() {
        //创建set 并放入多个元素
        stringRedisTemplate.opsForSet().add("sets", "小明", "小黑", "小尹", "小杨");
        //查看set中成员
        Set<String> sets = stringRedisTemplate.opsForSet().members("sets");
        sets.forEach(value -> System.out.println("value = " + value));
        //获取set集合元素个数
        Long size = stringRedisTemplate.opsForSet().size("sets");
        System.out.println("size = " + size);
    }

    //操作redis中Zset类型   opsForZSet 实际操作就是redis中Zset类型
    @Test
    public void testZset() {
        stringRedisTemplate.opsForZSet().add("zsets", "小黑", 20);//创建并放入元素

        Set<String> zsets = stringRedisTemplate.opsForZSet().range("zsets", 0, -1);//指定范围查询

        zsets.forEach(value -> System.out.println(value));
        System.out.println("=====================================");
        Set<ZSetOperations.TypedTuple<String>> zsets1 = stringRedisTemplate.opsForZSet().rangeByScoreWithScores("zsets", 0, 1000);//获取指定元素以及分数

        zsets1.forEach(typedTuple -> {
            System.out.println(typedTuple.getValue());
            System.out.println(typedTuple.getScore());
        });

    }

    //操作redis中Hash类型   opsForHash 实际操作就是redis中Hash类型
    @Test
    public void testHash() {

        //创建一个hash类型 并放入key value
        stringRedisTemplate.opsForHash().put("maps", "name", "戏子");

        Map<String, String> map = new HashMap<>();
        map.put("age", "12");
        map.put("bir", "2012-12-12");
        //放入多个key value
        stringRedisTemplate.opsForHash().putAll("maps", map);

        //获取多个key的value
        List<Object> values = stringRedisTemplate.opsForHash().multiGet("maps", Arrays.asList("name", "age"));
        values.forEach(value -> System.out.println(value));
        //获取hash中某个key的值
        String value = (String) stringRedisTemplate.opsForHash().get("maps", "name");
        System.out.println(value);
        //获取所有values
        List<Object> vals = stringRedisTemplate.opsForHash().values("maps");

        //获取所有keys
        Set<Object> keys = stringRedisTemplate.opsForHash().keys("maps");


    }


}
