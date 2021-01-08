package com.xizi;

import com.xizi.config.RedisSessionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
public class RedisSessionManagerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RedisSessionManagerApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return   builder.sources(RedisSessionManagerApplication.class);
    }
}
