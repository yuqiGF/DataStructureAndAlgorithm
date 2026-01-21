package com.yuqiqi.dataStructure.graph.traversal;

import com.yuqiqi.dataStructure.graph.Edge;
import com.yuqiqi.dataStructure.graph.Vertex;

import java.util.LinkedList;

/**
 * 图的深度优先遍历   邻接链表表示法
 */
public class DFS {
    /**
     * DFS 深度优先遍历    没错 就是这么简单  关键就在于那个每个节点有它自己独立的边集合，还有是否被访问过的记录属性⭐
     * @param v 起始节点
     */
    private static void dfs(Vertex v){
        v.visited = true;  //将访问状态设置为已访问过
        System.out.println(v.name);
        //遍历该节点的每条边
        for (Edge edge : v.edges) {
            //如果该边上有节点没有被访问过
            if (!edge.linked.visited) {
                dfs(edge.linked);
            }
        }
    }

    /**
     * 非递归方式的DFS   栈     会顺着一条线一直往栈的深处压
     */
    private static void dfs2(Vertex v){
        LinkedList<Vertex> stack = new LinkedList<>();
        stack.push(v);  //起始点入栈
        while (!stack.isEmpty()){
            Vertex pop = stack.pop();//出栈的这个就是要遍历的元素
            pop.visited = true; //标记为已访问
            System.out.println(pop.name);
            //查找下一个节点   （因为节点对象里面已经叭所有边都包含了 所以直接遍历就行）
            for (Edge edge : v.edges) {
                if (!edge.linked.visited){
                    stack.push(edge.linked);  //未访问的话 入栈
                }
            }
        }
    }
}
