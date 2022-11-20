package org.example.jedis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wxz
 * @date 19:50 2022/11/20
 */
public class GcOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("i:" + i);
            e.printStackTrace();
            throw e;
        }
    }
}
