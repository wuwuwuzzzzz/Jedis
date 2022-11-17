package org.example.jedis;

import java.util.concurrent.*;

/**
 * @author wxz
 * @date 10:31 2022/11/17
 */
public class MyThreadPollDemo {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                100L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 1; i <= 8; i++) {
                final int tmpI = i;
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "号窗口，" + "服务顾客" + tmpI);
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }

    }
}
