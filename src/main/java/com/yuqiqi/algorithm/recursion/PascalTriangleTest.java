package com.yuqiqi.algorithm.recursion;

public class PascalTriangleTest {
    //练习一下杨辉三角

    /**
     * 计算每个位置上的值
     * @param i
     * @param j
     * @return
     */
    //传图一个数组用作记录和传递   ⭐一般都是通过参数传递的
    public static int element(int[][] array , int i , int j){
        //如果数组里面已经有了的话直接返回
        if (array[i][j] != 0){
            return array[i][j];
        }
        //没有的话把这次计算的结果存入
        //三角形的左右两边都是1
        if (j == 0 || j == i){
            array[i][j] = 1;
            return 1;
        }
        array[i][j] = element(array, i - 1,j - 1)+ element(array,i - 1, j );
        return array[i][j];
    }

    /**
     * 打印
     * @param n 高度
     */
    public static void print(int n){
        //初始化数组
        int[][] array = new int[n][]; //有多高就有多少行
        //开始遍历每行
        for (int i = 0; i < n; i++) {
            array[i] = new int[i + 1]; //第i行有i + 1个数
            for (int j = 0; j <= i; j++) { //开始遍历每列  列数比行数多1 所以带等
                System.out.printf("%-4d",element(array,i,j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        print(5);
    }
}
