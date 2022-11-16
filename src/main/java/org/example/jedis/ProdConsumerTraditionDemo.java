package org.example.jedis;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareDate {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        } finally {
          lock.unlock();
        }
    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

/**
 *
 * @author wxz
 * @date 16:17 2022/11/16
 */
public class ProdConsumerTraditionDemo {
    public static void main(String[] args) {
        ShareDate shareDate = new ShareDate();
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                try {
                    shareDate.increment();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                try {
                    shareDate.decrement();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, "BB").start();
    }
}
