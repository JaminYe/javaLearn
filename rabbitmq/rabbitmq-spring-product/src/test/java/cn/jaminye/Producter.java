package cn.jaminye;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jaminye
 * @date 2021/7/17 上午11:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class Producter {
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * hello world
     */
    @Test
    public void testHelloWorld() {
        rabbitTemplate.convertAndSend("spring_queue", "hello world spring....");
    }

    /**
     * 广播模式
     */
    @Test
    public void fanout() {
        rabbitTemplate.convertAndSend("spring_fanout_exchange", "", "hello world spring....");
    }

    /**
     * 路由模式
     *
     * @author jaminye
     * @date 2021/7/17 下午3:11
     */
    @Test
    public void direct() {
        rabbitTemplate.convertAndSend("spring_direct_exchange", "key1", "key1消息1");
        rabbitTemplate.convertAndSend("spring_direct_exchange", "key1", "key1消息2");
        rabbitTemplate.convertAndSend("spring_direct_exchange", "key2", "key2消息1");
    }

    /**
     * 主题模式
     *
     * @author jaminye
     * @date 2021/7/17 下午3:11
     */
    @Test
    public void topic() {
        Map<String, String> map = new HashMap<String, String>(12);
        map.put("china.anhui.hefei.weather", "合肥的天气情况");
        map.put("china.anhui.wuhu.weather", "芜湖的天气情况");
        map.put("china.jiangsu.nanjing.weather", "南京的天气情况");
        map.put("china.jiangsu.suzhou.weather", "苏州的天气情况");
        map.put("china.anhui.hefei.news", "合肥的新闻情况");
        map.put("china.jiangsu.nanjing.news", "南京的天气情况");
        map.forEach((key, value) -> {
            rabbitTemplate.convertAndSend("spring_topic_exchange", key, value);
        });
    }
}
