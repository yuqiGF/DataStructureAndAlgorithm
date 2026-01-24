package com.yuqiqi.datastructure.graph.topologic;

import com.yuqiqi.datastructure.graph.Edge;
import com.yuqiqi.datastructure.graph.Vertex;

import java.util.LinkedList;

/**
 * 拓扑排序   深度优先实现   先沿着一条线往最里面遍历   出来的时候压栈 直到回到起点   然后再从其他节点开始遍历
 */
public class TopologicalSortDFS {
    public static void sort(LinkedList<Vertex> graph){
        LinkedList<Vertex> stack = new LinkedList<>();
        for (Vertex vertex : graph) {
            dfs(vertex,stack);
        }
    }

    private static void dfs(Vertex vertex, LinkedList<Vertex> stack) {
        //判断是否已经被遍历过了
        if (vertex.status == 2){
            return;
        }
        if (vertex.status == 1){
            return;  //出现环了
        }
        //遍历相邻节点之前将状态设置为遍历中
        vertex.status = 1;
        for (Edge edge : vertex.edges) {  //没有的话对相邻顶点遍历
            dfs(edge.linked,stack);
        }
        //处理完之后状态改变
        vertex.status = 2;
        stack.push(vertex);  //入栈
    }
}
