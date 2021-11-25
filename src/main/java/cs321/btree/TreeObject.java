package cs321.btree;
import cs321.create.DNASequence;

public class TreeObject{
    public BTreeNode left;
    public BTreeNode right;
    int key;

    public TreeObject(DNASequence seq) {
        key = seq.getInt();
        left = null;
        right = null;
    }

    public int getKey(){
        return key;
    }

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

    public void setLeft(BTreeNode child){
        left = child;
    }

    public BTreeNode getLeft(){
        return left;
    }

    public void setRight(BTreeNode child){
        right = child;
    }

    public BTreeNode getRight(){
        return right;
    }
}
