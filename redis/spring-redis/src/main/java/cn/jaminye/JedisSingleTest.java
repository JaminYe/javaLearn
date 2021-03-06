package cn.jaminye;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Jamin <br>
 * @date 2020/6/8 6:19<br>
 * 单机连接
 */
public class JedisSingleTest {
    public static void main(String[] args) {

        // 连接池配置文件
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);
        // 创建连接池 ,配置文件,ip,端口,超时,密码
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.150.100", 6379, 3000, null);
        Jedis jedis = null;
        try {
            //获取连接
            jedis = jedisPool.getResource();
            System.out.println(jedis.set("jamin", "666"));
            System.out.println(jedis.get("jamin"));
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
