package com.yuqiqi.leetcode.tree.binarySearchTree;

import com.yuqiqi.datastructure.tree.TreeNode;

import java.util.LinkedList;

/**
 * 二叉搜索树的范围和    给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 */
public class E938 {
    //思路：依旧是中序遍历  找符合的值
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null){
            return 0;
        }
        TreeNode p = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int sum = 0;
        while(p != null || !stack.isEmpty()){
            if (p != null){
                stack.push(p);
                p = p.left;
            }else {
                TreeNode pop = stack.pop();
                if (pop.val >= low && pop.val <= high){
                    sum += pop.val;
                }else if (pop.val > high){
                    return sum;  //剪枝
                }
                p = pop.right;
            }
        }
        return sum;
    }

    /**
     * 解法2  ⭐上下界递归   剪枝递归
     */
    public int rangeSumBST2(TreeNode root , int low ,int high){
        if (root == null){
            return 0;
        }
        if (root.val < low){  //左边超界了
            return rangeSumBST2(root.right,low,high);
        }
        if (root.val > high){
            return rangeSumBST2(root.left,low,high);
        }
        return root.val + rangeSumBST2(root.right,low,high) + rangeSumBST2(root.left,low,high);
    }
}
