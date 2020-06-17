package cn.jaminye;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;

/**
 * @author Jamin <br>
 * @date 2020/6/8 7:14<br>
 * 哨兵架构
 */
public class JedisSentinelTest {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(2000);
        jedisPoolConfig.setMaxIdle(1000);
        jedisPoolConfig.setMinIdle(5);
        String masterName = "mymaster";
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(new HostAndPort("192.168.2.123", 26379).toString());
        hashSet.add(new HostAndPort("192.168.2.123", 26380).toString());
        hashSet.add(new HostAndPort("192.168.2.123", 26381).toString());
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, hashSet, jedisPoolConfig, 3000, null);
        Jedis jedis = null;

        int i = 6001;
        while (true) {
            try {
                jedis = jedisSentinelPool.getResource();
                jedis.set("sentinel" + i, "sentinel" + i);
                System.out.println("sentinel" + i);
//                System.out.println(jedis.get("sentinel"));
                i++;
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
