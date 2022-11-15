package org.example.jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCaChe {

    private volatile Map<String, Object> map = new HashMap<>();

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "\t 正在写入");
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            rwLock.writeLock().unlock();
        }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "\t 写入完成");
    }

    public void get(String key) {
        rwLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "\t 正在读取");
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            rwLock.readLock().unlock();
        }
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName() + "\t 读取完成");
    }
}

/**
 * @author wxz
 * @date 15:28 2022/11/14
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCaChe myCaChe = new MyCaChe();

        for (int i = 1; i <= 3; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCaChe.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 3; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCaChe.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
}
