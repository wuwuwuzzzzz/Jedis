package org.example.jedis;

/**
 *
 * @author wxz
 * @date 19:15 2022/11/20
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();
    }
}
