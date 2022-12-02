package org.example.jedis;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author wxz
 * @date 10:24 2022/12/2
 */
public class LockSupportDemo {

    static Object objectLock = new Object();

    static Lock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "come in");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "被唤醒");
            } finally {
              lock.unlock();
            }
        }, "A").start();

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t" + "通知");
            } finally {
                lock.unlock();
            }
        }, "B").start();
    }

    private static void synchronizedWaitNotify() {
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t" + "come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "被唤醒");
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t" + "通知");
            }
        }, "B").start();
    }
}
