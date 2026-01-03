package com.yuqiqi.leetcode.tree.binaryTree;

import com.yuqiqi.dataStructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最大深度   （教材的深度比力扣少1）
 */
public class E104 {
    /**
     * 解法一   递归   就是后续遍历的应用
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0; //没有节点
        }
        //递归比较左右节点  取最大的
        int d1 = maxDepth(root.left);
        int d2 = maxDepth(root.right);
        return Integer.max(d1,d2) + 1;  //从这里选左右子树深度的最大值 + 1计数
    }

    /**
     * 我的递归
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        int i = 0;
        return check(0,root);
    }
    public int check(int i , TreeNode node){
        if (node == null){
            return i;
        }
        i ++; //有一个计一个
        int a = check(i,node.left);
        int b = check(i,node.right);
        return Math.max(a, b);
    }

    /**
     * 解法二  迭代法  记录栈的最大深度
     */
    public int solution2(TreeNode root){
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        TreeNode pop = null;
        int size = 0;
        while(curr != null || !stack.isEmpty()){
            if (curr != null){
                stack.push(curr);
                size = Math.max(stack.size(), size); //计数的size更新为栈的最大深度
                curr = curr.left; //向左遍历
            }else { //向右的时候要弹栈了
                TreeNode peek = stack.peek();
                if (peek.right == null){
                    pop = stack.pop();
                }
                if (peek.right == pop){  //上次弹出过
                    pop = stack.pop();
                }else {
                    curr = peek.right;
                }
            }
        }
        return size;
    }

    /**
     * 方法三  层序遍历   队列实现 每一层先出队 再入队
     */
    public int solution3(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        int h = 0;
//        int c1 = 1; //每层的元素个数
//        int c2 = 0; //这俩变量可以直接用队列的size替代掉
        //入队
        queue.offer(root);
        while(!queue.isEmpty()){
            h ++;
            int size = queue.size();  //每次取队列的大小就能直到要循环几次了（本层有几个元素）
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll(); //出队
                if (poll.left != null){
                    queue.offer(poll.left);
                }
                if (poll.right != null){
                    queue.offer(poll.right);
                }
            }
        }
        return h;
    }
}
