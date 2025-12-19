package com.yuqiqi.algorithm.recursion;

/**
 * 帕斯卡三角  杨辉三角
 * 地推关系：[i][j] = [i - 1][j - 1] + [i - 1][j]    [1][1] = 1
 */
public class PascalTriangle {

    /**
     * ⭐求杨辉三角i行j列的值
     * @param i 行
     * @param j 列
     * @return 结果
     */
    private static int element(int i,int j){
        //结束条件
        if (j == 0 || i == j){  //把杨辉三角斜过来 看到竖的一列1 斜的一列1
            return 1;
        }
        //通过递推关系 用上一行的东西来得出
        return element(i - 1, j - 1) + element(i - 1 , j);
    }

    /**
     * 打印杨辉三角
     * @param n 三角的高度
     */
    public static void print(int n){
        for (int i = 0; i < n; i++) {  //行
            printSpace(n,i); //打印空格
            for (int j = 0; j <= i; j++) {  //列
                System.out.printf("%-4d",element(i, j));  //%4d 间隔4个单位  -左对齐
            }
            System.out.println();
        }
    }

    /**
     * 打印空格  （从下往上数）
     * @param n 高度
     * @param i 行号
     */
    public static void printSpace(int n, int i){
        int num = (n - 1 - i) * 2;  //需要打印的空格数
        for (int j = 0; j < num; j++) {
            System.out.print(" "); //打印空格
        }
    }


    public static void main(String[] args) {
        print(10);
    }
}
