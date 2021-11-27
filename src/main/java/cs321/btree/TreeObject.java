package cs321.btree;

import cs321.create.DNASequence;

public class TreeObject {
    private long key;
    private int frequency;

    /**
     * Constructor for Tree object
     * 
     * @param seq The DNASequence to store in the tree object
     */
    public TreeObject(DNASequence seq) {
        // Store the DNASequence as a binary long
        key = seq.getLong();
        frequency = 0;
    }

    /**
     * Allows us to access the key which lets us compare to other elements and sort
     * correctly in the Btree
     * 
     * @return long The binary representation of the DNASequence
     */
    public long getKey() {
        return key;
    }

    /**
     * Indicates whether another object is less than, equal to, or greater than this
     * one
     * 
     * @param obj The object to compare to this object
     * @return int 0 if equal, -1 if less than, 1 if greater than the compared to
     *         object
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
     * Increases the frequency of the DNASequence in the BTree
     */
    public void increaseFrequency() {
        frequency++;
    }

    /**
     * Allows us to get how many times the given DNASequence has been attempted to
     * be inserted
     * 
     * @return int The frequency of the DNA Sequence in the BTree
     */
    public int getFrequency() {
        return frequency;
    }
}
