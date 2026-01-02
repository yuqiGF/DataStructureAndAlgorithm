package com.yuqiqi.leetcode.binaryTree;

import com.yuqiqi.dataStructure.binaryTree.TreeNode;

import java.util.Arrays;

/**
 * 根据中序后续遍历的结果构建二叉树
 */
public class E106 {
    /**
     * 递归   方法和E105一模一样    后续遍历结果确定根节点位置   中序遍历结果确定左右节点相对位置
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder.length == 0){
            return null;
        }
        int rootVal = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootVal);
        //找根节点在中序结果中的位置  构建新的左右子树数组
        for (int i = 0; i < inorder.length; i++) {
            if (rootVal == inorder[i]){
                //找到位置了
                int[] inLeft = Arrays.copyOfRange(inorder,0,i);
                int[] inRight = Arrays.copyOfRange(inorder,i + 1,postorder.length);

                int[] postLeft = Arrays.copyOfRange(postorder, 0, i);  //⭐记得左开右闭
                int[] postRight = Arrays.copyOfRange(postorder,i,postorder.length - 1);
                //⭐左右子树分别递归  递归结果就是根节点的左右子树
                root.left = buildTree(inLeft,postLeft);
                root.right = buildTree(inRight,postRight);
                break;
            }
        }
        return root;
    }
}
