package org.example.jedis;

/**
 *
 * @author wxz
 * @date 20:37 2022/11/20
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 1;    ; i++) {
            System.out.println("i:" + i);
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "" + i).start();
        }
    }
}
