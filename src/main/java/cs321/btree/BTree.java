package cs321.btree;

import java.io.IOException;
import java.io.RandomAccessFile;

//Test
public class BTree{
    private BTreeNode[] BTree;
    int size;
    private int degree;
    private RandomAccessFile file;
    private long address;

    public BTree(int degree, RandomAccessFile file, long address) throws IOException {
        BTree = new BTreeNode[4096];
        size = 0;
        this.degree = degree;
        this.file = file;
        this.address = address;
        BTreeNode x = new BTreeNode(degree, file, address);
        x.diskWrite();
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
            BTreeNode nodeReturn = TreeNode.diskRead(TreeNode.getChildAddress(i));
            return BTreeSearch(nodeReturn, Key);
        }
    }

    public void BTreeInsert(TreeObject Key) throws BTreeException, IOException {
            BTreeNode r = BTree.root();
            if (r.getNumElements() == (2*degree)){
                BTreeNode s = new BTreeNode(degree, file, address);
                setRoot(s);
                s.setChildAddress(1, r.getAddress());
                s.BTreeSplitChild(s,1);
                s.insertNonFull(Key);
            } else {
                r.insertNonFull(Key);
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
