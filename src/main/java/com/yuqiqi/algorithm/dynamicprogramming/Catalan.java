package com.yuqiqi.algorithm.dynamicprogramming;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

/**
 * 卡特兰数   n个节点组成的不同二叉搜索树数量    1  2  5 …………
 *   应用：
 *      ⭐⭐n个节点组成的不同二叉搜索树个数   E96
 *      ⭐⭐栈的出栈结果数（1的出栈次序）
 *      ⭐⭐有效括号的排列个数（1对括号里面包含几个括号）   E22
 *   数学归纳法 找规律⭐ 动态规划    节点的拆分⭐
 *   C0 = 1
 *   C1 = 1
 *   C2 = 2  （拆分成c0*c1 + c1*c0）
 *   C3 = 5  （拆分成c0 * c2 + c1 * c1 + c2 * c0）
 *   C4 = 14 （拆分成c0 * c3 + c1 * c2 + c2 * c1 + c3 * c0）
 */
public class Catalan {
    /**
     * 求n的卡特兰数  ？？？
     * @param n 节点个数
     * @return 卡特兰数
     */
    static int catalan(int n){
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {  //第j个卡特兰数的拆分
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        return dp[n];
    }

    /**
     * E22 括号生成   1  2  5  14 ……
     * @param n 括号对数
     * @return 组合结果
     */
    public List<String> generateParenthesis(int n) {
        //卡特兰数   ⭐整个换成动态数组
        ArrayList<String>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<>(List.of(""));  //空
        dp[1] = new ArrayList<>(List.of("()"));  //一对括号

        for (int i = 2; i < n + 1; i++) {  //第i个节点
            dp[i] = new ArrayList<>(); //初始化一下
            for (int j = 0; j < i; j++) {  //第i个节点的卡特兰数
                //⭐⭐ j对应的集合是内层要嵌套的括号   i - 1 - j对应的集合是与外层括号平级的括号
                for (String k1 : dp[j]) { //内层要嵌套的
                    for (String k2 : dp[i - 1 - j]) {  //平级要拼接的
                        dp[i].add("(" + k1 + ")" + k2);
                    }
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(catalan(4));
    }
}
