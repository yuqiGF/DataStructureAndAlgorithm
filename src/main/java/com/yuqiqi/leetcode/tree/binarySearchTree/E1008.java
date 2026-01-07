package com.yuqiqi.leetcode.tree.binarySearchTree;

import com.yuqiqi.dataStructure.tree.TreeNode;

/**
 * 前序遍历构建二叉搜索树   给定一个整数数组，它表示BST(即 二叉搜索树 )的 先序遍历 ，构造树并返回其根。
 * 前提：至少有一个节点   且没有重复的节点
 */
public class E1008 {
    /**
     * 解法一  安好前序遍历的顺序依次插入即可   n lgn
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        if (preorder.length == 1){
            return root;
        }
        for (int i = 1; i < preorder.length; i++) {
            TreeNode p = root;
            TreeNode parent = null;
            while (p != null){
                if (preorder[i] < p.val){
                    parent = p;
                    p = p.left;
                }else {  //题目说了没有重复的
                    parent = p;
                    p = p.right;
                }
            }
            if (parent.val > preorder[i]){  //⭐记得用数值比较
                parent.left = new TreeNode(preorder[i]);
            }else {
                parent.right = new TreeNode(preorder[i]);
            }
        }
        return root;
    }
    /**
     * 解法二  上界递归法  O（n）   每次创建新节点的时候先判断下一个解释是否可以作为该节点的子节点，不能的话就返回一个null  每次创建的节点都得包含左右两个子节点（null也算）
     */

    /**
     * 解法三   ⭐分治   逐渐拆分  直到拆解到最小
     */
    public TreeNode bstFromPreorder2(int[] preorder){
        return partition(preorder,0,preorder.length - 1);
    }
    public TreeNode partition(int[] preorder , int start , int end){
        if (start > end){
            return null;
        }
        TreeNode root = new TreeNode(preorder[start]);  //⭐每次递归调用的时候都得创建新的节点
        int index = start + 1;  //找到分割点  因为是前序遍历  左右两边的东西都挨着
        while(index <= end){
            if (preorder[index] > root.val){
                break;  //找到了中间点位置
            }
            index ++;
        }
        root.left = partition(preorder,start + 1,index - 1);
        root.right = partition(preorder,index,end);
        return root;
    }
}
