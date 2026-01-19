package com.yuqiqi.algorithm.recursion;

import java.util.LinkedList;
import java.util.List;

/**
 * 汉诺塔问题     三个柱子  大的不能压在小的上面
 * ⭐问题的关键在于函数形参的确定   确定为要移动的圆盘数量  源柱  借柱  目标柱   可以反复使用⭐
 * 拆成三步   原始问题：将a柱的元素移动到c  ->  1、将a柱最后一片上面的所有圆盘都移动到b  2、将a柱的最后一片移动到c  3、将b柱子的所有圆盘移动到c
 * 先借助b柱子把a柱最大的那片移动到c的最下面  然后自然而然又空出一个a柱子   依次递归即可
 */
public class HanoiTower {
    //三个链表模拟三个柱子
    static LinkedList<Integer> a = new LinkedList<>();
    static LinkedList<Integer> b = new LinkedList<>();
    static LinkedList<Integer> c = new LinkedList<>();
    //初始化
    static void init(int n){
        for (int i = n; i >= 1; i--) {  //从大了往小了摞
            a.add(i); //1 - n的圆盘
        }
    }


    public static void main(String[] args) {
        init(64);
        System.out.println(a);
//        b.addLast(a.removeLast());  //把a的顶上的元素移动到b的顶上   圆盘的移动
        move(64,a,b,c);
    }

    //打印各个汉诺塔
    private static void print() {
        System.out.println("---------------------");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    /**
     * 汉诺塔移动
     * @param n 圆盘个数
     * @param a 源
     * @param b 借
     * @param c 目标
     */
    static void move(int n ,
                     LinkedList<Integer> a,
                     LinkedList<Integer> b,
                     LinkedList<Integer> c){
        //结束条件
        if (n == 0){
            return;
        }
        //2、要实现最后一步 就需要把a上的其他元素都移动到b    这个时候就变成了a借助c移动到b  递归调用即可
        move(n - 1,a,c,b);
        //1、先看中间一步 a 移动到 c 喵
        c.addLast(a.removeLast());
        print();
        //3、最后把b上的元素借助a移动到c
        move(n - 1,b,a,c);
    }
}
