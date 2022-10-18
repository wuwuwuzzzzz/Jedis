package org.example.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @author wxz
 * @date 21:33 2022/10/17
 */
public class JedisDemo1Test {

    public static void main(String[] args) {
        // 创建Jedis对象
        try (Jedis jedis = new Jedis("10.211.55.5", 6379);) {
            // 测试
            String value = jedis.ping();
            System.out.println(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 操作key
     *
     * @author wxz
     * @date 09:58 2022/10/18
     */
    @Test
    public void demo1() {
        try (Jedis jedis = new Jedis("10.211.55.5", 6379)) {
            // 添加
            jedis.set("name", "lucy");
            // 获取
            System.out.println(jedis.get("name"));
            // 设置多个key-value
            jedis.mset("k1", "v1", "k2", "v2");
            List<String> get = jedis.mget("k1", "k2");
            System.out.println(get);
            Set<String> keys = jedis.keys("*");
            for (String key : keys) {
                System.out.println(key);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 操作List
     * @author wxz
     * @date 10:34 2022/10/18
     */
    @Test
    public void demo2() {
        try (Jedis jedis = new Jedis("10.211.55.5", 6379)) {
            jedis.lpush("key1", "lucy", "mary", "jack");
            List<String > values = jedis.lrange("key1", 0, -1);
            System.out.println(values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 操作Set
     * @author wxz
     * @date 10:34 2022/10/18
     */
    @Test
    public void demo3() {
        try (Jedis jedis = new Jedis("10.211.55.5", 6379)) {
            jedis.sadd("name", "lucy", "mary", "jack");
            Set<String > members = jedis.smembers("name");
            System.out.println(members);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 操作Hash
     * @author wxz
     * @date 10:34 2022/10/18
     */
    @Test
    public void demo4() {
        try (Jedis jedis = new Jedis("10.211.55.5", 6379)) {
            jedis.hset("users", "age", "20");
            String hash = jedis.hget("users", "age");
            System.out.println(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 操作zSet
     * @author wxz
     * @date 10:34 2022/10/18
     */
    @Test
    public void demo5() {
        try (Jedis jedis = new Jedis("10.211.55.5", 6379)) {
            jedis.zadd("China", 100d, "shanghai");
            List<String> zRange = jedis.zrange("China", 0, -1);
            System.out.println(zRange);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
