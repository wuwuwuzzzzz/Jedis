package org.example.jedis;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author wxz
 * @date 09:38 2022/12/2
 */
public class ReEnterLockDemo {

    static Object objectLockA = new Object();

    static Lock lock = new ReentrantLock();

    public static void m1() {
        new Thread(() -> {
            synchronized (objectLockA) {
                System.out.println(Thread.currentThread().getName() + "\t" + "外层调用");
                synchronized (objectLockA) {
                    System.out.println(Thread.currentThread().getName() + "\t" + "中层调用");
                    synchronized (objectLockA) {
                        System.out.println(Thread.currentThread().getName() + "\t" + "内层调用");
                    }
                }
            }
        }, "t1").start();
    }

    public static void main(String[] args) {
//        m1();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("外层");
                lock.lock();
                try {
                    System.out.println("内层");
                } finally {
                  lock.unlock();
                }

            } finally {
              lock.unlock();
            }
            
        }, "t2").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "调用开始");
            } finally {
                lock.unlock();
            }

        }, "t3").start();

    }
}
