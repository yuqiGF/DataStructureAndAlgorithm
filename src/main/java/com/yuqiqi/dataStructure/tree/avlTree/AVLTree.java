package com.yuqiqi.dataStructure.tree.avlTree;

/**
 * AVL树  移动自平衡的二叉搜索树
 * 在新增或者删除的时候会自动判断节点左右两侧的高度差   如果高度差大于1， 则他会自己旋转  来使自己平衡
 */
public class AVLTree {
    /**
     * AVL树节点类
     */
    static class AVLNode{
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height = 1; //⭐高度

        public AVLNode(int key , Object value) {
            this.value = value;
            this.key = key;
        }

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right, int height) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = height;
        }
    }

    //根节点
    AVLNode root;

    /**
     * 获取任意节点的高度   包含null值的处理
     * @param node
     * @return
     */
    private int height(AVLNode node){
        return node == null ? 0 : node.height;
    }

    /**
     * （新增 删除 旋转时）更新节点的高度    找其左右节点中较高的一个 然后加一 （父节点肯定要比孩子节点高嘛）
     * 后面要逐个调用好几次的
     * @param node
     */
    private void updateHeight(AVLNode node){
        node.height = Math.max(height(node.left),height(node.right))+ 1;
    }


    /**
     * ⭐平衡因子   balance factor = 左子树的高度-右子树的高度
     * @param node 节点对象
     * @return 返回0 1 -1 都是平衡的    大于1 或 小于-1 是不平衡的   大于1 左边高   小于-1 右边高
     */
    private int balanceFactor(AVLNode node){
        return height(node.left) - height(node.right);
    }

    /**
     * 右旋   RR⭐肯定都是左边高  red的左节点是yellow yellow的右节点是green
     * @param red 失衡的节点
     * @return 右旋后的结果
     */
    private AVLNode rightRotate(AVLNode red){
        AVLNode yellow = red.left; //找左节点
        AVLNode green = yellow.right; //把左节点的右节点取出来
        yellow.right = red; //旋转
        red.left = green; //换爹   red的左节点原本是yellow 旋转后就没了  刚好yellow转过来以后可能会多出来一个右孩子  正好赋值给它
        updateHeight(red);  //⭐记得更新高度   左旋时高度只有本节点和其左节点会变     ⭐ 是高度  不是深度
        updateHeight(yellow);  //注意顺序  先更新低的  再更新高的
        return yellow;
    }

    /**
     * 左旋   LL⭐ 应对左节点的左子树更高的情况
     * @param red 失衡的节点
     * @return 左旋后的结果
     */
    private AVLNode leftRotate(AVLNode red){
        AVLNode yellow = red.right;  //找右节点 当作新的“根”
        AVLNode green = yellow.left; //右节点可能已经有左节点了  需要换爹
        yellow.left = red; //旋转
        red.right = green; //换爹
        updateHeight(red); //更新高度
        updateHeight(yellow);
        return yellow;
    }

    /**
     * 先左旋  再右旋   LR⭐应对失衡节点的左节点 的右子树比其左子树高的情况
     * 先左子树左旋  然后自己右旋
     * @param node
     * @return
     */
    private AVLNode leftRightRotate(AVLNode node){
        node.left = leftRotate(node.left);  //左子树左旋  左旋完之后根节点的左节点就变了
        return rightRotate(node);  //根节点右旋
    }

    /**
     * 先右旋  再左旋
     */
    private AVLNode rightLeftRotate(AVLNode node){
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    /**
     * 检查是否平衡  四种失衡的情况 LL  RR  LR  RL  失衡的话转回来
     * @param node 节点
     * @return 平衡后的节点
     */
    private AVLNode balance(AVLNode node){
        if (node == null){
            return null;
        }
        int balanceFactor = balanceFactor(node);
        //左大于右  可能是LL  LR
        if (balanceFactor > 1 && balanceFactor(node.left) >= 0){  // LL 失衡左边高度高  且左子树也是左边高度高
            return rightRotate(node);//右旋
        }else if (balanceFactor > 1 && balanceFactor(node.left) < 0){ //LR
            return leftRightRotate(node); //子树左旋 然后自己右旋
        }
        else if (balanceFactor < -1 && balanceFactor(node.right) <= 0){  //RR  ⭐考虑删除时的特殊情况 右边删没了 左节点两子树高度还都是1  这时候也要左旋 （加个等号）
            return leftRotate(node);
        }else if (balanceFactor < -1 && balanceFactor(node.right) > 0){ //RL
            return rightLeftRotate(node);
        }
        return node;
    }

    /**
     * 新增节点   记得保持树的平衡
     * @param key
     * @param value
     */
    public void put(int key , Object value){
        doPut(root,key,value);
    }

    /**
     * 递归新增操作
     */
    private AVLNode doPut(AVLNode node, int key , Object value){
        //没有的话插入
        if (node == null){
            return new AVLNode(key,value);
        }
        //已有元素的话更新
        if (node.key == key){
            node.value = value;
            return node;
        }
        //找空位
        if (key < node.key){
            node.left = doPut(node.left,key,value);  //记得更新节点
        }else {
            node.right = doPut(node.right,key,value);
        }
        updateHeight(node);  //更新高度  更新了高度之后可能失衡
        return balance(node);  //返回平衡后的节点
    }

    /**
     * 删除
     */
    public void remove(int key){
        root = doRemove(root, key);
    }

    /**
     * 递归删除   注意这个需要更新高度
     */
    private AVLNode doRemove(AVLNode node, int key){
        //没找到  直接返回null
        if (node == null){
            return null;
        }
        if (key < node.key){
            node.left = doRemove(node.left,key);  //记得更新节点
        } else if (key > node.key) {
            node.right = doRemove(node.right,key);
        }else {
            //找到了  1 没孩子  2 只有一个孩子  3俩孩子都有
            if (node.left == null && node.right == null){
                return null;
            }
            else if (node.left == null){
                node = node.right;  //被删除后剩下的元素暂存到node里 后面跟新高度
            }else if (node.right == null){
                node = node.left;
            }else {
                //找后继 来替换它   右子树的最小值
                AVLNode s = node.right;
                while(s.left != null){
                    s = s.left;
                }
                s.right = doRemove(node.right,s.key);  //后继节点和本节点不挨着的时候 处理后继节点的后事 相当于把该删的这个节点的右子树中删掉了后继节点那个位置的节点
                s.left = node.left;  //后继节点上位
                node = s;  //返回删除完后剩下的  这里临时赋值给node  后续处理高度问题
            }
        }
        //更新高度
        updateHeight(node);
        //平衡检查
        return balance(node);
    }
}
