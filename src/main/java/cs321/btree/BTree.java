package cs321.btree;
//Test
public class BTree<E> extends TreeObject<E>{
    private cs321.btree.TreeObject<E>[] BTree;
    int cap = 100;
    int size;

    public BTree(E key1, int pos1) {
        super(key1, pos1);
        BTree = new TreeObject[cap];
        size = 0;
    }

    public cs321.btree.TreeObject<E>[] expandCap() {
        size *= 2;
        cs321.btree.TreeObject<E>[] BTreeCopy = new TreeObject[cap];;
        for(int i = 0; i < size/2; i++){
            BTreeCopy[i] = BTree[i];
        }
        return BTreeCopy;
    }

    public cs321.btree.TreeObject<E> root() {
        TreeObject<E> root = BTree[0];
        return root;
    }

    public boolean isLeaf(cs321.btree.TreeObject<E> TreeObject) {
        boolean leaf = false;
        if(TreeObject.left == null && TreeObject.right == null){
            leaf = true;
        }
        return leaf;
    }

    public void inOrderTreeWalk(cs321.btree.TreeObject<E> TreeObject){
        if (TreeObject != null) {
            inOrderTreeWalk(TreeObject.left);
            System.out.println(TreeObject.getKey());
            inOrderTreeWalk(TreeObject.right);
        }
    }

    public cs321.btree.TreeObject<E> treeSearch(cs321.btree.TreeObject<E> TreeObject,E newKey){
        if(TreeObject == null | TreeObject.equal(newKey) == 0) {
            return TreeObject;
        }
//        if (TreeObject.key < newKey) {
        if (TreeObject.equal(newKey) > 0) {
            return treeSearch(TreeObject.left, newKey);
        } else {
            return treeSearch(TreeObject.right, newKey);
        }
    }
    public cs321.btree.TreeObject<E> IterativeTreeSearch(cs321.btree.TreeObject<E> TreeObject, E newKey){
        while(TreeObject != null && TreeObject.key != newKey) {
            if (TreeObject.equal(newKey) < 0) {
                TreeObject = TreeObject.left;
            } else {
                TreeObject = TreeObject.right;
            }
        }
        return TreeObject;
    }

    public cs321.btree.TreeObject<E> treeMin(cs321.btree.TreeObject<E> TreeObject){
        while(TreeObject.left != null) {
            TreeObject = TreeObject.left;
        }
        return TreeObject;
    }

    public cs321.btree.TreeObject<E> treeMax(cs321.btree.TreeObject<E> TreeObject){
        while(TreeObject.right != null) {
            TreeObject = TreeObject.right;
        }
        return TreeObject;
    }

    public cs321.btree.TreeObject<E> treeSuccessor(cs321.btree.TreeObject<E> TreeObject){
        if (TreeObject.right != null) {
            return treeMin(TreeObject.right);
        }
        TreeObject<E> parent = TreeObject.parent;
        while(parent != null && (TreeObject.getKey() == parent.right.getKey())) {
            TreeObject = parent;
            parent = parent.parent;
        }
        return parent;
    }

    public TreeObject<E> treePredecessor(cs321.btree.TreeObject<E> TreeObject){
        if (TreeObject.left != null) {
            return treeMax(TreeObject.left);
        }
        TreeObject<E> parent = TreeObject.parent;
        while(parent != null && (TreeObject.getKey() == parent.left.getKey())) {
            TreeObject = parent;
            parent = parent.parent;
        }
        return parent;
    }
    public void treeInsert(cs321.btree.TreeObject<E> NewObject){
        TreeObject<E> newParent = null;
        TreeObject<E> root = root();
        if(BTree.length >= size-1){
            BTree = expandCap();
        }
        while(root != null) {
            newParent = root;
//          if (NewObject.key < root.key) {
            if (NewObject.equal(root.key) < 0) {
                    root = root.left;
                } else {
                    root = root.right;
                }
            size++;
        }
        NewObject.parent = newParent;
        if(newParent == null) {
            BTree[0] = NewObject;
//      } else if(NewObject.key < newParent.key){
        } else if(NewObject.equal(newParent.key) < 0){
            newParent.left = NewObject;
        } else {
            newParent.right = NewObject;
        }
    }

    public void transplant(cs321.btree.TreeObject<E> u, cs321.btree.TreeObject<E> v){
        if(u.parent == null) {
            BTree[0] = v;
        } else if(u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if(v != null) {
            v.parent = u.parent;
        }
    }

    public void treeDelete(cs321.btree.TreeObject<E> newObject){
        if(newObject.left == null) {
            transplant(newObject, newObject.right);
        } else if (newObject.right == null) {
            transplant(newObject, newObject.left);
        } else {
            newObject.parent = treeMin(newObject.right);
        }
        if(newObject.parent.parent != newObject) {
            transplant(newObject.parent, newObject.parent.right);
        }
        newObject.parent.right = newObject.right;
        newObject.parent.right.parent = newObject.parent;
        transplant(newObject, newObject.parent);
        newObject.parent.left = newObject.left;
        newObject.parent.left.parent = newObject.parent;
        size--;
    }

}
