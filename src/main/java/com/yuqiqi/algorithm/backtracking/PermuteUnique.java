package com.yuqiqi.algorithm.backtracking;

import java.util.*;

/**
 * 不重复的全排列   ⭐回溯
 * 思路：把结果放到set里面 最后再转回来 （可以  但效率低）
 * 正常做法：⭐剪枝⭐  回溯的时候加一个条件  判断上一个是不是和自己一样 且 已访问
 */
public class PermuteUnique {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums); //⭐排个序 方便去重  如果有重复的的话 直接给他都锁上
        LinkedList<List<Integer>> result = new LinkedList<>();
        rec2(nums , new boolean[nums.length] , new LinkedList<>() , result);
        return result.stream().toList();
    }

    /**
     * 递归回溯
     * @param nums 原始数组
     * @param visited 记录是否已经被访问的数组 （和nums数组对应）
     * @param stack 栈 记录某一次的排序结果 用来回溯
     * @param result 记录最终结果 不用回溯
     */
    public void rec(int[] nums , boolean[] visited ,
                    LinkedList<Integer> stack , HashSet<List<Integer>> result){
        if (stack.size() == nums.length){
            //复制一份数据放进去
            result.add(new LinkedList<>(stack));  //到了个数之后往结果集合里面加
        }
        //遍历  发现没有被使用的数字  把它标记为已使用  然后入栈
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]){
                stack.push(nums[i]);
                visited[i] = true;  //⭐这里是重点
                rec(nums , visited , stack , result);  //递归调用
                //回溯⭐⭐
                visited[i] = false;
                stack.pop();
            }
        }
    }

    public void rec2(int[] nums , boolean[] visited ,
                    LinkedList<Integer> stack , LinkedList<List<Integer>> result){
        if (stack.size() == nums.length){
            //复制一份数据放进去
            result.add(new LinkedList<>(stack));  //到了个数之后往结果集合里面加
        }
        //遍历  发现没有被使用的数字  把它标记为已使用  然后入栈
        for (int i = 0; i < nums.length; i++) {
            //⭐⭐⭐⭐这里加一个判断   剪枝
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]){
                continue;  //已访问 并且遇到了重复的  直接跳过  （把这俩重复的数字直接固定住）
            }
            if (!visited[i]){
                stack.push(nums[i]);
                visited[i] = true;  //⭐这里是重点
                rec2(nums , visited , stack , result);  //递归调用
                //回溯⭐⭐
                visited[i] = false;
                stack.pop();
            }
        }
    }
}
