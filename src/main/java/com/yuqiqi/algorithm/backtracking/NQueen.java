package com.yuqiqi.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ⭐N皇后问题   力扣51
 * ⭐回溯法
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 */
public class NQueen {
    public List<List<String>> solveNQueens(int n) {  //传入的是棋盘的维度

        List<List<String>> result = new ArrayList<>();  //结果数组

        //⭐重点是记录冲突位置
        boolean[] ca = new boolean[n];   //记录列的冲突
        boolean[] cb = new boolean[2 * n - 1];   //左斜线冲突  i + j  是一样的的话就是一条线上的
        boolean[] cc = new boolean[2 * n - 1];   //右斜线冲突  n - 1 - (i - j)  全部转化为正数
        char[][] table = new char[n][n];  //⭐记录整个棋盘

        //⭐单元格初始化  如果没有皇后的话 初始化为. 有皇后的话初始化为Q
        for (char[] chars : table) {
            Arrays.fill(chars , '.');
        }

        //调用递归  进行回溯
        rec(0 , n , table , ca , cb , cc , result);

        return result;
    }

    /**
     * 处理棋盘的递归
     * @param i 处理第几行  ⭐（一行最多只能有一个  所以就当计数了）
     * @param n 棋盘的维度
     * @param table 棋盘数组
     * @param ca 列的冲突
     * @param cb 左斜线的冲突
     * @param cc 右斜线的冲突
     */
    public static void rec(int i , int n , char[][] table ,
                           boolean[] ca,
                           boolean[] cb,
                           boolean[] cc,
                           List<List<String>> result  //传入结果数组来记录结果
                           ){
        //结束条件
        if (i == n){  //找到了解
            ArrayList<String> list = new ArrayList<>();
            for (char[] chars : table) {
                StringBuilder sb = new StringBuilder();  //因为要加入的是连着的整个字符串  所以要拼接
                for (char c : chars) {
                    sb.append(c);
                }
                list.add(sb.toString());
            }
            result.add(list);
            return;
        }
        //针对列的循环   尝试往列中放入皇后
        for (int j = 0; j < n; j++) {
            //⭐遇到冲突 直接跳过
            if (ca[j] || cb[i + j] || cc[n - 1 - (i - j)]){
                continue;  //剪枝
            }
            table[i][j] = 'Q' ;  //放入皇后
            ca[j] = cb[i + j] = cc[n - 1 - (i - j)] = true;  //更新列和斜线的冲突
            //⭐调用递归  开始回溯
            rec(i + 1 , n , table , ca , cb , cc , result);
            table[i][j] = '.' ;  //⭐回溯
            ca[j] = cb[i + j] = cc[n - 1 - (i - j)] = false;  //⭐回溯冲突
        }
    }
}
