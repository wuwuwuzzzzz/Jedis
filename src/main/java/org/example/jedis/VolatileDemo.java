package org.example.jedis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {
    AtomicInteger atomicInteger = new AtomicInteger();

    public void add() {
        atomicInteger.getAndIncrement();
    }
}

/**
 *
 * @author wxz
 * @date 15:12 2022/11/12
 */
public class VolatileDemo {

}
