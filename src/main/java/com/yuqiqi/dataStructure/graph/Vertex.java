package com.yuqiqi.dataStructure.graph;

import java.util.List;

/**
 * 顶点
 */
public class Vertex {
    String name;
    List<Edge> edges;  //边们

    public Vertex(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
