package com.yuqiqi.datastructure.graph.mst;


import com.yuqiqi.datastructure.graph.Edge;
import com.yuqiqi.datastructure.graph.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 最小生成树  克鲁斯卡尔算法
 * 先把边排个序  由小到大按照权重处理
 */
public class Kruskal {
    static class Edge implements Comparable<com.yuqiqi.datastructure.graph.Edge>{
        List<Vertex> vertices;
        int start;
        int end;
        int weight;

        public Edge(List<Vertex> vertices, int start, int end, int weight) {
            this.vertices = vertices;
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(com.yuqiqi.datastructure.graph.Edge o) {
            return Integer.compare(this.weight,o.weight);
        }
    }

    /**
     * 克鲁斯卡尔算法
     * @param size 顶点个数
     * @param queue 边的队列
     */
    public static void kruskal(int size , PriorityQueue<Edge> queue){
        List<Edge> list = new ArrayList<>();  //结果集合
        DisjointSet set = new DisjointSet(size);  //⭐不相交集合

        while (list.size() < size - 1){  //循环 找够size-1条边
            Edge poll = queue.poll(); //获取权重最小的边
            //判断连个顶点是否已经联通
            int i = set.find(poll.start);  //找牢大
            int j = set.find(poll.end);  //找牢大
            if (i != j){  //未相交  牢大不同
                list.add(poll);
                set.union(i,j);  //让两个相交 让牢大相同
            }
        }
    }
}
