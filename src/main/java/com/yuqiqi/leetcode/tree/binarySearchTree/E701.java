package com.yuqiqi.leetcode.tree.binarySearchTree;

import com.yuqiqi.dataStructure.tree.TreeNode;

/**
 * 二叉搜索树新增 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 */
public class E701 {
    /**
     * 迭代法
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null){
            root = new TreeNode(val);
            return root;
        }
        TreeNode p = root;
        TreeNode parent = null;
        while(p != null){
            if (val < p.val){
                parent = p;
                p = p.left;
            }else if (val > p.val){
                parent = p;
                p = p.right;
            } //题目里以及保证了不相同了
        }
        if (val < parent.val){
            parent.left = new TreeNode(val);
        }else {
            parent.right = new TreeNode(val);
        }
        return root;
    }

    /**
     * 递归法
     */
    public TreeNode insertIntoBST2(TreeNode root , int val){
        if (root == null){
            return new TreeNode(val);  //找到空位了  返回
        }
        if (val < root.val){
            root.left = insertIntoBST2(root.left,val);
        } else if (val > root.val) {
            root.right = insertIntoBST2(root.right,val);
        }
        return root;
    }
}
