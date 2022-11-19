package org.example.jedis;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 *
 * @author wxz
 * @date 17:53 2022/11/19
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("=================");
        myWeakHashMap();
    }

    public static void myHashMap() {
        HashMap<Integer, String> map = new HashMap<>(10);
        Integer key = new Integer(1);
        String value = "HashMap";
        map.put(key, value);
        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map + "\t" + map.size());
    }

    public static void myWeakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>(10);
        Integer key = new Integer(2);
        String value = "WeakHashMap";
        map.put(key, value);
        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map + "\t" + map.size());
    }
}
