package com.yuqiqi.dataStructure.graph;

/**
 * 边
 */
public class Edge {
    Vertex linked;  //顶点
    int weight; //权重

    public Edge(Vertex linked) {
        this(linked,1);
    }

    public Edge(Vertex linked, int weight) {
        this.linked = linked;
        this.weight = weight;
    }
}
