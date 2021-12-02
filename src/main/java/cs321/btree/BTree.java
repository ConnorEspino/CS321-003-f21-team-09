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
            s.insert(Key);
        } else {
            //BTreeInsertNonFull(r, Key);
            r.insert(Key);
        }
    }

    //good?
//    private void BTreeInsertNonFull(BTreeNode TreeNode, TreeObject key) throws BTreeException {
//        int i = TreeNode.getNumElements();
//        if(TreeNode.isLeaf() && i != degree){
//                TreeNode.insert(key);
//
////                DiskWrite(key);
//        } else if(TreeNode.isLeaf() && i == degree){
//            while(i > 1 && TreeNode.getElement(i).getKey() > key.getKey()){
//                i--;
//            }
//            i++;
////            DiskRead(TreeNode.getElement(i))
//            if(i == (2*degree-1)){
//                BTreeNode child = BTreeSplitChild(TreeNode, i);
//                if(key.getKey() > TreeNode.getElement(i).getKey()){
//                    i++;
//                }
//                BTreeInsertNonFull(child, key);
//            }
//        }
//    }

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
