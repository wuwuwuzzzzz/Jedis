package org.example.jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxz
 * @date 17:08 2022/11/22
 */
public class StringDemo {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = new String("Hello");
        String str3 = str2;
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str2 == str3);
        System.out.println(str1.equals(str2));
        System.out.println(str1.equals(str3));
        System.out.println(str2.equals(str3));
    }
}
