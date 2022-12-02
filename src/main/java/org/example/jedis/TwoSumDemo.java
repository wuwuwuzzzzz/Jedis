package org.example.jedis;


import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wxz
 * @date 16:45 2022/12/1
 */
public class TwoSumDemo {

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (target - nums[i] == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(10);
        for (int i = 0; i < nums.length; i++) {
            int partnerNumber = target - nums[i];
            if (map.containsKey(partnerNumber)) {
                return new int[]{map.get(partnerNumber), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] myIndex = twoSum(nums, target);
        assert myIndex != null;
        for (int index : myIndex) {
            System.out.println(index);
        }
    }
}
