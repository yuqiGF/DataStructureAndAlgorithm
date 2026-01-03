package com.yuqiqi.leetcode.tree.binaryTree;

import com.yuqiqi.dataStructure.tree.TreeNode;

/**
 * 翻转二叉树  给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点
 */
public class E226 {
    /**
     * 递归  就只是翻转  递归着一层一层往下调换就行  （和判断是否对称不一样）
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        flap(root);
        return root;
    }
    public void flap(TreeNode root){
        if (root == null){
            return;
        }
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        //递归调用
        flap(root.left);
        flap(root.right);
    }
}
