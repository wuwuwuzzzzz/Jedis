package org.example.jedis;

import java.util.concurrent.*;

/**
 *
 * @author wxz
 * @date 15:48 2022/11/17
 */
public class MyThreadPoolDemo2 {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        try {
            for (int i = 1; i <= 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }
}
