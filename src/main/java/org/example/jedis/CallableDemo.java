package org.example.jedis;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("come in Callable");
        return 1024;
    }
}

/**
 *
 * @author wxz
 * @date 22:31 2022/11/16
 */
public class CallableDemo {
    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        Thread t1 = new Thread(futureTask, "AAA");
        t1.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
