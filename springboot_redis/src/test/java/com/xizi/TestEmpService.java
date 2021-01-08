package com.xizi;


import com.xizi.service.EmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEmpService {

    @Autowired
    private EmpService empService;

    @Test
    public void test() {
        empService.findAll().forEach(emp -> System.out.println(emp));
        System.out.println("===============================");
        empService.findAll().forEach(emp -> System.out.println(emp));
    }

    @Test
    public void testmd5() {
        String key = "-1306491699:2928403212:com.xizi.dao.EmpDao.findAll:0:2147483647:select id,name from emp:SqlSessionFactoryBean";
        //利用spring框架提供md5工具类
        String s = DigestUtils.md5DigestAsHex(key.getBytes());
        //32位 16进制字符串
        System.out.println(s); //b0dc350a29902d209d98514a54dbac5f

    }
}
