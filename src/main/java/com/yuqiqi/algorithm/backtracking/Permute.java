package com.yuqiqi.algorithm.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列  力扣46  ⭐回溯思想
 */
public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        rec(nums , new boolean[nums.length] , new LinkedList<>() , result);
        return result;
    }

    /**
     * 递归回溯
     * @param nums 原始数组
     * @param visited 记录是否已经被访问的数组 （和nums数组对应）
     * @param stack 栈 记录某一次的排序结果 用来回溯
     * @param result 记录最终结果 不用回溯
     */
    public void rec(int[] nums , boolean[] visited ,
                    LinkedList<Integer> stack , LinkedList<List<Integer>> result){
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
}
