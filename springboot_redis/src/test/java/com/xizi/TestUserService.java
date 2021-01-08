package com.xizi;


import com.xizi.pojo.User;
import com.xizi.service.UserService;

import org.apache.ibatis.cache.Cache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUserService {


    @Autowired
    private UserService userService;

    @Test
    public void testFindAll() {

        userService.findAll().forEach(u -> System.out.println("User = " + u));
        System.out.println("=================================================================");
        userService.findAll().forEach(u -> System.out.println("User = " + u));
    }

    //
    @Test
    public void testFindOne() {

        Cache cache;
        User byId = userService.findById("4");
        System.out.println(byId);
        System.out.println("==================================");
        User byId1 = userService.findById("4");
        System.out.println(byId1);

    }

    //
//
    //执行增删改 清空缓存
    @Test
    public void testDelete() {
        userService.delete("1");
    }

    //执行增删改 清空缓存
    @Test
    public void testSave() {
        User user = new User();
        user.setName("戏子学JAVA11");
        user.setAge(23);
        user.setBir(new Date());
        userService.save(user);
    }

    //执行增删改 清空缓存
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId("2").setName("小尹1").setAge(20).setBir(new Date());
        userService.update(user);
    }


}
