package cs321.btree;

//Test
public class BTree{
    private BTreeNode[] BTree;
    int size;
    private int degree;

    public BTree(int degree) {
        BTree = new BTreeNode[4096];
        size = 0;
        this.degree = degree;
        BTreeNode x = new BTreeNode(degree);
 //       DiskWrite(x);
        setRoot(x);
    }

    public BTreeNode root() {
        BTreeNode root = BTree[0];
        return root;
    }

    public void setRoot(BTreeNode newRoot) {
        BTree[0] = newRoot;
    }


//good?
    public TreeObject BTreeSearch(BTreeNode TreeNode, TreeObject Key){
        int i = 1;
        while((i <= TreeNode.getNumElements()) && Key.getKey() > TreeNode.getElement(i).getKey()){
            i++;
        }
        if (i < TreeNode.getNumElements() && 0 == (Key.equals(TreeNode.getElement(i)))) {
            return (TreeNode.getElement(i));
        } else if(TreeNode.isLeaf()){
            return null;
        } else {
//            DiskRead(TreeNode.getChild(i));
            return BTreeSearch(TreeNode.getChild(i), Key);
        }
    }

//good?
    public void BTreeInsert(TreeObject Key) throws BTreeException {
        BTreeNode r = new BTreeNode(degree);
        r = root();
        if (r.getNumElements() == (2*degree)){
            BTreeNode s = new BTreeNode(degree);
            setRoot(s);
            s.setChild(0,r);
            BTreeSplitChild(s, 1);
            BTreeInsertNonFull(s, Key);
        } else {
            BTreeInsertNonFull(r, Key);
        }
    }

    //good?
    private void BTreeInsertNonFull(BTreeNode TreeNode, TreeObject key) throws BTreeException {
        int i = TreeNode.getNumElements();
        if(TreeNode.isLeaf() && i != degree){
                TreeNode.insert(key);

//                DiskWrite(key);
        } else if(TreeNode.isLeaf() && i == degree){
            while(i > 1 && TreeNode.getElement(i).getKey() > key.getKey()){
                i--;
            }
            i++;
//            DiskRead(TreeNode.getElement(i))
            if(i == (2*degree-1)){
                BTreeNode child = BTreeSplitChild(TreeNode, i);
                if(key.getKey() > TreeNode.getElement(i).getKey()){
                    i++;
                }
                BTreeInsertNonFull(child, key);
            }
        }
    }

    public BTreeNode BTreeSplitChild(BTreeNode TreeNode, int index){
        BTreeNode z = new BTreeNode(degree);
        BTreeNode y = new BTreeNode(degree);
        y = TreeNode.getChild(index);
        for(int j = 1; j< degree-1; j++){
            //z.key(j) = y.key(j+degree)
        }
        if(!y.isLeaf()){
            for(int j = 1; j < degree;j++){
                //z.child.key(j) = y.child.key(j+degree)
            }
        }
        //y.getNumElements() = degree-1;
//
        for(int j = TreeNode.getNumElements(); j > degree+1; j--){
            TreeNode.getChild(degree+j) = TreeNode.getChild(j);
        }
        TreeNode.getElement(index).getKey() = y.getElement(index).getKey();
        TreeNode.getNumElements()++;
        size++;
        return z;
    }

    //TODO
    public TreeObject[] getArrayOfNodeContentsForNodeIndex(int indexNode) {
        return null;
    }

    public int getNumberOfNodes() {
        return size;
    }

//    //TODO?
//    public int returnIndex(BTreeNode Object){
//        int index = -1;
//        for(int i = 0; i < size; i++){
//            if(BTree[i].equals(Object)){
//                index = i;
//            }
//        }
//        return index;
//    }
}
