package com.yuqiqi.leetcode.ds.tree.binarySearchTree;

import com.yuqiqi.datastructure.tree.TreeNode;

import java.util.LinkedList;

/**
 * 验证二叉搜索树  给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 严格小于 当前节点的数。
 * 节点的右子树只包含 严格大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树
 */
public class E98 {
    /**
     * 迭代法   递归会让问题变得复杂    ⭐思路：二叉搜索树的中序遍历的结果为升序， 中序遍历该树判断是否为升序即可
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null){
            return false;
        }
        TreeNode p = root;
        long prev = Long.MIN_VALUE;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while(p != null || !stack.isEmpty()){
            if (p != null){
                stack.push(p);
                p = p.left;
            }else {
                TreeNode pop = stack.pop(); //弹栈 开始归
                if (prev >= pop.val){
                    return false;
                }
                prev = pop.val;
                p = pop.right;
            }
        }
        return true;
    }

    /**
     * 解法二： 中序遍历  递归版
     * @param node
     * @return
     */
    long prev = Long.MIN_VALUE;
    public boolean isValidBST2(TreeNode node){
        if (node == null){
            return true;
        }
        boolean a = isValidBST2(node.left);//左子树也要为真
        if (!a){ //左子树以及为false了就不用往后走了
            return false;  //⭐二叉树剪枝   提前做判断  避免不必要的操作
        }
        //值 也要为真
        if (prev >= node.val){
            return false;
        }
        prev = node.val;
        return isValidBST2(node.right);//右子树也要为真     前面左子树已经判断了  这里只判断右子树即可
    }

    /**
     * 解法三  ⭐上下界递归    给每个节点指定上下界  然后递归操作即可
     */
    public boolean doValid(TreeNode node , long min , long max){
        if (node == null){
            return true;
        }
        if (node.val <= min || node.val >= max){
            return false;
        }
        boolean a = doValid(node.left, min, node.val);  //判断左边
        boolean b = doValid(node.right, node.val, max);  //判断右边
        return a && b;
    }
}
