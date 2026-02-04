package com.yuqiqi.algorithm.backtracking;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 回溯算法⭐   通过递归的“归”这一过程  让程序回到之前的状态
 *      基本数据类型因为通过参数传递 不会修改值  可以直接恢复
 *      引用类型的话需要在归的时候手动删除
 */
public class Backtracking {
    static void rec(int n , LinkedList<String> list){
        if (n == 3){
            return;
        }
        System.out.println(n);
        System.out.println(list);
        list.push("a");
        rec(n + 1 , list);  //递归
        list.pop();  //⭐手动删除 实现回溯
        System.out.println(n);
        System.out.println(list);
    }

    public static void main(String[] args) {
        rec(1 , new LinkedList<>());
    }
}
