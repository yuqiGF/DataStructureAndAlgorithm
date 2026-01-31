package com.yuqiqi.algorithm.dynamicprogramming;

/**
 * 卡特兰数   n个节点组成的不同二叉搜索树数量
 *   参考力扣96
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

    public static void main(String[] args) {
        System.out.println(catalan(4));
    }
}
