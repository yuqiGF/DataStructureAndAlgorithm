package com.yuqiqi.dataStructure.graph.topologicSort;

import com.yuqiqi.dataStructure.graph.Edge;
import com.yuqiqi.dataStructure.graph.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 拓扑排序 （有向无环图）     就像是流程图一样的方式排序   比如说：学完这几个才能学另一个 这个样的逻辑关系
 */
public class TopologicalSort {
    public static void sort(LinkedList<Vertex> graph){
        //1、统计每个节点的入度   遍历的时候加上就行
        for (Vertex vertex : graph) {
            for (Edge edge : vertex.edges) {
                edge.linked.inDegree ++;  //遍历每个节点的每条边  遇到一次  对应的另一个节点的入度就加一
            }
        }
        //2、将度数为0的节点加入队列
        LinkedList<Vertex> queue = new LinkedList<>();
        for (Vertex vertex : graph) {
            if (vertex.inDegree == 0){
                queue.offer(vertex);  //入队
            }
        }
        //结果加入集合 检测是否成环
        ArrayList<String> result = new ArrayList<>();
        //3、从队列中逐个删除元素  每删一个，和它相邻的节点的入度就减一  然后将入度减为0的节点入队
        while (!queue.isEmpty()){
            Vertex poll = queue.poll();
            result.add(poll.name); //加入集合检测成环
            for (Edge edge : poll.edges) {
                edge.linked.inDegree --;   //相邻元素的入度--
                if (edge.linked.inDegree == 0){
                    queue.offer(edge.linked);
                }
            }
        }
        if (result.size() == graph.size()){
            System.out.println("没有环");
        }
    }
}
