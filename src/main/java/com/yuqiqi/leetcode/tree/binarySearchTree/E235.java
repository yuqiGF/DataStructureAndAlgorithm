package com.yuqiqi.leetcode.tree.binarySearchTree;

import com.yuqiqi.datastructure.tree.TreeNode;

/**
 * 二叉搜索树的最近公共祖先   给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class E235 {
    //思路： 遍历的时候找共同向左的时候或者共同向右的时候往下遍历  找分开的时候就是要的结果
    //⭐ 待查找节点在某一结点的两侧，则此节点就是最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null){
            return null;
        }
        TreeNode node = root;
        while(node != null){
            if (node.val > p.val && node.val > q.val){  //同小
                node = node.left;
            } else if (node.val < p.val && node.val < q.val) {  //同大
                node = node.right;
            }else {
                break;
            }
        }
        return node;
    }
}
