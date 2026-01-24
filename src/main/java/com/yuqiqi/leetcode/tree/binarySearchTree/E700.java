package com.yuqiqi.leetcode.tree.binarySearchTree;

import com.yuqiqi.datastructure.tree.TreeNode;

/**
 * 二叉搜素树查找   给定二叉搜索树（BST）的根节点 root 和一个整数值 val。
 * 你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null 。
 */
public class E700 {
    /**
     * 递归法
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null){
            return null;
        }
        if (root.val == val){
            return root;
        }
        if (val < root.val){
            return searchBST(root.left,val);
        }
        return searchBST(root.right, val);
    }

    /**
     * 迭代
     */
    public TreeNode searchBST2(TreeNode root, int val){
        TreeNode p = root;
        while(p != null){
            if (val < p.val){
                p = p.left;
            }else if (val > p.val){
                p = p.right;
            }else {
                return p;
            }
        }
        return null;
    }
}
