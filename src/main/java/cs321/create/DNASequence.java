package cs321.create;

/**
 * A class used for holding the inputed DNA sequence as integer values.
 * 
 * @author Ken R.
 * 
 */
public class DNASequence {
    private String sequenceString;
    private int size;
    private long list;

    /** 
     * Constructor for the DNASequence class.
     * 
     * @param sequence - DNA sequence (string) to input
     */
    public DNASequence(String sequence) {
        sequenceString = sequence;
        for (int i = 0; i < sequence.length(); i++) {
            list = list << 2;
            switch (sequence.charAt(i)) {
                case 'A':
                    list |= (DNA.A);
                break;
                case 'T':
                    list |= (DNA.T);
                break;
                case 'C':
                    list |= (DNA.C);
                break;
                case 'G':
                    list |= (DNA.G);
                break;
            }
            size++;
        }
    }

    /**
     * Returns the size of the sequence as an int value.
     * 
     * @return int - Size of the sequence.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the DNA sequence as a String.
     * 
     * @return String - The current DNA sequence in String form.
     */
    public String toString() {
        return sequenceString;
    }

    /**
     * Returns the DNA sequence as a Long.
     * 
     * @return long - The listed DNA sequence.
     */
    public long getLong() {        
        return list;
    }

    /**
     * Compares two DNASequence objects, to see if they are equal.
     * 
     * @param otherList - The list to compare to.
     * @return boolean - True or false depending on the results of the given objects.
     */
    public boolean equals(DNASequence otherList) {

        if (this.getSize() != otherList.getSize()) {
            return false;
        }

        return this.equals(otherList);
    }
    
}
