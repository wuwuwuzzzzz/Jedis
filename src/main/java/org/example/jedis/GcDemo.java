package org.example.jedis;

/**
 *
 * @author wxz
 * @date 16:13 2022/11/18
 */
public class GcDemo {
    public static void main(String[] args) {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "(字节)" + (totalMemory / (double) 1024 / 1024) + "MB");
        System.out.println("MAX_MEMORY(-Xms) = " + maxMemory + "(字节)" + (maxMemory / (double) 1024 / 1024) + "MB");
    }
}
