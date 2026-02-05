package com.yuqiqi.leetcode;

import java.util.HashMap;

/**
 * 两数之和2   输入的是有序数组   ⭐利用有序性  使用双指针查找
 *  * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 *  * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 *  * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 *  * 你所设计的解决方案必须只使用常量级的额外空间。⭐⭐O（1）
 */
public class E167 {
    //这个是和E01一样的哈希表解法   但是空间复杂度最坏的情况下是O(n)
    public int[] twoSum1(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])){
                return new int[]{map.get(target - numbers[i])  + 1 , i + 1};
            }else {
                map.put(numbers[i] , i);  //放它自己进去
            }
        }
        return new int[]{};
    }

    //解法二  利用拍好了序的特性  使用双指针
    public int[] twoSum(int[] numbers , int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j){
            int res = numbers[i] + numbers[j];
            if (res > target){  //高了
                j --;
            }else if (res < target){
                i ++;
            }else {
                return new int[]{i + 1, j + 1};   //因为它的索引是从1开始算的
            }
        }
        return new int[]{};
    }

}
