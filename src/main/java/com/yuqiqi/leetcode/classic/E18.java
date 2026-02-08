package com.yuqiqi.leetcode.classic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 四数之和   E15有通解
 */
public class E18 {
    public static List<List<Integer>> fourSum(int[] nums , int target) {
        Arrays.sort(nums); //排个序 用双指针
        ArrayList<List<Integer>> result = new ArrayList<>();
        dfs(4 , 0 , nums.length - 1 , (long)target , nums , new LinkedList<>() , result);
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
    public static void dfs(int n , int i , int j , long target , int[] nums ,
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
    public static void twoSum(int i , int j , int[] numbers , long target ,
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

    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{2, 2, 2, 2, 2}, 8));
    }
}
