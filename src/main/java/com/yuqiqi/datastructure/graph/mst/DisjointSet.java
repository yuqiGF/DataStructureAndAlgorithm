package com.yuqiqi.datastructure.graph.mst;

import java.util.Arrays;

/**
 * 不相交集合  （并查集和）
 * 初始每个元素映射为自己本身    索引对应顶点   元素用来表示与之有关系的顶点   （连在一起的顶点牢大都一样）
 */
public class DisjointSet {
    int[] s;
    int[] size; //记录大小  ⭐大的当老大

    public DisjointSet(int size) {
        s = new int[size];
        this.size = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = i;
            this.size[i] = 1;
        }
    }

    //查找   找到“老大”
    public int find(int x){
        if (x == s[x]){
            return x;
        }
        //优化-路径压缩  顺便把查找结果直接记录下来
        return s[x] = find(s[x]);  //没找到的话递归接着找
    }

    //合并   让两个集合相交  选出新的老大  x，y是原来牢大的索引
    public void union(int x , int y){
        if (size[x] < size[y]){   //x比y的大小 小的话 x和y交换即可
            int t = x;
            x = y;
            y = t;
        }
        s[y] = x;  //改变老大
        size[x] = size[x] + size[y];
    }

    public static void main(String[] args) {
        DisjointSet set = new DisjointSet(7);
        System.out.println(Arrays.toString(set.s));
        //索引对应顶点
        //元素用来表示与之有关系的顶点
    }
}
