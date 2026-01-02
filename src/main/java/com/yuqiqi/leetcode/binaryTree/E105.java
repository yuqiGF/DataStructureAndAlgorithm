package com.yuqiqi.leetcode.binaryTree;

import com.yuqiqi.dataStructure.binaryTree.TreeNode;

import java.util.Arrays;

/**
 * 根据前中序遍历的结果构建二叉树    前序遍历确定根节点   中序遍历根据根节点的位置确定其他节点的相对位置   然后拆分成左右子树去递归
 */
public class E105 {
    /**
     * 递归法
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0){
            return null;
        }
        //找到根节点
        int rootVal = preorder[0];
        TreeNode root = new TreeNode(rootVal);
        for (int i = 0; i < inorder.length; i++) {
            if (rootVal == inorder[i]){
                //找到了 0~i-1 是左树  i+1~inorder.length是右子树
                //创建新的中序子树集合
                int[] inLeft = Arrays.copyOfRange(inorder, 0, i); //根据区间复制数组  左开右闭
                int[] inRight = Arrays.copyOfRange(inorder, i + 1, inorder.length);
                //创建新的前序子树集合
                int[] preLeft = Arrays.copyOfRange(preorder, 1, i + 1);
                int[] preRight = Arrays.copyOfRange(preorder,i + 1,preorder.length);
                //⭐递归
                root.left = buildTree(preLeft,inLeft); //返回左子树的根节点
                root.right = buildTree(preRight,inRight); //返回右子树的根节点
                break;
            }
        }
        return root;
    }
}
