package com.jamin.redislock.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jamin
 * @date 2020/8/1 20:33
 */
@RestController
public class RedisController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    Redisson redisson;

    @RequestMapping("redisTest")
    public String redisTest() {


/**
 * 分布式锁
 *    1.  stringRedisTemplate.opsForValue().setIfAbsent("key", "value");if (!flag){return "";}finally { stringRedisTemplate.delete("key");}   如果加锁后运维部署把程序kill了,其他线程永远进不来
 *   2. stringRedisTemplate.opsForValue().setIfAbsent("key", "value", 10, TimeUnit.SECONDS);finally { stringRedisTemplate.delete("key");}
 *   在超高并发时程序运行时间不一致,第一个线程15秒 10秒后锁自动解除程序还未运行完,第二个线程进来获得锁后第一个线程运行完把锁删除了  依次类推,释放的永远不是自己的锁
 *   3. 动态设置锁的方法String lockValue = UUID.randomUUID().toString();Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(lock, lockValue, 10, TimeUnit.SECONDS);if (!flag) { return ""; }
 *   if (lockValue.equals(stringRedisTemplate.opsForValue().get(lock))) { stringRedisTemplate.delete("key"); }, 这里还是会出现锁失效的可能
 *   4. 续命   获取锁后开一个定时器进行在锁自动释放前获取当前自己锁是否被释放 如果没有证明代码还没执行完,进行续命,若执行完删除当前定时器
 */
        String lockKey = "key";
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            //内部使用续命的方式
            redissonLock.lock();
            String countKey = "count";
            //获取库存数量
            int count = Integer.parseInt(stringRedisTemplate.opsForValue().get(countKey));
            //有库存减少
            if (count > 0) {
                count = count - 1;
                stringRedisTemplate.opsForValue().set(countKey, String.valueOf(count));
                System.out.println("扣除成功,剩余数量:" + count);
            } else {
                System.out.println("扣除失败");
            }
        } finally {
            redissonLock.unlock();
        }
        return "end";
    }
}
