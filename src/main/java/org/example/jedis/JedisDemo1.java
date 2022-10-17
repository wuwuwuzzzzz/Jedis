package org.example.jedis;

import redis.clients.jedis.Jedis;

/**
 *
 * @author wxz
 * @date 21:33 2022/10/17
 */
public class JedisDemo1 {

    public static void main(String[] args) {
        // 创建Jedis对象
        Jedis jedis = new Jedis("10.211.55.5", 6379);

        // 测试
        String value = jedis.ping();
        System.out.println(value);
    }
}
