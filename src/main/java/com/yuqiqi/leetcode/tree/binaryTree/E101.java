package com.yuqiqi.leetcode.tree.binaryTree;

import com.yuqiqi.datastructure.tree.TreeNode;

/**
 * 对称二叉树  递归  传入的时连个节点
 */
public class E101 {
    /**
     * 思路 递归  左节点和右节点对比
     * @param
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }
        return check(root.left , root.right);
    }

    /**
     * 递归  比较左边和右边
     */
    public static boolean check(TreeNode left , TreeNode right){
        if (left  == null && right == null) {
            return true; //两边都为空
        }
        if (left == null || right == null){
            return false; //只有以边为空
        }
        //⭐两边都不为空
        if (left.val != right.val){
            return false;
        }
        //递归  返回最有两个节点是否都对称
        return check(left.right, right.left) && check(left.left, right.right);
    }

}
