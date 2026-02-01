package com.yuqiqi.algorithm.dynamicprogramming;

/**
 * 旅行商问题TSP    ⭐动态规划   NP-Hard
 * 必须经过每一个地点的最短路径   ⭐组合数 = （n - 1）！ 复杂度超级高  只能求近似解
 * 递推式：出发城市 i 剩余城市集合 j  遍历j时的变量k是剩余的某一城市
 * d(i,j) => min(    说白了就是暴力拆分
 *                  g[i][k] + d(k,j去掉k)
 *                  g[i][k] + d(k,j去掉k)
 *                  g[i][k] + d(k,j去掉k)
 *               )
 *  d(k,空)=> 从k返回起点 => g[k][i]
 *  总共四个城市的时候 ⭐用二进制代表不同的城市组合
 */
public class TravellingSalesman {
    public static int tsp(int[][] g){
        int m = g.length;  //城市数目
        int n = 1 << (m - 1);  //剩余城市的组合数 2^(m - 1)
        int[][] dp = new int[m][n];
        //填充第0列
        for (int k = 0; k < m; k++) {
            dp[k][0] = g[k][0];
        }
        //填充后续列
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                dp[i][j] = Integer.MAX_VALUE / 2;
                if (contains(j,i)){
                    continue;
                }
                //填充单元格
                for (int k = 0; k < m; k++) {
                    if (contains(j,k)){
                        dp[i][j] = Integer.min(dp[i][j], g[i][k] + dp[k][exclude(j,k)]);
                    }
                }

            }
        }
        //最终结果在右上角
        return dp[0][n - 1];
    }

    //判断集合中是否包含某元素
    public static boolean contains(int set , int city){
        return ((set >> (city - 1)) & 1) == 1;
    }

    //从集合中排除某元素
    public static int exclude(int set , int city){
        return set ^ (1 << (city - 1));
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 2, 3},
                {1, 0, 6, 4},
                {2, 6, 0, 5},
                {3, 4, 5, 0}
        };
        System.out.println(tsp(graph));
    }
}
