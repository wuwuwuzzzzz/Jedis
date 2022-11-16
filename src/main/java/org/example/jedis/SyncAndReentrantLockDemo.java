package org.example.jedis;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();

    private Condition c3 = lock.newCondition();

    public void print() {
        lock.lock();
        try {
            while (number != 1) {
                c1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + number);
            }
            number = 2;
            c2.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
          lock.unlock();
        }

    }
}

/**
 * 多线程之间顺序调用，实现A->B->C三个线程启动
 * AA打印5次 BB打印10次 CC打印15次
 *
 * @author wxz
 * @date 17:11 2022/11/16
 */
public class SyncAndReentrantLockDemo {

}
