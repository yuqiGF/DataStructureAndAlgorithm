package com.yuqiqi.dataStructure.tree.binarySearchTree;

/**
 * Binary Search Tree 二叉搜索树  普通的树节点上新增一个key属性（不能重复） 用来比较大小  ⭐类比MAP
 * 根节点的左边的节点的值一定比根节点小  根节点右边的值一定比根节点大
 * ⭐本二叉搜索树采用泛型实现
 */
public class BST<T extends Comparable<T>,V> {    //⭐这种写法是泛型的上限   泛型里面填的哪个类元素必须是实现了后面的 哪个接口的类
    /**
     * 二叉搜索树的节点类    ⭐就相当于值被挂在了键key的下面
     */
    static class BSTNode<T,V>{
        T key;
        V value;
        BSTNode<T,V> left;
        BSTNode<T,V> right;

        public BSTNode(T key) {
            this.key = key;
        }

        public BSTNode(T key, V value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(T key, V value, BSTNode<T,V> left, BSTNode<T,V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    BSTNode<T,V> root;  //根节点

    /**
     * 查找key对应的值  递归查找
     * @param key 关键字
     * @return 对应的值
     */
    public V get(T key){
        if (key == null){
            return null;
        }
        return doGet(root,key);  //调用递归起点
    }

    /**
     * 递归查找  需要传入当前的节点和键   key小了向左找  key大了向右找
     * @param node 节点
     * @param key 键
     * @return 结果
     */
    private V doGet(BSTNode<T,V> node, T key){
        if (node == null){
            return null; //没找到
        }
        /*
         * ⭐ CompareTo的返回值  为-1时 调用者小  为0时 相等  为1时 调用者大
         */

        if (key.compareTo(node.key) < 0){ //小了
            return doGet(node.left,key); //向左找  ⭐这这尾递归调用java无法对其自动优化，最好转化为循环实现，防止栈溢出
        }else if (key.compareTo(node.key) > 0){ //大了
            return doGet(node.right,key); //向右找
        }else {
            return node.value; //找到了
        }
    }

    /**
     * 迭代查找
     * @param key 键
     * @return 结果
     */
    private V doGet2(T key){
        BSTNode<T,V> node = root;
        while(node != null){
            if (key.compareTo(node.key) < 0){
                node = node.left; //向左找
            }else if (key.compareTo(node.key) > 0){
                node = node.right; //向右找
            }
            else {
                return node.value; //找到了
            }
        }
        return null; //没找到
    }

    /**
     * 查找最小关键字对应的值
     * @return 值
     */
    public V min(){
        return null;
    }

    /**
     * 查找最大关键字对应的值
     * @return 值
     */
    public V max(){
        return null;
    }

    /**
     * 查找节点的前任值  ⭐前驱值 所有比他大的（它右边的）节点中离他最近的
     * @return 值
     */
    public V predecessor(){
        return null;
    }

    /**
     * 查找节点的后任值  ⭐后继值
     * @return 值
     */
    public V successor(){
        return null;
    }

    /**
     * 删除key所对应的节点
     * @param key 关键字
     * @return 删除的key节点的值
     */
    public V delete(T key){
        return null;
    }
}
