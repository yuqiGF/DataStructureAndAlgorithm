package com.yuqiqi.algorithm.recursion;

/**
 * 逆序打印字符串    ⭐再递归调用后打印即可
 */
public class ReversePrintString {
    /**
     * 逆序打印字符串
     *
     * @param n   从哪个索引开始
     * @param str 要打印的字符串
     */
    public static void f(int n, String str) {    //⭐递归的变化量必须通过参数传递  不能使用静态变量
        if (n == str.length()) {
            return;
        }
        f(n + 1, str);
        //逆序递归  操作放在递归后面
        System.out.println(str.charAt(n));
    }

    public static void main(String[] args) {
        f(1, "yuqiqi");
    }
}
