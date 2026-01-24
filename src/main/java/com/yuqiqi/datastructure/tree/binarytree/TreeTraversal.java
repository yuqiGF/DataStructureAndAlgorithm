package com.yuqiqi.datastructure.tree.binarytree;

import com.yuqiqi.datastructure.tree.TreeNode;

/**
 * ⭐树的遍历  （递归实现）
 * 广度优先遍历BFS： 用队列实现的层序遍历 E102
 * 深度优先遍历DFS：前序遍历（根 左 右）  中序遍历（左 根 右）  后续遍历（左 右 根）
 */
public class TreeTraversal {
    /**
     * 前序遍历   先访问节点的值  然后再访问左右节点
     */
    static void preOrder(TreeNode node){
        if (node == null){
            return; //递归结束条件
        }
        int val = node.val; //访问值
        System.out.print(val + " ");
        //递归遍历左右节点
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历   先访问左子树 再访问自己  再访问右子树
     */
    static void inOrder(TreeNode node){
        if (node == null){
            return;
        }
        inOrder(node.left); //先访问左
        int val = node.val; //再访问自己
        System.out.print(val + " ");
        inOrder(node.right); //再访问右
    }

    /**
     * 后序遍历  左右中
     */
    static void postOrder(TreeNode node){
        if (node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        int val = node.val;
        System.out.print(val + " ");
    }
}
