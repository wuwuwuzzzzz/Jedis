package org.example.jedis;

/**
 *
 * @author wxz
 * @date 16:35 2022/11/11
 */
public class Singleton {

    private static volatile Singleton instance = null;

    private Singleton() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法Singleton()");
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Singleton.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
