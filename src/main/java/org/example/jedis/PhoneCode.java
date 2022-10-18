package org.example.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * 模拟手机发送验证码功能 1 随机6位数 2分钟内有效 2 每天只能输入3次
 *
 * @author wxz
 * @date 10:50 2022/10/18
 */
public class PhoneCode {

    public static void main(String[] args) {
        // 模拟验证码发送
        verifyCode("13516276502");
//        getRedisCode("13516276502", "383547");
    }

    /**
     * 生成6位数验证码
     *
     * @return java.lang.String
     * @author wxz
     * @date 10:52 2022/10/18
     */
    public static String getCode() {
        // (int)((Math.random()*9+1)*100000) 简便方法
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code.append(rand);
        }
        return String.valueOf(code);
    }


    /**
     * 每个手机只能发三次 设置过期时间
     *
     * @param phone 手机号
     * @author wxz
     * @date 11:04 2022/10/18
     */
    public static void verifyCode(String phone) {
        try (Jedis jedis = new Jedis("10.211.55.5", 6379)) {
            // 手机发送次数key
            String countKey = "VerifyCode" + phone + ":count";
            // 验证码key
            String codeKey = "VerifyCode" + phone + ":code";
            // 每个手机只能发送三次
            String count = jedis.get(countKey);
            if (count == null) {
                // 没有发送
                jedis.setex(countKey, 24*60*60, "1");
            } else if (Integer.parseInt(count) <= 2) {
                // 发送次数 + 1
                jedis.incr(countKey);
            } else if (Integer.parseInt(count) > 2) {
                // 发送三次， 不能再发送
                System.out.println("发送上限了！！！");
                jedis.close();
                return;
            }

            // 发送验证码放到redis里面
            String vCode = getCode();
            jedis.setex(codeKey, 120, vCode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 验证码校验
     * @author wxz
     * @date 11:16 2022/10/18
     * @param phone 手机号
     * @param code 验证码
     */
    public static void getRedisCode(String phone, String code) {
        try (Jedis jedis = new Jedis("10.211.55.5", 6379)) {
            String codeKey = "VerifyCode" + phone + ":code";
            String redisCode = jedis.get(codeKey);
            if (redisCode.equals(code)) {
                System.out.println("成功");
            } else {
                System.out.println("失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
