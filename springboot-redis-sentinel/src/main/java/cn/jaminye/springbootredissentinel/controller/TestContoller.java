package cn.jaminye.springbootredissentinel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jamin
 * @date 2020/8/1 17:13
 * 模拟tomcat高并发请求
 */
@RestController
public class TestContoller {
    int i = 200;

    @RequestMapping("test")
    public void test() {
        i = i - 1;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
}
