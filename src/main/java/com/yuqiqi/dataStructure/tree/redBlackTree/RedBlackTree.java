package com.yuqiqi.dataStructure.tree.redBlackTree;

/**
 * 红黑树  一种自平衡的二叉搜索树    相较于AVL树  红黑树插入和删除时需要更少次数的旋转操作
 * 特性：
 * 1、所有节点都有两种颜色  红色  黑色  （插入时默认是红色）
 * 2、所有的null视为黑色
 * 3、红色节点不能相邻
 * 4、根节点是黑色
 * 5、从根节点到任意一个叶子节点经过的黑色节点数量一致
 */
public class RedBlackTree {
    //节点颜色
    enum Color{
        RED,BLACK
    }

    //根节点
    private Node root;

    //节点类
    private static class Node{
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;  //父节点
        Color color = Color.RED;  //颜色

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Node(int key, Object value, Node left, Node right, Node parent, Color color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.color = color;
        }

        //是否是左孩子
        boolean isLeftChild(){
            return parent != null && parent.left == this;  //父节点不为空  且该节点是父节点的左节点
        }

        //叔叔节点    和父亲平级的其他节点
        Node uncle(){
            if(parent == null || parent.parent == null){  //父亲节点不为空  且爷爷节点不为空（有爷爷才能有父亲和叔叔）
                return null;
            }
            if (parent.isLeftChild()){
                return parent.parent.right;  //父亲是爷爷的左孩子  那么叔叔就是爷爷的右孩子
            }else {
                return parent.parent.left;
            }
        }

        //兄弟节点
        Node sibling(){
            if (parent == null){
                return null;  //没爹自然就没有兄弟了
            }
            if (this.isLeftChild()){
                return parent.right;
            }else {
                return parent.left;
            }
        }
    }

    /**
     * //判断节点是否为红色   （更好的处理null值）
     * @param node 节点
     * @return 是否为红色
     */
    boolean isRed(Node node){
        return node != null && node.color == Color.RED;
    }

    /**
     * 判断是否为黑色
     * @param node 节点
     * @return 是否为黑色
     */
    boolean isBlack(Node node){
//        return !isRed(node);
        return node == null || node.color == Color.BLACK;  //规则怪谈  空节点也是黑色的
    }

    /**
     * 右旋 1、parent处理   2、旋转后新根和更上层根的父子关系
     * @param pink 待旋转节点
     */
    private void rightRotate(Node pink){
        Node parent = pink.parent; //⭐取到parent
        Node yellow = pink.left;  //待处理节点的左节点 （后续要成为根）
        Node green = yellow.right;  //右节点的右节点（要换爹的）
        //⭐处理parent属性
        if (green != null){
            green.parent = pink;  //green有时候是不存在的
        }
        yellow.parent = parent;  //黄色的爹变成原来粉色的爹
        pink.parent = yellow;  //粉色的爹旋转后变为了粉色
        //正常旋转换爹
        yellow.right = pink;  //⭐旋转   yellow成为新根
        pink.left = green;  //换爹
        //⭐处理和更上层的根节点的关系
        if (parent == null){
            root = yellow;  //父节点为空，即要换的就是根节点  那么直接换根就行了
        }
        else if (parent.left == pink){
            parent.left = yellow;
        }else {
            parent.right = yellow;
        }
    }

    /**
     * 左旋
     * @param pink 带旋转节点
     */
    private void leftRotate(Node pink){
        Node yellow = pink.right;
        Node parent = pink.parent;
        Node green = yellow.left;
        //parent节点
        if (green != null){
            green.parent = pink;
        }
        yellow.parent = parent;
        pink.parent = yellow;
        //旋转  换爹
        yellow.left = pink;
        pink.right = green;
        //处理更上层的根
        if (parent == null){
            root = yellow;  //要左旋的就是根节点  直接换根
        }else if (parent.left == pink){
            parent.left = yellow;
        }else {
            parent.right = yellow;
        }
    }

    /**
     * 新增节点  遇到相同key时替换   默认插入的节点是红色的  注意红黑树规则  ⭐修复红红冲突
     * @param key
     * @param value
     */
    public void put(int key , Object value){
        Node p = root; //节点指针
        Node parent = null;
        while(p != null){
            if (key < p.key){
                parent = p;
                p = p.left;
            }else if (key > p.key){
                parent = p;
                p = p.right;
            }else {
                //找到了
                p.value = value;
                return;
            }
        }
        //没找到  新增   找p的父节点去
        Node inserted = new Node(key, value);
        if (parent == null){
            root = inserted;  //插入的就是根节点
        }else if (key < parent.key){  //⭐切记这里拿key判断  用==判断的话会出现问题 第一次恒为true
            parent.left = inserted;
            inserted.parent = parent;
        }else {
            parent.right = inserted;
            inserted.parent = parent;
        }
        fixRedRed(inserted);
    }

    /**
     * ⭐调整双红  插入的是红节点 其父节点也是红节点的情况（红红挨着了  不符合红黑树平衡条件）
     * @param node 待调整节点
     */
    void fixRedRed(Node node){
        //⭐插入节点默认是红色的
        //case1 插入的是根节点   那么此节点变黑
        if (node == root){
            node.color = Color.BLACK;
            return;
        }
        //case2 插入的节点的父亲为黑色，那么不用变
        if (isBlack(node.parent)){
            return;
        }
        //⭐插入节点的父节点是红节点  此时触发红红相邻
        Node parent = node.parent;
        Node uncle = node.uncle();
        Node grandparent = parent.parent;
        //case3 父亲节点为红色 且 叔叔节点为红色：  此时只需⭐变色
        // 1 父亲变为黑色
        // 2 为了保证黑色平衡，叔叔也变为黑色
        // 3 祖父必为黑色，此时又会导致黑色不平衡，那么祖父就变为红色
        // 4 此时祖父又会触发红红相邻 对祖父进行递归调整
        if (isRed(uncle)){
            parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            grandparent.color = Color.RED;
            fixRedRed(grandparent);  //祖父作为触发红红相邻的元素去递归调用
            return;
        }

        //case4 父亲节点为红色 且 叔叔节点为黑色   此时要⭐旋转+变色  四种情况
        //1 父亲为左孩子，插入节点也是左孩子  此时触发LL不平衡   父亲变黑 祖父变红 祖父右旋
        if (parent.isLeftChild() && node.isLeftChild()){
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
            rightRotate(grandparent);
            return;
        }
        //2 父亲为左孩子，插入节点为右孩子  此时触发LR不平衡  先让父亲节点左旋 然后“父亲”变黑 祖父变红 祖父右旋
        else if (parent.isLeftChild()){
            leftRotate(parent);  //父亲左旋后变成了LL的场景
            node.color = Color.BLACK;  //旋转后node变成了“父亲”
            grandparent.color = Color.RED;
            rightRotate(grandparent); //祖父右旋
        }
        //3 父亲为右孩子，插入节点是右孩子  触发RR不平衡   左旋
        else if (!node.isLeftChild()){
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
            leftRotate(grandparent);
            return;
        }
        //4 父亲为右孩子，插入节点是左孩子  触发RL不平衡  先让祖父的右节点（父节点）右旋 然后变色 然后祖父自己左旋
        else {
            rightRotate(parent);
            node.color = Color.BLACK;
            grandparent.color = Color.RED;
            leftRotate(grandparent);
        }
    }

    /**
     * 删除节点   李代桃僵 处理黑黑不平衡
     */
    public void remove(int key){
        Node deleted = find(key);
        if (deleted == null){
            return;
        }
        //找到了
        doRemove(deleted,key);
    }

    /**
     * 递归删除   同样是四种情况  没孩子  一个孩子（左/右）  俩孩子
     */
    private void doRemove(Node deleted, int key) {
        Node replaced = findReplace(deleted);
        if (replaced == null){ //没有孩子
            if (deleted == root){  //case1 删的是根节
                root = null;     //直接变为null清空树即可
            }


            return;
        }
        if (deleted.left == null || deleted.right == null){  //有一个孩子
            if (deleted == root){  //case1 删除的是根  ⭐此时只可能是只有两个节点的情况  不然就不平衡了
                root.key = replaced.key; //此时让被替换的那个节点当根节点
                root.value = replaced.value;  //⭐注意java的值传递机制限制
                root.left = root.right = null;  //清空左右节点  只剩下根了
            }


            return;
        }
        //有两个孩子
        // ⭐⭐⭐李代桃僵   让待删除节点的键和值与其后继节点替换  然后要删的元素就变成了其后继节点 （只可能没有孩子或者只有任意一棵子树）
        //极大的简化啊！！！！   ⭐注意 java的值传递机制  不能直接替换node  但是可以替换里面的元素
        int k = deleted.key;
        deleted.key = replaced.key;
        replaced.key = k;

        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v;
        //替换完之后 要删的就变成了它的后继replaced了
        doRemove(replaced,key);
    }

    /**
     * 查找删除的节点
     */
    private Node find(int key){
        Node p = root;
        while(p != null){
            if (key < p.key){
                p = p.left;
            }else if (key > p.key){
                p = p.right;
            }else {
                return p;
            }
        }
        return null;
    }

    /**
     * 查找删剩下的孩子节点
     */
    private Node findReplace(Node deleted){
        if (deleted.left == null && deleted.right == null){
            return null;
        }else if (deleted.left == null){
            return deleted.right;
        }else if (deleted.right == null){
            return deleted.left;
        }else {  //左右都不为空
            //找后继
            Node s = deleted.right;
            while(s.left != null){
                s = s.left;
            }
            return s;  //返回后继节点  为⭐李代桃僵替换方法做准备
        }
    }

}
