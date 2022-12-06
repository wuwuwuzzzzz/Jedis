package org.example.jedis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author wxz
 * @date 11:44 2022/12/2
 */
public class AqsDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("-------- A thread come in");
                try {
                    TimeUnit.MINUTES.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } finally {
              lock.unlock();
            }
        }, "A").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("-------- B thread come in");
            } finally {
                lock.unlock();
            }
        }, "B").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("-------- C thread come in");
            } finally {
                lock.unlock();
            }
        }, "C").start();
    }
}
