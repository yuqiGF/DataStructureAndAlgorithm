package com.yuqiqi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 三数之和为0
 *  给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 *  有多个答案  要去重
 *  ⭐和组合问题类似   但是限制了仅使用一次递归  用回溯dfs写会超时
 */
public class E15 {
    //思路  先固定一个数字  然后让剩下的俩个数的和等于新的两数之和 （降维⭐）   用递归来写  可以解决n数之和
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); //排个序 用双指针
        ArrayList<List<Integer>> result = new ArrayList<>();
        dfs(3 , 0 , nums.length - 1 , 0 , nums , new LinkedList<>() , result);
        return result;
    }

    /**
     * 递归求解n数之和的方法
     * @param n 几个数的和
     * @param i 起始点  （因为不会重复  所以下一次直接从后面一个的数字开始就行了）
     * @param j 结束点
     * @param target 目标数字
     * @param nums 待选数组
     * @param stack 记录单个结果
     * @param result 记录整个结果
     */
    public void dfs(int n , int i , int j , int target , int[] nums ,
                    LinkedList<Integer> stack , List<List<Integer>> result){
        if (n == 2){
            //两数之和
            twoSum(i, j, nums, target , stack , result);
            return;
        }
        for (int k = i; k < j - (n - 2); k++) {
            //检查重复
            if (k > i && nums[k] == nums[k - 1]){
                continue;
            }
            stack.push(nums[k]);  //固定第k个数字
            dfs(n - 1 , k + 1 , j , target - nums[k] , nums , stack , result);
            stack.pop();  //回溯⭐
        }
    }

    //双指针求有序的两数之和
    public void twoSum(int i , int j , int[] numbers , int target ,
                        LinkedList<Integer> stack , List<List<Integer>> result) {
        while (i < j){
            int res = numbers[i] + numbers[j];
            if (res > target){  //高了
                j --;
            }else if (res < target){
                i ++;
            }else {  //找到了 加入结果集合
                ArrayList<Integer> list = new ArrayList<>(stack);
                list.add(numbers[i]);
                list.add(numbers[j]);
                result.add(list);  //找到解了
                // ⭐缩小范围再找
                i ++;
                j --;
                //去一下重   因为已经排好序了  所以遇到了重复的就跳过
                while (i < j && numbers[i] == numbers[i - 1]){
                    i ++;
                }
                while (i < j && numbers[j] == numbers[j + 1]){
                    j --;
                }
            }
        }
    }
}
