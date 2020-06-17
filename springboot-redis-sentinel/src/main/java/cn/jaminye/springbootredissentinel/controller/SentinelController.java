package cn.jaminye.springbootredissentinel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Jamin <br>
 * @date 2020/6/8 22:56<br>
 */
@RestController
public class SentinelController {

    @Autowired
    StringRedisTemplate stringRedisTemplates;
    @Autowired
    RedisTemplate redisTemplate;
    private static final Logger logger = LoggerFactory.getLogger(SentinelController.class);

    @RequestMapping("test")
    public void test(){
        int i = 1;
       while (true) {
        try {
               stringRedisTemplates.opsForValue().set("test" + i, i + "");
            // stringRedisTemplates.opsForValue().set("测试1111", "测试2");
            // stringRedisTemplates.opsForValue().set("test1234567", "测试2");
            System.out.println("key:test" + i);
            i++;
            Thread.sleep(3000);
        } catch (Exception e) {

           }

        }
    }
}
