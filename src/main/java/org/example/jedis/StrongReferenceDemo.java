package org.example.jedis;

/**
 *
 * @author wxz
 * @date 14:03 2022/11/19
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = o1;
        o1 = null;
        System.gc();
        System.out.println(o2);
    }
}
