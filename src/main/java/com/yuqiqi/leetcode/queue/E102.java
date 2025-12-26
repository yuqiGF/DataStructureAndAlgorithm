package com.yuqiqi.leetcode.queue;

import com.yuqiqi.bean.TreeNode;
import com.yuqiqi.dataStructure.queue.LinkedListQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 二叉树的层序遍历  给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）
 */
public class E102 {
    /**
     * ⭐层序遍历  先取到根节点  进入队列，然后从头部挨个弹出节点  看这个节点有没有左右节点，有的话依次加入队列，没有的话取出队列里下一个，直到队列为空
     * 直到队列空了，结束遍历
     * @param root 根节点
     * @return 层序遍历结果
     */
    public List<List<Integer>> MyLevelOrder(TreeNode root) {
        LinkedListQueue<TreeNode> queue = new LinkedListQueue<>(); //创建队列
        List<List<Integer>> result = new ArrayList<>(); //结果集合
        //判空
        if (root == null){
            return result;
        }
        //放入根节点
        queue.offer(root);
        int c1 = 1; //当前这层的节点数
        int c2 = 0; //下一层的节点数  记录孩子节点的个数，下次循环赋值给c1

        //开始遍历
        while(!queue.isEmpty()){  //队列非空
            List<Integer> list = new ArrayList<>(); //内层集合
            for (int i = 0; i < c1; i++) {
                TreeNode n = queue.poll();  //⭐取出根节点
                list.add(n.val); //加入内层集合
                if (n.left != null){
                    queue.offer(n.left); //左节点入队
                    c2 ++;
                }
                if (n.right != null){
                    queue.offer(n.right); //右节点入队
                    c2 ++;
                }
            }
            result.add(list); //内层集合加入外层
            c1 = c2; //更新c1
        }
        return result;
    }

    /**
     *  LeetCode解法⭐jdk中的linkedList其实就可以充当双端队列（只使用尾插和头删方法）  字带了队列的对应方法
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        //创建队列
        LinkedList<TreeNode> queue = new LinkedList<>();
        //创建结果集合
        List<List<Integer>> result = new ArrayList<>();
        //判空
        if (root == null){
            return result;
        }
        //放入根节点
        queue.offer(root);
        //记录节点数
        int c1 = 1; //本层节点 初始为1
        int c2 = 0; //下一层节点数 第层节点遍历后的左右节点总个数

        //开始遍历
        while(!queue.isEmpty()){ //不为空
            List<Integer> level = new ArrayList<>(); //层集合
            for (int i = 0 ; i < c1 ; i ++) { //循环次数 = 当前本层的节点数  也就是取出的节点数
                //取出节点
                TreeNode n = queue.poll();
                level.add(n.val); //插入层中
                //判断是否有左或右节点
                if (n.left != null){
                    queue.offer(n.left);
                    c2 ++;
                }
                if (n.right != null){
                    queue.offer(n.right);
                    c2 ++;
                }
            }
            c1 = c2; //更新本层次数为下一次循环遍历做准备
            c2 = 0; //重置c2
            result.add(level);
        }
        return result;
    }
}
