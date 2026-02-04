package com.yuqiqi.algorithm.backtracking;

import org.xml.sax.helpers.AttributeListImpl;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 组合总和  力扣39    回溯思想⭐   （问题背景和零钱兑换一样  但是它要具体的组合  只能回溯了  如果问最多或者最少 可以用动态规划）
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //思路：回溯思想
        List<List<Integer>> result = new LinkedList<>();
//        Arrays.sort(candidates);  //先排个序
//        rec(candidates , target , new LinkedList<>() , result);
        dfs(0 , candidates , target , new LinkedList<>() , result);
        return result;
    }

    public void rec(int[] candidates , int target ,
                    LinkedList<Integer> stack , List<List<Integer>> result){
        if (!stack.isEmpty()){
            int sum = 0;
            for (Integer i : stack) {
                sum += i;
            }
            if (sum == target){
                ArrayList<Integer> n = new ArrayList<>(stack);  //如果直接修改栈的话会影响回溯⭐
                Collections.sort(n);
                if (result.contains(n)){  //如果包含了排序后的结果  直接返回  当去重了
                    return;
                }
                result.add(new ArrayList<>(n));
                return;
            }
            if (sum > target){
                return;
            }
        }
        for (int i = 0; i < candidates.length; i++) {
            stack.push(candidates[i]);
            rec(candidates , target , stack , result);
            stack.pop();
        }
    }

    // 思路二   ⭐记录起始位置并 利用target做减法  ⭐target也参与回溯（这样就不用每次都记录stack里面的总和了）
    public void dfs(int start , int[] candidates , int target ,
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

            stack.push(candidates[i]);  //固定
            dfs(i , candidates , target - candidates[i] , stack , result);  //递归
            stack.pop(); //回溯
        }
    }
}
