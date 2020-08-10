package cn.jaminye;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;

/**
 * @author Jamin <br>
 * @date 2020/6/16 21:44<br>
 * 集群架构
 */
public class JedisClusterTest {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(5);
// 最大空闲数量
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(20);
//创建集群节点
        HashSet<HostAndPort> jedisClusterNode = new HashSet<>();
        jedisClusterNode.add(new HostAndPort("192.168.150.101", 8001));
        jedisClusterNode.add(new HostAndPort("192.168.150.102", 8002));
        jedisClusterNode.add(new HostAndPort("192.168.150.103", 8003));
        jedisClusterNode.add(new HostAndPort("192.168.150.101", 8004));
        jedisClusterNode.add(new HostAndPort("192.168.150.102", 8005));
        jedisClusterNode.add(new HostAndPort("192.168.150.103", 8006));
        JedisCluster cluster = null;
        try {
            // 第一个5000   连接超时时间 第二个5000 等待返回超时时间 10 最大尝试连接次数   jamin密码
            cluster = new JedisCluster(jedisClusterNode, 5000, 5000, 10, "jamin", jedisPoolConfig);
            System.out.println(cluster.set("test11", "111"));
            System.out.println(cluster.get("test11"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
