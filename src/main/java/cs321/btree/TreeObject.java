package cs321.btree;

import cs321.create.DNASequence;

/**
 * Class for creating aa tree object to store DNASequences, and frequency of a DNA Sequence.
 * 
 * @author Connor Espino
 */
public class TreeObject implements Comparable<TreeObject>{
    private DNASequence seq;
    private int frequency;
    private int seqLength;

    /**
     * Constructor for Tree object
     * 
     * @param seq The DNASequence to store in the tree object
     */
    public TreeObject(DNASequence seq) {
        // Store the DNASequence
        this.seq = seq;
        frequency = 0;
        seqLength = seq.toString().length();
    }

    /**
     * Overloaded constructor for Tree object
     * 
     * @param seq  The DNASequence to store in the tree object
     * @param freq The frequency of how often this DNA Sequence was inserted into
     *             the BTree
     */
    public TreeObject(DNASequence seq, int freq) {
        // Store the DNASequence as a binary long
        this.seq = seq;
        frequency = freq;
    }

    /**
     * Allows us to access the key which lets us compare to other elements and sort
     * correctly in the Btree
     * 
     * @return long The binary representation of the DNASequence
     */
    public long getKey() {
        return seq.getLong();
    }

    /**
     * Indicates whether another object is less than, equal to, or greater than this
     * one
     * 
     * @param obj The object to compare to this object
     * @return int 0 if equal, -1 if less than, 1 if greater than the compared to
     *         object
     */
    public int compareTo(TreeObject obj) {
        if (seq.getLong() == obj.getKey())
            return 0;
        else if (seq.getLong() < obj.getKey())
            return -1;
        else if (seq.getLong() > obj.getKey())
            return 1;
        else
            return 0;
    }

    /**
     * Indicates whether another tree object is equal to this one by comparing the two key values.
     * 
     * @param obj The other tree object to compare to this one
     * @return boolean True if the objects are equal, false otherwise
     */
    public boolean equals(TreeObject obj){
        return seq.getLong() == obj.getKey();
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

    public String toString(){
        String sequenceString = "";
        String listString = Long.toBinaryString(seq.getLong());
        
        for (int i = 0; i < listString.length(); i++) {
            if(listString.equals("0")){
                sequenceString = sequenceString + 'A';
            }else if(listString.equals("11")){
                sequenceString = sequenceString + 'T';
            }else if(listString.equals("1")){
                sequenceString = sequenceString + 'C';
            }else if(listString.equals("10")){
                sequenceString = sequenceString + 'G';
            }
            i++;
        }

        return sequenceString;
    }

    public int getSequenceLength(){
        return seqLength;
    }
}
