package com.yuqiqi.leetcode.queue.deque;

import com.yuqiqi.bean.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 数的Z字层序遍历   与102类似
 */
public class E103 {
    /**
     * 同样是用队列去存储  但是在偶数添加元素的时候使用双端队列从头部添加
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        //初始化队列和结果集合
        LinkedList<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        //判空
        if (root == null){
            return result;
        }
        int c1 = 1; //本层节点数
        boolean isOdd = false; //判断是否是偶数层

        queue.offer(root); //入队
        while (!queue.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            int c2 = 0;  //下一层节点数
            for (int i = 0; i < c1; i++) {
                TreeNode node = queue.poll();  //出队
                if (isOdd){ //偶数层 头插
                    level.addFirst(node.val);
                }else { //奇数层 尾插
                    level.addLast(node.val);
                }
                if (node.left != null){
                    queue.offer(node.left);
                    c2 ++;
                }
                if (node.right != null){
                    queue.offer(node.right);
                    c2 ++;
                }
            }
            result.add(level); //加入结果集合
            c1 = c2; //更新c1 为下一层循环做准备
            isOdd = !isOdd; //奇偶flag反转
        }
        return result;
    }
}
