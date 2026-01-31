package com.yuqiqi.leetcode.ds.hashtable;

import java.util.HashMap;

/**
 * 找出出现一次的数字  给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 */
public class E136 {
    //思路  使用hashmap 不存在的话加进去 存在的话拿出来  最后剩下的那个就是想要的结果
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)){
                map.remove(num);
            }else {
                map.put(num,1);
            }
        }
        return map.keySet().iterator().next();
    }

    //⭐方法二：异或   任何数与0异或都是它本身  与相同的元素异或 就是0
    public int singleNumber2(int[] nums){
        int result = 0;
        for (int num : nums) {
            result = result ^ num;
        }
        return result;
    }
}
