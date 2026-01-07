package com.yuqiqi.leetcode.tree.binarySearchTree;

import com.yuqiqi.dataStructure.tree.TreeNode;

/**
 * 删除二叉搜索树中的节点  给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * 一般来说，删除节点可分为两个步骤：
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 */
public class E450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            return null;
        }
        //找到节点
        TreeNode p = root;
        TreeNode parent = null;
        while(p != null){
            if (key < p.val){
                parent = p;
                p = p.left;
            }else if (key > p.val){
                parent = p;
                p = p.right;
            }else {
                break;
            }
        }
        if (p == null){
            return root;        //找到了p
        }

        //⭐加一层判断  处理只有根节点一个节点的情况    因为java是值传递   不能直接改
        if (parent == null){  //要删的就是根节点
            if (p.left == null && p.right == null){
                return null;  //⭐
            }else if (p.left == null){
                root = p.right;
            }else if (p.right == null){
                root = p.left;
            }else {  //根节点左右两边都存在
                TreeNode s = p.right;
                TreeNode sp = p;
                while(s.left != null){
                    sp = s;
                    s = s.left;
                }
                if (sp != p){  //被删除节点的后继的父节点不是被删除节点  证明他俩不挨着
                    //这时候要把s的右子树全部托孤给s的父亲sp
                    shift(sp,s,s.right);
                    s.right = p.right;
                }
                root = s;
                s.left = p.left;
            }
        }

        //1、左子树为空或者右子树为空
        else if (p.left == null){
            shift(parent,p,p.right);
        } else if (p.right == null) {
            shift(parent,p,p.left);
        }else {
            //都不为空  找后继节点 右子树中的最小值   记得处理后继节点s的后事
            TreeNode s = p.right;
            TreeNode sp = p;
            while(s.left != null){
                sp = s;
                s = s.left;
            }
            if (sp != p){  //被删除节点的后继的父节点不是被删除节点  证明他俩不挨着
                //这时候要把s的右子树全部托孤给s的父亲sp
                shift(sp,s,s.right);
                s.right = p.right;
            }
            shift(parent,p,s);
            s.left = p.left;
        }
        return root;
    }

    private void shift(TreeNode parent , TreeNode p , TreeNode child){
        if (parent.left == p){
            parent.left = child;
        }
        else if (parent.right == p){
            parent.right = child;
        }
    }
}
