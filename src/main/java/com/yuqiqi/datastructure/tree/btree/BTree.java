package com.yuqiqi.datastructure.tree.btree;

import java.util.Arrays;

/**
 * B树  一种更适合于存储的数据结构   可以有效缩短树高  减少磁盘io
 * 度：degree：树中节点孩子数  t
 * 阶：order：所有孩子节点数的最大值
 * 孩子数：ceil（m / 2） ~ m
 * 关键字数：ceil（m / 2） - 1 ~ m - 1
 */
public class BTree {
    static class Node{
        int[] keys; //关键字  不止一个
        Node[] children; //节点  不止一个
        int keyNumber; //有效关键字数
        boolean leaf = true;  //是否是叶子节点
        int t;  //树的最小度数  最小孩子数

        public Node(int t) {  //t >= 2
            this.t = t;
            this.children = new Node[2 * t];  //最小度数 * 2 就是最大孩子数
            keys = new int[2 * t - 1];
        }

        //辅助方法  打印有效关键字
        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys,0,keyNumber));
        }

        //辅助方法 多路查找 找key
        Node get(int key){
            int i = 0; //key指针  用来确定孩子的位置
            while(i < keyNumber){
                if (keys[i] == key){
                    return this;  //找到了
                }
                if (keys[i] > key){
                    break;  //找到比他大的了  退出 到下一个节点中去找
                }
                i ++;  //比节点中的key小的话继续找
            }
            //此时keys[i] > key 或者 i == keyNumber
            if (leaf){
                return null; //如果查找的是叶子节点 直接就没了
            }
            //非叶子节点情况
            return children[i].get(key);  //直接到第i个孩子中去查找
        }

        //向指定索引插入key
        void insertKey(int key , int index){
            System.arraycopy(keys,index,keys,index + 1,keyNumber - index);  //向后拷贝一位
            keys[index] = key; //插入key
            keyNumber ++;
        }

        //向指定索引插入child
        void insertChild(Node child , int index){
            System.arraycopy(children, index, children, index + 1, keyNumber + 1 - index);
            children[index] = child;
        }

        //移除指定index处的key
        int removeKey(int index){
            int t = keys[index];
            System.arraycopy(keys,index + 1 , keys, index , --keyNumber - index);
            return t;
        }

        //删除左边的key
        int removeLeftmostKey(){
            return removeKey(0);
        }

        //删除最右边的key
        int removeRightmostKey(){
            return removeKey(keyNumber - 1);
        }

        //移除指定位置的child
        Node removeChild(int index) {
            Node removed = children[index];
            System.arraycopy(children, index + 1, children, index, keyNumber + 1 - index - 1);
            children[keyNumber] = null;
            return removed;
        }


        //移除最左边的child
        Node removeRightmostChild(){
            return removeChild(keyNumber);
        }

        //移除最右边的child
        Node removeLeftmostChild(){
            return removeChild(0);
        }

        //index孩子处左边的兄弟
        Node childLeftSibling(int index){
            return index > 0 ? children[index - 1] : null;
        }

        //index孩子处右边的兄弟
        Node childRightSibling(int index){
            return index == keyNumber ? null : children[index + 1];
        }

        //复制当前所有的key和value到target中
        void moveToTarget(Node target){
            int start = target.keyNumber;
            if (!leaf){ //非叶子的话复制child
                for (int i = 0; i <= keyNumber ; i++) {
                    target.children[start + i] = children[i];  //追加 不覆盖
                }
            }
            for (int i = 0; i < keyNumber; i++) {
                target.keys[target.keyNumber ++] = keys[i];
            }
        }
    }

    Node root;  //根节点
    int t;  //树中节点的最小度数
    final int MAX_KEY_NUMBER;  //最大key
    final int MIN_KEY_NUMBER;  //最小key

    public BTree() {
        this(2);  //无参时默认调用有参 指定默认值为2 不能再小了
    }

    public BTree(int t) {
        this.t = t;
        root = new Node(t);
        MAX_KEY_NUMBER = 2 * t - 1;
        MIN_KEY_NUMBER = t - 1; //最下key就是孩子数 - 1
    }

    /**
     * 判断一个key是否存在
     */
    public boolean contains(int key){
        return root.get(key) != null;
    }

    /**
     * 新增一个key   查找本节点中的插入位置   如果没有空位的话 更新
     * 有空位的话  如果是叶子节点  直接插入   如果是非叶子节点  继续再children[i]处递归插入
     * ⭐记得限制拆入的最大值  达到最大值后要进行分列split
     */
    public void put(int key){
        doPut(root,key,null,0);
    }

    //递归插入
    private void doPut(Node node , int key , Node parent , int index){
        int i = 0;
        while(i < node.keyNumber){
            if (node.keys[i] == key){
                return;  //更新逻辑
            }
            if (node.keys[i] > key){
                break;  //找到了
            }
            i ++;
        }
        if (node.leaf){
            node.insertKey(key,i);  //插入
        } else {
            doPut(node.children[i],key,node,i); //递归插入
        }
        if (node.keyNumber == MAX_KEY_NUMBER){
            split(node, parent, index);
        }
    }

    /**
     * 分裂   将该节点的key分为大中小三分（t以后的  t-1 t-1之前的） 中间的去到父节点中  然后右节点去新节点  左边的留下 （注意每个节点的最小度数为t）
     * @param left 本节点
     * @param parent 父节点
     * @param index 分列节点在父节点中的索引位置    （child的索引和key的索引是挂钩的）
     */
    private void split(Node left , Node parent , int index){
        if (parent == null){  //⭐特殊情况分列的是根节点的话需要创建一个新的根  把原来的根拷贝进去作为parent
            Node newRoot = new Node(t);
            newRoot.leaf = false; //不再是叶子节点了
            newRoot.insertChild(left,0);
            this.root = newRoot;
            parent = newRoot;
        }
        //1 创建right节点  然后叭left中t之后的key和child移动过去
        Node right = new Node(t);  //右节点
        right.leaf = left.leaf;  //分列前是非叶子  分裂后也是非叶子
        System.arraycopy(left.keys,t,right.keys,0,t - 1);  //将一般的元素个数拷贝到right中
        if (!left.leaf){  //如果不是叶子节点的话需要把孩子也一起拷贝过去
            System.arraycopy(left.children,t,right.children,0,t);  //孩子数比key数多一个
        }
        right.keyNumber = t - 1;  //设置有效上限
        left.keyNumber = t - 1;  //设置有效上限
        //2 中间的t-1处的节点插入到父节点中
        int mid = left.keys[t - 1];
        parent.insertKey(mid,index);
        //3 right节点作为父节点的孩子插入到index + 1处
        parent.insertChild(right,index + 1);
    }

    /**
     * 删除一个key
     */
    public void remove(int key){
        doRemove(root,key, null , 0);
    }

    //递归删除
    private void doRemove(Node node , int key , Node parent , int index){
        int i = 0;
        while(i < node.keyNumber){
            if (node.keys[i] >= key){
                break;  //找到了
            }
            i ++;  //退出循环  在第i个孩子里继续查找
        }
        // i双重含义  待删除key索引   到第i个孩子中继续查找
        if (node.leaf){
            if (!found(node, key, i)){ //⭐case1 叶子 且没找到
                return;
            }else { //⭐case2 叶子 且找到了
                node.removeKey(i);
            }
        }else {
            if (!found(node, key, i)){ //⭐case3 非叶子 且没找到
                doRemove(node.children[i] , key , node, i);  //到第i个孩子中查找
            }else { //⭐case4 非叶子 且找到了
                //找后继
                Node s = node.children[i + 1]; //比它大的一个孩子
                while(!s.leaf){ //非叶子
                    s = s.children[0];  //继续从最左边的孩子里找
                }
                int sKey = s.keys[0];  //找到了
                //替换待删除的key
                node.keys[i] = sKey;
                //删除后继key
                doRemove(node.children[i + 1],sKey , node , i + 1);  //递归操作  相当于在子树中删除掉了后继的key
            }
        }
        //删除完之后key小于下限
        if (node.keyNumber < MIN_KEY_NUMBER){
            //调整平衡  case5 case6
            balance(parent,node,index);
        }
    }

    //调整平衡   x是节点数不够的那个节点
    private void balance(Node parent , Node x , int i){
        //⭐case6 根节点
        if (x == root){
            if (root.keyNumber == 0 && root.children[0] != null){
                root = root.children[0];  //删掉了根节点 换根即可
            }
        }
        Node left = parent.childLeftSibling(i);  //左兄弟
        Node right = parent.childRightSibling(i);  //右兄弟
        if (left != null && left.keyNumber > MIN_KEY_NUMBER){
            //⭐case5-1 左边富裕  右旋  （父节点中有一个去右边  左边的节点中有一个去到父节点）
            x.insertKey(parent.keys[i - 1],0);  //把父节点中的前驱key旋转下来   被旋转的key恰好是本节点索引位置减一的值
            if (!left.leaf){ //如果左边的兄弟右孩子的话
                x.insertChild(left.removeRightmostChild(),0);  // 把最右边的孩子放到被调整的节点中的最左边
            }
            parent.keys[i - 1] = left.removeRightmostKey();  //左边兄弟最大的key旋转上去
            return;
        }
        if (right != null && right.keyNumber > MIN_KEY_NUMBER){
            //⭐case5-2 右边富裕  左旋
            x.insertKey(parent.keys[i],x.keyNumber);  //父节点中的后继key旋转下来 插入到最右侧
            if (!right.leaf){
                x.insertChild(right.removeLeftmostChild(),x.keyNumber + 1);  //右兄弟的最小节点换爹
            }
            parent.keys[i] = right.removeLeftmostKey();  //右边兄弟的最小key转上去
            return;
        }
        //⭐case5-3 两边都不够借  向左合并
        if (left != null){
            //向左兄弟合并
            parent.removeChild(i);
            left.insertKey(parent.removeKey(i - 1), left.keyNumber);  //被调整索引处减一  合并到左节点
            x.moveToTarget(left);  //复制当前所有节点到target
        }else {
            //没有左兄弟就向自己合并  右兄弟不要了
            parent.removeChild(i + 1); //把右边移除
            x.insertKey(parent.removeKey(i),x.keyNumber);  //key下来   合并到自己的最右边
            right.moveToTarget(x);
        }
    }

    //判断是否找到了
    private static boolean found(Node node, int key, int i) {
        return i < node.keyNumber && node.keys[i] == key;
    }
}
