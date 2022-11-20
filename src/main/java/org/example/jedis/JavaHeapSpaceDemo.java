package org.example.jedis;

import java.util.Random;

/**
 *
 * @author wxz
 * @date 19:30 2022/11/20
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        String str = "wxz";
        while (true) {
            str += str + new Random().nextInt(111111) + new Random().nextInt(22222);
            str.intern();
        }
    }
}
