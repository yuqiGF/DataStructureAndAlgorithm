package com.yuqiqi.leetcode.ds.tree.binaryTree;

import java.util.LinkedList;

/**
 * 后序表达式（逆波兰表达式）转二叉树  （只包含双联运算符）
 */
public class ExpressionTree {
    /**
     * 树节点类
     */
    public static class TreeNode{
        public String val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(String val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    /**
     * 思路： 遍历 往栈里面存  遇到数字 入栈  遇到运算符 出栈   建立好运算关系后（运算符）再入栈    （先弹栈的是右孩子）
     */
    public TreeNode constructExpressionTree(String[] tokens){
        LinkedList<TreeNode> stack = new LinkedList<>();
        for (String token : tokens) {
            switch (token){
                case "+","-","*","/" -> {
                    //构建树系节点
                    TreeNode right = stack.pop();
                    TreeNode left = stack.pop();
                    TreeNode node = new TreeNode(token, left, right);
                    stack.push(node); //再次入栈
                }
                default -> {
                    stack.push(new TreeNode(token,null,null));
                }
            }
        }
        //最后留在栈顶的就是根节点
        return stack.pop();
    }
}
