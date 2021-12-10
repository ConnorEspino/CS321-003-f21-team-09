package cs321.btree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.Queue;

//Test
public class BTree{
    private BTreeNode[] BTree;
    int size;
    private int degree;
    private RandomAccessFile file;
    private long address;
    public int seqLength;

    public BTree(int degree, RandomAccessFile file, long address) throws IOException {
        BTree = new BTreeNode[4096];
        size = 0;
        this.degree = degree;
        this.file = file;
        this.address = address + getOffset();
        BTreeNode x = new BTreeNode(degree, file, address);
        x.diskWrite(null);
        setRoot(x);
        seqLength = 1;
    }

    public BTreeNode root() {
        BTreeNode root = BTree[0];
        return root;
    }

    public void setRoot(BTreeNode newRoot) {
        BTree[0] = newRoot;
    }


    //good?
    public TreeObject BTreeSearch(BTreeNode TreeNode, TreeObject Key) throws BTreeException, IOException {
        int i = 1;
        while((i <= TreeNode.getNumElements()) && Key.getKey() > TreeNode.getElement(i).getKey()){
            i++;
        }
        if (i < TreeNode.getNumElements() && 0 == (Key.compareTo(TreeNode.getElement(i)))) {
            return (TreeNode.getElement(i));
        } else if(TreeNode.isLeaf()){
            return null;
        } else {
            BTreeNode nodeReturn = TreeNode.diskRead(TreeNode.getChildAddress(i), null);
            return BTreeSearch(nodeReturn, Key);
        }
    }

    public void BTreeInsert(TreeObject Key) throws BTreeException, IOException {
        BTreeNode r = root();
        if (r.getNumElements() == (2*degree)-1){
            address += getOffset();
            BTreeNode s = new BTreeNode(degree, file, address);
            setRoot(s);
            s.setChildAddress(0, r.getAddress());
            s.BTreeSplitChild(0);
            size+=2;
            s.insertNonFull(Key);
        } else {
            if(size == 0){
                size++;
            }
            r.insertNonFull(Key);
            r.diskWrite(null);
        }
    }

    public BTreeNode getArrayOfNodeContentsForNodeIndex(int indexNode) throws BTreeException, IOException {
        address += getOffset();
        BTreeNode retVal = new BTreeNode(degree, file, address);
        LinkedList<BTreeNode> nodesChecked = new LinkedList<BTreeNode>();
        LinkedList<BTreeNode> BFS = new LinkedList<BTreeNode>();
        nodesChecked.add(root());
        BFS.add(root());
        address += getOffset();
        BTreeNode thisNode = new BTreeNode(degree, file, address);
        while(nodesChecked.size() <= indexNode){
            thisNode = thisNode.diskRead(BFS.removeFirst().getAddress(), null);
//            thisNode = BFS.removeFirst();
            if(!thisNode.isLeaf()){
                for(int i = 0; i < thisNode.getNumChildren(); i++){
                    BTreeNode tempNode = thisNode.diskRead(thisNode.getChildAddress(i), null);
                    BFS.add(tempNode);
                    nodesChecked.add(tempNode);
                }
            }
        }
        retVal = thisNode.diskRead(nodesChecked.get(indexNode).getAddress(), null);
        return retVal;
    }

    public void inOrderTraversalPrint() throws IOException, BTreeException {
        BTreeNode root = root();
        root.diskWrite(null);
        root.inOrderTraversal(root);
    }

    public int getNumberOfNodes() {
        return size;
    }

    /**
     * Returns the address offset, calculated by finding the max used space of a node on the disk
     * @return long Address offset
     */
    public long getOffset(){
        //Return the max size used by a node with degree
        return (33 + (2*degree - 1)*(96) + (2*degree) * (64));
    }

}
