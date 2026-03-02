package com.yuqiqi.leetcode.hot100;

import java.util.*;
import java.util.spi.AbstractResourceBundleProvider;

/**
 * 矩阵置零
 */
public class SetZeroes {
    public static void setZeroes(int[][] matrix) {
        //先找0的位置
        ArrayList<int[]> list = new ArrayList<>();  //存行和列的位置
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0){
                    list.add(new int[]{i , j});
                }
            }
        }
        for (int[] ints : list) {
            int x = ints[0];
            int y = ints[1];
            Arrays.fill(matrix[x] , 0);  //行置零
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][y] = 0;  //列置零
            }
        }
    }

    public static void main(String[] args) {
        setZeroes(new int[][]{{0,1,2,0} , {3,4,5,2} , {1,3,1,5}});
    }
}
