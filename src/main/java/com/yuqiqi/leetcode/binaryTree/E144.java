package com.yuqiqi.leetcode.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.yuqiqi.dataStructure.binaryTree.TreeNode;

/**
 * 二叉树前序遍历
 */
public class E144 {
    /**
     * 递归法  非常直观好用
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        funstion(list,root);
        return list;
    }
    private void funstion(List<Integer> list , TreeNode root){
        if(root == null){
            return;
        }
        list.add(root.val);
        funstion(list, root.left);
        funstion(list, root.right);
    }

    /**
     * 迭代法（非统一写法）   ⭐用栈来存储遍历数据
     */
    public List<Integer> preorderTraversal2(TreeNode root){
        //初始化栈
        LinkedList<TreeNode> stack = new LinkedList<>();
        //结果数组
        List<Integer> list = new ArrayList<>();
        TreeNode current = root; //遍历指针
        while (current != null || !stack.isEmpty()){  //节点非空或栈里有东西的时候
            if (current != null){ //节点非空
//                System.out.println("go" + current); //打印自己
                list.add(current.val);
                //入栈  记住回来的路
                stack.push(current);
                current = current.left; //向左迭代
            }else { //节点是空的  指走到头了 该回去了
                TreeNode pop = stack.pop();
//                System.out.println("back" + pop.val);
                current = pop.right; //⭐ 点睛之笔  让current更新为弹出元素的右节点   如果有的话再次循环的时候就能再次入栈
            }
        }
        return list;
    }
}
