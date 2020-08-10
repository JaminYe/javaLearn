package com.jamin.redislock;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedislockApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedislockApplication.class, args);
    }

    @Bean
    public Redisson redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.150.110").setDatabase(0);
        return ((Redisson) Redisson.create(config));
    }
}



