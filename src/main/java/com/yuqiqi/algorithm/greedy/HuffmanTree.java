package com.yuqiqi.algorithm.greedy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 哈夫曼树及其编码   （满二叉树）  贪心（每次取频率最低的）+前缀编码+二叉树+优先队列
 */
public class HuffmanTree {
    /**
     * 构建过程：  首先它是一个满二叉树
     * 1、将统计了出现频率的字符放入优先级队列  ⭐频次低的优先级高  最后就处在生成的树的更下面的位置
     * 2、每次出队两个频率最低的元素 给他俩找爹 （创建爹  爹的频次是俩孩子的和，左右孩子分别是刚刚去取出来的俩节点）
     * 3、把爹重新放入队列，重复2-3
     * 4、当队列只剩一个元素的时候，Huffman树构建完成
     */
    static class Node{
        Character ch;  //字符
        int freq;  //出现频次
        Node left;
        Node right;
        String code;  //编码   （实际上应该是二进制位  这里简化成字符串）

        public Node(Character ch) {
            this.ch = ch;
        }

        public Node(int freq, Node left, Node right) {
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        int getFreq(){
            return freq;
        }
        //判断是否为叶子
        boolean isLeaf(){
            return left == null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "ch=" + ch +
                    ", freq=" + freq +
                    ", left=" + left +
                    ", right=" + right +
                    ", code='" + code + '\'' +
                    '}';
        }
    }

    String str;  //原始字符串
    Node root;
    Map<Character,Node> map = new HashMap<>();  //map集合用于记录频率

    /**
     * 构造
     * @param str 原始字符串
     */
    public HuffmanTree(String str) {
        this.str = str;
        //⭐统计频率
        char[] chars = str.toCharArray();
        for (char c : chars) {
            //已有的话 频次++
//            if (!map.containsKey(c)) {
//                map.put(c, new Node(c));  //没有的话放入
//            }
//            map.get(c).freq ++;  //已有的话 频次++
            Node node = map.computeIfAbsent(c, Node::new);  //如果为空的话执行后面的内容  不为空的话直接返回c处的值
            node.freq ++;
        }
        //⭐构造树
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getFreq));  //指定按照频次排序
        queue.addAll(map.values());  //直接全部添加
        //循环
        while (queue.size() >= 2){
            Node x = queue.poll();
            Node y = queue.poll();
            int freq = x.freq + y.freq;  //确定爹的频次
            queue.offer(new Node(freq,x,y));  //创建新节点并且把它放入队列
        }
        //最后队列中剩下的一个就是根节点
        root = queue.poll();
        //⭐计算每个字符的编码  深度优先遍历
        dfs(root , new StringBuilder());
    }

    /**
     * 深度优先遍历  并编码：向左编0  向右编1    使用stringBuilder  往深走加 往回走减
     * @param node 根节点
     */
    private int dfs(Node node , StringBuilder code) {
        int sum = 0; //统计总的byte数
        if (node.isLeaf()){
            //找到最下面了  找到编码
            node.code = code.toString(); //记录编码
            sum = node.freq * code.length();
        }else {
            //不是叶子节点就得继续往下找
            sum += dfs(node.left , code.append(0)); //向左走 +0
            sum += dfs(node.right , code.append(1)); //向右走 +1
        }
        //往回走
        if (!code.isEmpty()){ //非空的时候
            code.deleteCharAt(code.length() - 1); //删掉最后一个
        }
        return sum;
    }

    /**
     * 编码
     */
    public String encode(){
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(map.get(c).code); //把每个字符的编码拼接进去
        }
        return sb.toString();
    }

    /**
     * 解码  从根节点开始寻找数字对应的字符  0向左走  1向右走  没走到头的话每一步索引都++  走到头的话从头开始重新走
     */
    public String decode(String str){
        char[] chars = str.toCharArray();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        Node p = root; //节点指针
        while (i < chars.length){
            if (!p.isLeaf()){
                if (chars[i] == '0'){  //⭐注意是字符0
                    p = p.left;  //向左
                }else if (chars[i] == '1'){
                    p = p.right;
                }
                i ++;
            }
            if (p.isLeaf()){  //是叶子节点  找到了    都在本轮循环判断  避免丢失最后一个元素
                sb.append(p.ch);
                p = root;  //指针回到起点
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        HuffmanTree tree = new HuffmanTree("abbcccccc");
        String encoded = tree.encode();
        String decode = tree.decode(encoded);
        System.out.println(decode);
    }
}
