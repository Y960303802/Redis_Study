package itcast.jedis.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

public class JedisTest {


    @Test
    public void test1(){
        //1.获取连接
        //空参默认localhost 端口号6379
        Jedis jedis=new Jedis();
     //   Jedis jedis=new Jedis("localhost",6379);
        //2.操作
        jedis.set("username","zhangsan");
        String username=jedis.get("username");
        System.out.println(username);

        //使用setex()方法储存可以指定过期时间的 key value
        jedis.setex("activecode",20,"hehe");
        //将activecode ,hehe键值对存入redis,并且20秒后自动删除该键值对



        //3.关闭连接
        jedis.close();

    }
    /*
   hash 数据结构操作
     */
    @Test
    public void test2(){
        //1.获取连接
        //空参默认localhost 端口号6379
        Jedis jedis=new Jedis();
        //   Jedis jedis=new Jedis("localhost",6379);
        //2.操作
        //存储hash
        jedis.hset("user","name","lisi");
        jedis.hset("user","age","23");
        jedis.hset("user","gender","male");
        //获取hash
        String name=jedis.hget("user","name");
        System.out.println(name);
        //获取hash的所有map中的数据
        Map<String,String> user=jedis.hgetAll("user");
        //keyset
        Set<String> keyset=user.keySet();
        for (String key:keyset){
            //获取value
            String value =user.get(key);
            System.out.println(key+":" +value);
        }

        //3.关闭连接
        jedis.close();
    }


}
