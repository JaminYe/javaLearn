package cn.jaminye.xxljobdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class XxlJobDemoApplication {
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(XxlJobDemoApplication.class, args);
		//让程序不结束
		new CountDownLatch(1).await();
	}
}
