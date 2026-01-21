package com.yuqiqi.dataStructure.graph.dijkstra;

import com.yuqiqi.dataStructure.graph.Edge;
import com.yuqiqi.dataStructure.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * ⭐迪杰斯特拉最短路径算法  （权重不能为负）
 * 1、将每个顶点设置为未访问的状态  创建一个⭐未访问顶点的集合
 * 2、为每个顶点分配一个⭐临时距离  初始顶点 设置为0  其他顶点设置为正无穷
 * 3、每次选择临时距离最小的顶点访问
 * 4、对于当前顶点，遍历其所有未访问的邻居，更新他们的临时距离为更小的值
 * 5、当前顶点的邻居处理完之后从未访问集合中删除
 */
public class Dijkstra {
    /**
     * 迪杰斯特拉算法
     * @param graph 图
     * @param source 起始顶点
     */
    public static void dijkstra(List<Vertex> graph , Vertex source){
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
                int dist = curr.dist + edge.weight;  //新的距离等于当前的dist+路径的权
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
