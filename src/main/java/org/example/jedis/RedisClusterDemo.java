package org.example.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * Redis集群设置
 *
 * @author wxz
 * @date 09:19 2022/10/20
 */
public class RedisClusterDemo {

    public static void main(String[] args) {
        // 创建对象
        HostAndPort hostAndPort = new HostAndPort("10.211.55.5", 6379);
        JedisCluster jedisCluster = new JedisCluster(hostAndPort);

        // 进行操作
        jedisCluster.set("b1", "value1");
        String b1 = jedisCluster.get("b1");
        System.out.println(b1);
        jedisCluster.close();
    }
}
