package com.yuqiqi.algorithm.recursion;

import java.util.jar.JarEntry;

/**
 * 帕斯卡三角  杨辉三角
 * 地推关系：[i][j] = [i - 1][j - 1] + [i - 1][j]    [1][1] = 1
 */
public class PascalTriangle {

    /**
     * ⭐求杨辉三角i行j列的值   2^n
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


    //记忆化优化  n^2
    //递归过程
    public static int element1(int[][] a, int i, int j){
        //判断是否已经计算过了
        if (a[i][j] != 0){
            return a[i][j];  //计算过了就直接返回
        }
        //没计算过的话就存入数组
        if (j == 0|| i == j){
            //存入计算结果过
            a[i][j] = 1;
            return 1;
        }
        //存入计算结果
        a[i][j] = element1(a,i - 1, j - 1)+ element1(a,i - 1 , j);
        return a[i][j];
    }

    //打印过程
    public static void print1(int n){
        int[][] a = new int[n][];  //行数就是高度n
        for (int i = 0; i < n; i++) {  //行
            printSpace(n,i);
            a[i] = new int[i + 1];  //指定每行有多少列
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", element1(a,i,j));
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        print1(10);
    }
}
