package com.yuqiqi.leetcode.tree.binaryTree;

import com.yuqiqi.datastructure.tree.TreeNode;

import java.util.LinkedList;

/**
 * 二叉树的最小深度
 */
public class E111 {
    /**
     * 递归  （后序遍历）
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int d1 = minDepth(root.left);  //左子树深度
        int d2 = minDepth(root.right);  //右子树深度
        //⭐要考虑特殊情况 一条腿的树
        if (d1 == 0){
            return d2 + 1;
        }
        if (d2 == 0){
            return d1 + 1;
        }
        return Math.min(d1,d2) + 1;
    }

    /**
     * 层序遍历   ⭐第一个遇到的叶子节点就是最小深度所在的子树
     */
    public int minDepth2(TreeNode root){
        if (root == null){
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0; //记录层数
        while (!queue.isEmpty()){
            level ++; //层数+1
            int size = queue.size();
            for (int i = 0 ; i < size ; i ++) {
                TreeNode poll = queue.poll();//取出
                if (poll.right == null && poll.left == null){
                    return level;
                }
                if (poll.left != null){
                    queue.offer(poll.left);
                }
                if (poll.right != null){
                    queue.offer(poll.right);
                }
            }
        }
        return level;
    }
}

