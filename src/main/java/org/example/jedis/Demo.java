package org.example.jedis;

/**
 * @author wxz
 * @date 15:14 2022/11/11
 */
public class Demo {
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
    }
}
