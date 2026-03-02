package com.yuqiqi.leetcode.hot100;

import com.google.common.collect.ImmutableRangeMap;

public class Rotate48 {
    /**
     * 旋转图像  n * n的矩阵
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //转置 + 行反转 == 旋转     遍历左下方元素   遍历左半边元素
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {  //左下
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {  //左侧
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }
}
