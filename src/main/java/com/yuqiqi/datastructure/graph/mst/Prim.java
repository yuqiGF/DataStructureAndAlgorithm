package com.yuqiqi.datastructure.graph.mst;

import com.yuqiqi.datastructure.graph.Edge;
import com.yuqiqi.datastructure.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * 最小生成树 （把所有的顶点按照距离的最小值连起来）  Prim算法
 * 以顶点为核心 每次找到和顶点相邻的其他最近的顶点 把他们连起来
 * 思路和迪杰斯特拉算法几乎一模一样 只是更新距离的时候只考虑权重
 */
public class Prim {
    public static void prim(List<Vertex> graph , Vertex source){
        ArrayList<Vertex> list = new ArrayList<>(graph);  //⭐未访问的顶点集合
        //初始节点的距离改为0
        source.dist = 0;
        while (!list.isEmpty()){
            //选dist最小的顶点
            Vertex curr = chooseMinDisVertex(list);
            //更新邻居距离
            updateNeighboursDist(curr);
            //移除
            list.remove(curr);
            curr.visited = true;  //记录为已处理
        }
        for (Vertex vertex : graph) {
            System.out.println(vertex.name + " " + vertex.dist + (vertex.prev == null ? "null" : vertex.prev));
        }
    }

    private static void updateNeighboursDist(Vertex curr) {
        for (Edge edge : curr.edges) {
            //需要判断一下拿到的顶点是否在未遍历的集合中 （即 是否已访问）
            Vertex n = edge.linked;
            if (!n.visited){
//                int dist = curr.dist + edge.weight;  //新的距离等于当前的dist+路径的权
                int dist = edge.weight;  //⭐⭐直接那权重去比较即可
                if (dist < n.dist){ //比原本的距离小的话
                    n.dist = dist;  //更新
                    n.prev = curr;  //⭐更新最短距离从何而来
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
