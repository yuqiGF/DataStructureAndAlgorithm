package com.yuqiqi.dataStructure.graph.traversal;

import com.yuqiqi.dataStructure.graph.Edge;
import com.yuqiqi.dataStructure.graph.Vertex;

import java.util.LinkedList;

/**
 * 广度优先遍历    队列    经典入队再出队   队列先进先出  出来的顺序就是层序遍历的顺序
 */
public class BFS {
    public static void bfs(Vertex v){
        LinkedList<Vertex> queue = new LinkedList<>();

        queue.offer(v);
        v.visited = true;
        while (!queue.isEmpty()){
            Vertex poll = queue.poll();  //出队
            System.out.println(poll.name);
            for (Edge edge : poll.edges) {
                if (!edge.linked.visited) {
                    edge.linked.visited = true;
                    queue.offer(edge.linked);  //入队
                }
            }
        }
    }
}
