package com.yuqiqi.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和 2
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        dfs(0 , candidates , target , new boolean[candidates.length] , new LinkedList<>() , result);
        return result;
    }

    //思路 ：记录一个数组来对应是否已使用
    public void dfs(int start , int[] candidates , int target , boolean[] used,
                    LinkedList<Integer> stack , List<List<Integer>> result){
        if (target < 0){
            return;  //爆了  直接返回
        }
        if (target == 0){
            result.add(new LinkedList<>(stack));  //找到了
        }
        for (int i = start; i < candidates.length; i++) {
            if (target < candidates[i]){
                continue;  //⭐剪枝  如果目标比此次的候选数据少的话  直接返回就行了
            }
            if (i > 0 && !used[i - 1] && candidates[i - 1] == candidates[i]){
                //遇到这一个和上一个一样的话  就不用它了
                continue;  //只有上一个被访问到了的话才能继续   把这俩锁到一起   控制俩个位置不同但是值相同的元素不重复
            }
            stack.push(candidates[i]);  //固定
            used[i] = true;
            dfs(i + 1 , candidates , target - candidates[i] , used , stack , result);  //递归
            used[i] = false;
            stack.pop(); //回溯
        }
    }
}
