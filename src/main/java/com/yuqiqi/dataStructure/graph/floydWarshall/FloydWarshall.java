package com.yuqiqi.dataStructure.graph.floydWarshall;

import com.yuqiqi.dataStructure.graph.Vertex;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * ⭐弗洛伊德最短路径算法   可以计算任意节点到任意节点的最短路径   关键是二维数组和借用⭐
 *  二维数组 表示横坐标到达纵坐标的路径距离 到不了就是无穷大， 循环判断n次（有几个顶点就循环几次） 看这个点能否通过另一个点到达另外的一个点 且距离较小 则更新数组中的元素值
 */
public class FloydWarshall {
    public static void floydWarshall(List<Vertex> graph){
        int size = graph.size();
        int[][] dist = new int[size][size];  //记录路程值
        Vertex[][] prev = new Vertex[size][size];  //记录路径

        //初始化  看能否到达
        for (int i = 0; i < size; i++) {  //遍历行
            Vertex v = graph.get(i); //外层节点
            Map<Vertex, Integer> map = v.edges.stream().collect(Collectors.toMap(e -> e.linked, e -> e.weight)); //外层节点转化为map集合（记得重写equals和hashcode方法）
            for (int j = 0; j < size; j++) {  //遍历列
                Vertex u = graph.get(j); //内层节点
                //⭐自己
                if (v == u){
                    dist[i][j] = 0;
                }
                //从与外层节点连接的节点集合中获取该边，如果有的话设置为该路径的权重（此时是再初始化  直接设置权重即可），没有的话设置为正无穷
                else {
                    dist[i][j] = map.getOrDefault(u,Integer.MAX_VALUE);
                    prev[i][j] = map.get(u) != null ? v : null;
                }
            }
        }

        //借路    有几个顶点就借几次路  有k个  借k次
        /*
            v2 -> v1  +   v1 -> v?
            dist[1][0]  dist[0][0]
            dist[1][0]  dist[0][1]
            dist[1][0]  dist[0][2]
            dist[1][0]  dist[0][3]
         */
        for (int k = 0; k < size; k++) {  //借第几个顶点
            for (int i = 0; i < size; i++) {  //行
                for (int j = 0; j < size; j++) {  //列
//                    dist[i][k] + dist[k][j]  //i行的顶点借助k顶点到达j列的顶点
//                    dist[i][j] //i直接到达j
                    if (dist[i][k] != Integer.MAX_VALUE &&
                            dist[k][j] != Integer.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                        prev[i][j] = prev[k][j];  //更新路径 为借助的那个节点
                    }
                }
            }
        }
        print(dist);
    }

    private static void print(int[][] dist){
        return;
    }
}
