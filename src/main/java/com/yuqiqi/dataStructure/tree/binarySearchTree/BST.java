package com.yuqiqi.dataStructure.tree.binarySearchTree;

/**
 * Binary Search Tree 二叉搜索树  普通的树节点上新增一个key属性（不能重复） 用来比较大小  ⭐类比MAP
 * 根节点的左边的节点的值一定比根节点小  根节点右边的值一定比根节点大
 * ⭐本二叉搜索树采用泛型实现
 */
public class BST<K extends Comparable<K>,V> {    //⭐这种写法是泛型的上限   泛型里面填的哪个类元素必须是实现了后面的 哪个接口的类
    /**
     * 二叉搜索树的节点类    ⭐就相当于值被挂在了键key的下面
     */
    static class BSTNode<K,V>{
        K key;
        V value;
        BSTNode<K,V> left;
        BSTNode<K,V> right;

        public BSTNode(K key) {
            this.key = key;
        }

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(K key, V value, BSTNode<K,V> left, BSTNode<K,V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    BSTNode<K,V> root;  //根节点

    /**
     * 查找key对应的值  递归查找
     * @param key 关键字
     * @return 对应的值
     */
    public V get(K key){
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
    private V doGet(BSTNode<K,V> node, K key){
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
    private V doGet(K key){
        BSTNode<K,V> node = root;
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
     * 查找最小关键字对应的值   一路向左 找到最左边的哪个值就是最小值
     * @return 值
     */
    public V min(){
        return min(root);
    }

    /**
     * 从某节点开始找最小值
     * @param node
     * @return
     */
    private V min(BSTNode<K,V> node){
        if (node == null){
            return null;
        }
        BSTNode<K,V> p = root;
        while(p.left != null){
            p = p.left;
        }
        return p.value;
    }

    /**
     * 查找最小值  递归实现
     */
    private V doMin(BSTNode<K,V> node){
        //特殊条件  没有节点的情况
        if (node == null){
            return null;
        }
        if (node.left == null){
            return node.value; //结束条件
        }
        return doMin(node.left);
    }

    /**
     * 查找最小值   非递归实现
     */
    private V doMin(){
        if (root == null){
            return null;
        }
        BSTNode<K,V> node = root;
        while(node.left != null){
            node = node.left;
        }
        return node.value;
    }

    /**
     * 查找最大关键字对应的值   一路向右 直到走到头  和查找最小值基本一模一样
     * @return 值
     */
    public V max(){
        return max(root);
    }

    /**
     * 找任意一个节点为起点的最大值
     * @param node
     * @return
     */
    private V max(BSTNode<K,V> node){
        if (node == null){
            return null;
        }
        BSTNode<K, V> p = node;
        while(p.right != null){
            p = p.right;
        }
        return p.value;
    }

    /**
     * 查找最大值，仅非递归实现    递归也行 但是终究被优化
     */
    private V doMax(){
        if (root == null){
            return null;
        }
        BSTNode<K , V> node = root;
        while(node.right != null){
            node = node.right;
        }
        return node.value;
    }

    /**
     * 插入元素  （只在树的末尾插入）   如果树里有的话 直接修改值   如果没有的话 创建新的树节点
     * @param key 插入的键（位置）
     * @param value 插入的值
     */
    public void put(K key , V value){
        if (root == null){  //树是空的   直接创建为根节点
            root = new BSTNode<>(key, value);
            return;
        }
        BSTNode<K,V> node = root;
        //加一个变量记录parent父节点
        BSTNode<K,V> parent = null;
        while(node != null){
            parent = node;  //记录
            if (key.compareTo(node.key) < 0){
                node = node.left;
            }else if (key.compareTo(node.key) > 0){
                node = node.right;
            }
            else {
                //找到了  （原本就有）
                node.value = value;  //直接更新node
                return;
            }
        }
        //没找到  则在此位置（node = null）创建新节点
        if (key.compareTo(parent.key) > 0){  //找到要在父亲节点的左边创建还是右边创建
            parent.right = new BSTNode<>(key, value);
        }else{
            parent.left = new BSTNode<>(key, value);
        }

    }

    /**
     * 查找节点的前任值  ⭐前驱值 所有比他大的（它右边的）节点中离他最近的
     * @return 值
     * ⭐⭐二叉搜索树的中序遍历的结果就是排序结果  找前后任排个序就行了 （但是这样做效率不好 得用俩指针）
     */
    public V predecessor(K key){
        /*
         * 情况1 该节点有左子树  则前任就是其左子树中的最大值
         * 情况2 该节点没有左子树  则前任是其上面第一个从左边连过来的父节点
         */
        //先得找到该节点
        if (root == null){
            return null;
        }
        BSTNode<K,V> p = root;
        BSTNode<K,V> ancestorFromLeft = null;  //⭐记录自左而来的节点
        while(p != null){
            if (key.compareTo(p.key) > 0){
                ancestorFromLeft = p; //记录向右走的节点  也就是自左而来的节点
                p = p.right;
            }else if (key.compareTo(p.key) < 0){
                p = p.left;
            }else {
                break; //找到了
            }
        }
        if (p == null){ //没找到
            return null; //找到的p为空  根节点为空  直接返回
        }
        //找到了
        if (p.left != null){  //左子树不为空
            return max(p); //找以该节点为起点的最大值
        }
        //左子树为空
        return ancestorFromLeft == null ? null : ancestorFromLeft.value;
    }

    /**
     * 查找节点的后任值  ⭐后继值
     * @return 值
     */
    public V successor(K key){
        /*与前任同理
        情况1：有右子树时  后任节点就是右子树中的最小值
        情况2：没有右子树时  后任节点就是最近的从右而来的祖先节点
         */
        if (root == null){
            return null;
        }
        BSTNode<K,V> p = root;
        BSTNode<K,V> ancestorFromRight = null;  //⭐记录自右而来的节点
        while(p != null){
            if (key.compareTo(p.key) > 0){
                p = p.right;
            }else if (key.compareTo(p.key) < 0){
                ancestorFromRight = p; //记录向左走的节点  也就是自右而来的节点
                p = p.left;
            }else {
                break; //找到了
            }
        }
        if (p == null){ //没找到
            return null; //找到的p为空  根节点为空  直接返回
        }
        //找到了
        if (p.right != null){  //右子树不为空
            return min(p.right);  //右子树的最小值
        }
        //右子树为空
        return ancestorFromRight == null ? null : ancestorFromRight.value;
    }

    /**
     * 删除key所对应的节点
     * @param key 关键字
     * @return 删除的key节点的值
     */
    public V delete(K key){
        return null;
    }
}
