package com.yuqiqi.leetcode.hot100;

import javax.xml.crypto.Data;
import java.util.LinkedList;

public class Trap {
    public int trap(int[] height) {
        //单调递减栈
        LinkedList<Data> stack = new LinkedList<>();
        int max = 0;
        for (int i = 0 ; i < height.length ; i ++) {
            while (!stack.isEmpty() && height[i] > stack.peek().height){
                Data pop = stack.pop();  //被弹出的
                Data peek = stack.peek();  //被弹出的左边的
                if (peek != null){
                    max += (i - peek.index - 1) * (Math.min(peek.height , height[i]) - pop.height);  //遇到中间的那种就直接是  + 0 了
                }
            }
            stack.push(new Data(i , height[i]));  //放入
        }
        return max;
    }

    public class Data{
        int index;
        int height;

        public Data(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }
}
