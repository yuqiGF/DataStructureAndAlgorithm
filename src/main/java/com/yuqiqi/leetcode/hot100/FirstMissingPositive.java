package com.yuqiqi.leetcode.hot100;

import java.util.HashMap;
import java.util.HashSet;

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        //全塞哈希表里然后查   ⭐但是空间复杂度是n
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int i = 1;
        while (true){
            if (!set.contains(i)){
                return i;
            }
            i ++;
        }
    }
    //思路二  原地置换  把每个元素放到该去的位置上去  （只考虑能坐到座位上的）
    public int firstMissingPositive2(int[] nums){
        int n = nums.length;
        //把可以做到有限座位上的人都换到正确位置
        for (int i = 0; i < nums.length; i++) {
            //⭐在座位范围里面  但是没有在正确的座位位置上
            while (nums[i] >= 1 && nums[i] < n && nums[i] != nums[nums[i] - 1]){  //例 学号 1 在0 的位置
                int j = nums[i] - 1; //当前血好该在的位置
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;  //一直换 换到是了为止
            }
        }

        //检查  第一个不在自己座位上的同学的座位就是答案
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i){
                return i + 1;
            }
        }
        return n + 1;
    }
}
