package com.yuqiqi.leetcode.tree.binaryTree;

import com.yuqiqi.dataStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树中序遍历
 */
public class E94 {
    /**
     * 递归法
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        recursion(list,root);
        return list;
    }

    public void recursion(List<Integer> list , TreeNode root){
        if (root == null){
            return;
        }
        recursion(list,root.left);
        list.add(root.val);
        recursion(list,root.right);
    }

    /**
     * 迭代法  （统一写法）
     */
    public List<Integer> inorderTraversal2(TreeNode root){
        List<Integer> list = new ArrayList<>();//结果数组
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        TreeNode pop = null;
        while(current != null || !stack.isEmpty()){
            if (current != null){  //处理左边
                stack.push(current);
                current = current.left;
            }else {  //处理右边
                TreeNode peek = stack.peek(); //取出栈顶来判断
                if (peek.right == null){
                    list.add(peek.val);
                    pop = stack.pop();
                }
                else if (peek.right == pop){
                    pop = stack.pop();
                }else {
                    //处理右边
                    list.add(peek.val);
                    current = peek.right;
                }
            }
        }
        return list;
    }
}
