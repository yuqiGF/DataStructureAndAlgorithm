package com.yuqiqi.algorithm.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合之和3   力扣 216
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 */
public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        rec(k , n , 1 , new LinkedList<>() , result);
        return result;
    }

    //记录起始位置并且做减法的回溯
    public void rec(int k , int n , int start,
                    LinkedList<Integer> stack , List<List<Integer>> result){
        //结束条件
        if (n < 0){
            return;
        }
        if (n == 0 && stack.size() == k){
            result.add(new LinkedList<>(stack));
        }

        for (int i = start; i < 10; i++) {
            if (n < i){  //剪枝
                continue;
            }
            stack.push(i);
            rec(k , n - i , i + 1 , stack , result);
            stack.pop();
        }
    }
}
