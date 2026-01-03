package com.yuqiqi.dataStructure.tree.binaryTree;

import com.yuqiqi.dataStructure.tree.TreeNode;

import java.util.LinkedList;

/**
 * ⭐树的遍历  （迭代实现）
 */
public class TreeTraversal2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4, null, null), null),
                new TreeNode(3,
                        new TreeNode(5, null, null), new TreeNode(6, null, null)));
        test(root);
    }

    /**
     * 迭代遍历 （前序  中序）  去的时候的节点就是⭐前序遍历结果   回来的时候的节点就是⭐中序遍历（此时根节点直接被弹出了）
     * @param root
     */
    public static void test(TreeNode root){
        //初始化栈
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()){  //节点非空或栈里有东西的时候
            if (current != null){ //节点非空
                System.out.println("go" + current); //打印自己   ⭐前序遍历
                //入栈  记住回来的路
                stack.push(current);
                current = current.left; //向左迭代
            }else { //节点是空的  指走到头了 该回去了
                TreeNode pop = stack.pop();
                System.out.println("back" + pop.val);  //⭐中序遍历
                current = pop.right; //⭐ 点睛之笔  让current更新为弹出元素的右节点   如果有的话再次循环的时候就能再次入栈
            }
            //⭐⭐⭐后续遍历要再弹栈的时候加一些条件  不是直接弹出  而是要再右节点处理完之后（为null或者刚才弹出过）才能弹出
        }
    }

    /**
     * ⭐⭐⭐统一迭代法 绕树一周 遍历！
     */
    public static void traversal(TreeNode node){
        //初始化栈
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = node; //节点指针
        //⭐记录刚刚出栈的节点
        TreeNode pop = null;
        while(curr != null || !stack.isEmpty()){ //开始遍历
            if (curr != null){
                stack.push(curr); //入栈
                //待处理左子树
                System.out.println("前"+curr.val);   //⭐前序遍历  （这里也可以用peek）
                curr = curr.left;
            }else {
                TreeNode peek = stack.peek(); //获取当前栈顶
                //右子树为空
                if (peek.right == null){
                    System.out.println("中" + peek.val); //⭐中序遍历 （右子树为空时也需要打印中序结果）
                    pop = stack.pop(); //出栈并记录
                    System.out.println("后" + pop.val);  //⭐后续遍历
                }
                //右子树处理完成  （右节点刚刚弹出过）
                else if (peek.right == pop) {
                    pop = stack.pop();
                    System.out.println("后" + pop.val);  //⭐后续遍历
                }
                //待处理右子树
                else {
                    System.out.println("中" + peek.val); //⭐中序遍历  （左子树处理完 ，右子树处理前，此时该元素未弹出 所以用peek）
                    curr = peek.right;
                }
            }
        }
    }
}
