package com.yuqiqi.leetcode.linshan.basic;

import com.yuqiqi.datastructure.tree.TreeNode;

public class CheckTree {
    public boolean checkTree(TreeNode root) {
        return root.val == root.left.val + root.right.val;
    }
}
