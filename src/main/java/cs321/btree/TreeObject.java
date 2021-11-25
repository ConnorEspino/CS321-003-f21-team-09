package cs321.btree;
import cs321.create.DNASequence;

public class TreeObject{
    public BTreeNode left;
    public BTreeNode right;
    long key;

    /**
     * Constructor for Tree object
     * @param seq The DNASequence to store in the tree object
     */
    public TreeObject(DNASequence seq) {
        //Store the DNASequence as a binary long
        key = seq.getLong();
        left = null;
        right = null;
    }

    /**
     * Allows us to access the key which lets us compare to other elements and sort correctly in the Btree
     * 
     * @return long The binary representation of the DNASequence
     */
    public long getKey(){
        return key;
    }

    /**
     * Indicates whether another object is less than, equal to, or greater than this one
     * 
     * @param obj The object to compare to this object 
     * @return int 0 if equal, -1 if less than, 1 if greater than the compared to object
     */
    public int equals(TreeObject obj) {
        if (key == obj.getKey())
            return 0;
        else if (key < obj.getKey())
            return -1;
        else if (key > obj.getKey())
            return 1;
        else 
            return 0;
    }

    /**
     * Allows us to set the left child pointer of this TreeObject
     * 
     * @param child The BTreeNode that is less than the current object
     */
    public void setLeft(BTreeNode child){
        left = child;
    }

    /**
     * Allows us to get the left child pointer of this TreeObject
     * 
     * @return BTreeNode The BTreeNode that is less than the current object
     */
    public BTreeNode getLeft(){
        return left;
    }

    /**
     * Allows us to set the right child pointer of this TreeObject
     * 
     * @param child The BTreeNode that is greater than the current object
     */
    public void setRight(BTreeNode child){
        right = child;
    }

    /**
     * Allows us to get the right child pointer of this TreeObject
     * 
     * @param child The BTreeNode that is greater than the current object
     */
    public BTreeNode getRight(){
        return right;
    }
}
