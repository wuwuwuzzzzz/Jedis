package org.example.jedis;

import java.lang.ref.SoftReference;

/**
 *
 * @author wxz
 * @date 15:59 2022/11/19
 */
public class SoftReferenceDemo {

    public static void softRefMemoryEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }

    public static void softRefMemoryNotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args) {
//        softRefMemoryEnough();
        softRefMemoryNotEnough();
    }
}
