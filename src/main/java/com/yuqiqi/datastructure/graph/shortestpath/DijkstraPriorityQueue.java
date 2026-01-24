package com.yuqiqi.datastructure.graph.shortestpath;

import com.yuqiqi.datastructure.graph.Edge;
import com.yuqiqi.datastructure.graph.Vertex;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * ⭐迪杰斯特拉最短路径算法  优先级队列实现
 * 1、创建一个⭐优先级队列
 * 2、为每个顶点分配一个⭐临时距离  初始顶点 设置为0  其他顶点设置为正无穷
 * 3、每次选择临时距离最小的顶点访问
 * 4、对于当前顶点，遍历其所有未访问的邻居，更新他们的临时距离为更小的值  ⭐更新后需要重新加入优先级队列（重复了也没关系，位置更重要 加入后会自动调整位置）
 * 5、当前顶点的邻居处理完之后将当前节点从中队列中删除
 */
public class DijkstraPriorityQueue {
    /**
     * 迪杰斯特拉算法
     * @param graph 图
     * @param source 起始顶点
     */
    public static void dijkstra(List<Vertex> graph , Vertex source){
        //创建优先级队列  传入一个比较器   java中的优先级队列默认是小顶堆  指定按临时距离从从小开始排
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(value -> value.dist));
        for (Vertex vertex : graph) {
            queue.offer(vertex); //全部入队
        }
        //初始节点的距离改为0
        source.dist = 0;
        while (!queue.isEmpty()){
            //选dist最小的顶点  直接取头部即可
            Vertex curr = queue.peek();
            //更新邻居距离
            updateNeighboursDist(curr , queue);
            //移除
            queue.poll();
            curr.visited = true;  //记录为已处理
        }
        for (Vertex vertex : graph) {
            System.out.println(vertex.name + " " + vertex.dist + (vertex.prev == null ? "null" : vertex.prev));
        }
    }

    private static void updateNeighboursDist(Vertex curr , PriorityQueue<Vertex> queue) {
        for (Edge edge : curr.edges) {
            //需要判断一下拿到的顶点是否在未遍历的集合中 （即 是否已访问）
            Vertex n = edge.linked;
            if (!n.visited){
                int dist = curr.dist + edge.weight;  //新的距离等于当前的dist+路径的权
                if (dist < n.dist){ //比原本的距离小的话
                    n.dist = dist;  //更新
                    n.prev = curr;  //⭐更新最短距离从何而来
                    queue.offer(curr);  //⭐重新加入队列
                }
            }
        }
    }

    /**
     * 选取距离最小的顶点
     * @param list 未访问顶点集合
     * @return 当前顶点
     */
    private static Vertex chooseMinDisVertex(ArrayList<Vertex> list) {
        Vertex curr = list.getFirst();
        for (Vertex vertex : list) {
            if (vertex.dist < curr.dist){
                curr = vertex;
            }
        }
        return curr;
    }
}
