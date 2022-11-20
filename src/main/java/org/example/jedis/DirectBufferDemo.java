package org.example.jedis;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author wxz
 * @date 20:11 2022/11/20
 */
public class DirectBufferDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory:" + (sun.misc.VM.maxDirectMemory() / (double) 1024 / 1024) + "MB");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024 );
    }
}
