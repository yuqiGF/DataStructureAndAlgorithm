package com.yuqiqi.dataStructure.graph.bellmanFord;

import com.yuqiqi.dataStructure.graph.Edge;
import com.yuqiqi.dataStructure.graph.Vertex;

import java.util.List;

/**
 * ⭐贝尔曼福德最短路径算法  （负边也可以处理  但是出现负数环的话无法处理）
 * 在迪杰斯特拉算法的基础上每处理一个节点更新所有节点的临时距离值⭐  有n个节点就循环n-1次
 * 也就不需要什么集合和队列了
 */
public class BellmanFord {
    private static void bellmanFord(List<Vertex> graph, Vertex source) {
        source.dist = 0;  //同样是将起点的临时距离改为0
        int size = graph.size();  //拿到图的节点数
        for (int i = 0; i < size - 1; i++) {  //有n个节点就循环n-1次
            for (Vertex start : graph) {  //遍历每个节点
                for (Edge edge : start.edges) {  //遍历每个边
                    Vertex end = edge.linked;
                    int dist = start.dist + edge.weight;
                    if (start.dist != Integer.MAX_VALUE && dist < edge.linked.dist) {  //起始点的距离不为正无穷且新距离小于老距离时更新
                        end.dist = dist;
                        end.prev = start;  //更新来时的路径
                    }
                }
            }
        }
        //⭐循环完n-1次的话理论上已经找不到更小的临时距离了   此时再来一次循环，如果还能找到更小的  说明出现了负权环
        for (Vertex start : graph) {
            for (Edge edge : start.edges) {
                if (start.dist != Integer.MAX_VALUE && start.dist + edge.weight < edge.linked.dist){
                    System.out.println("出现了负权环");
                    return;
                }
            }
        }
    }
}
