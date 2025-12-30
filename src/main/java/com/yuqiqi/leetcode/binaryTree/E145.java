package com.yuqiqi.leetcode.binaryTree;

import com.yuqiqi.dataStructure.binaryTree.TreeNode;

import java.lang.runtime.TemplateRuntime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树后序遍历
 */
public class E145 {
    /**
     * 递归法
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recursion(list , root);
        return list;
    }
    public void recursion(List<Integer> list , TreeNode root){
        if (root == null){
            return;
        }
        recursion(list , root.left);
        recursion(list , root.right);
        list.add(root.val);
    }

    /**
     * 统一迭代法
     */
    public List<Integer> postorderTraversal2(TreeNode root){
        List<Integer> list = new ArrayList<>();
        //栈
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        TreeNode pop = null;
        while(curr != null || !stack.isEmpty()){
            if (curr != null){
                //左
                stack.push(curr);
                curr = curr.left;
            }else {
                //右
                TreeNode peek = stack.peek();
                if (peek.right == null){
                    pop = stack.pop();
                    list.add(pop.val);
                }else if (peek.right == pop){
                    pop = stack.pop();
                    list.add(pop.val);
                }else {
                    curr = peek.right;
                }
            }
        }
        return list;
    }
}
