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
        //连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(2000);
        jedisPoolConfig.setMaxIdle(1000);
        jedisPoolConfig.setMinIdle(5);
        //master名称
        String masterName = "mymaster";
        //哨兵
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(new HostAndPort("192.168.150.100", 26379).toString());
        hashSet.add(new HostAndPort("192.168.150.100", 26380).toString());
        hashSet.add(new HostAndPort("192.168.150.100", 26381).toString());
        //创建连接池
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, hashSet, jedisPoolConfig, 3000, null);
        Jedis jedis = null;
        int i = 1;
        while (true) {
            try {
                jedis = jedisSentinelPool.getResource();
                jedis.set("sentinel" + i, "sentinel" + i);
                System.out.println("sentinel" + i);
                i++;
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
