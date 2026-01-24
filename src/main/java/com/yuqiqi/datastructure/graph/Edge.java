package com.yuqiqi.datastructure.graph;

/**
 * 边  邻接链表实现
 */
public class Edge {
    public Vertex linked;  //顶点（边上的另一个节点）
    public int weight; //权重

    public Edge(Vertex linked) {
        this(linked,1);
    }

    public Edge(Vertex linked, int weight) {
        this.linked = linked;
        this.weight = weight;
    }
}
