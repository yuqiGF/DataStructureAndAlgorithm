package com.yuqiqi.algorithm.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合   力扣77   回溯思想⭐    1，2和2.1效果是一样的
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 */
public class Combination {
    public List<List<Integer>> combine(int n, int k) {
        //思路：回溯
        ArrayList<List<Integer>> result = new ArrayList<>();
        rec(n,k,new LinkedList<>() , result);
        return result;
    }

    //反着来遍历   恰好过了
    public void rec(int n , int k ,
                    LinkedList<Integer> stack , ArrayList<List<Integer>> result){
        if (stack.size() == k){
            result.add(new ArrayList<>(stack));
            return;   //结束条件
        }
        for (int i = 1; i <= n; i++) {
            if (!stack.isEmpty() && stack.peek() == i){   //直接判断栈顶元素是否和此元素相等即可
                continue;  //⭐剪枝   这个就直接把重复的剔掉了
            }
            stack.push(i);
            rec(i , k , stack , result);  //正好  从后往前的一个组合 倒过来了  1   21   321   4321 …………
            stack.pop();  //回溯
        }
    }

    //正着来回溯
    public void dfs(int n , int k , int start ,
                    LinkedList<Integer> stack , ArrayList<List<Integer>> result){
        if (stack.size() == k){
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int i = start; i <= n; i++) {
            if (k - stack.size() > n - i + 1){
                continue;   //剪枝  超过还剩下的所需个数时直接跳过
            }
            stack.push(i);
            start = i + 1;  //从start + 1开始遍历  不能要自己
            dfs(n , k , start , stack , result);
            stack.pop();  //回溯
        }
    }
}
