package com.yuqiqi.dataStructure.graph;

import java.util.List;

/**
 * 顶点    邻接表实现  每个节点后面跟着它的邻接链表  如A->B->C   B->D->C这种 ⭐此时它的边不是单独的边，而是独属于这个节点的边，只连接与这个节点相连的节点
 */
public class Vertex {
    public String name;
    public List<Edge> edges;  //边们 ⭐  注意：这些边只属于该节点，其他节点需要单独另创建
    public boolean visited;  //⭐记录是否被访问过
    public int inDegree;  //记录入度 方便层序遍历
    public int status;  //顶点状态属性  0未访问  1访问中  2访问过

    public int dist = INF;  //距离  用来计算最短路径
    public static final Integer INF = Integer.MAX_VALUE;  //最大值 无穷大
    public Vertex prev = null;  //从何而来  记录路径

    public Vertex(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
