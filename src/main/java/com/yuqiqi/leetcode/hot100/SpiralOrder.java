package com.yuqiqi.leetcode.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 螺旋矩阵
 */
public class SpiralOrder {
    private final int[][] dirs = new int[][]{{0 , 1} , {1 , 0} , {0 , -1} , {-1 , 0}};  //分别模拟 右 下 左 上
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        ArrayList<Integer> list = new ArrayList<>(m * n);
        //模拟 右下左上 四个方向
        int i = 0;
        int j = 0;
        int di = 0;  //方向
        for (int k = 0; k < m * n; k++) {  //填满为止
            list.add(matrix[i][j]);
            matrix[i][j] = Integer.MAX_VALUE;  //已访问
            //判断要不要转向（按下一个的为止来判断）
            int x = i + dirs[di][0];
            int y = j + dirs[di][1];
            if (x >= m || y >= n || x < 0 || y < 0 || matrix[x][y] == Integer.MAX_VALUE){
                di = (di + 1) % 4;  //顺时针转90度
            }
            //转向后的位置
            i += dirs[di][0];
            j += dirs[di][1];
        }
        return list;
    }
}
