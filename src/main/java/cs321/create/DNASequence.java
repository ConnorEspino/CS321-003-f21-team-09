package cs321.create;

/**
 * A class used for holding the inputed DNA sequence as integer values.
 * 
 * @author Ken R.
 * 
 */
public class DNASequence {
    private int size;
    private int list[];

    /** 
     * Constructor for the DNASequence class.
     * 
     * @param sequence - DNA sequence (string) to input
     */
    public DNASequence(String sequence) {
        list = new int[sequence.length()-1];
        for (int i = 0; i < sequence.length(); i++) {
            switch (sequence.charAt(i)) {
                case 'A':
                    list[i] = (DNA.A);
                break;
                case 'T':
                    list[i] = (DNA.T);
                break;
                case 'C':
                    list[i] = (DNA.C);
                break;
                case 'G':
                    list[i] = (DNA.G);
                break;
            }
            size++;
        }
    }

    /**
     * Returns the first int in the sequence.
     * 
     * @return int - The first int in the list. 
     */
    public int getFirst() {
        return list[0];
    }


    /**
     * Returns the last int in the sequence.
     * 
     * @return int - The last int in the list. 
     */
    public int getLast() {
        return list[size-1];
    }


    /**
     * Returns the int in the sequence at the specified index.
     * 
     * @param index - Index of the specified int.
     * @return int - The int in the list at the given index.
     */
    public int get(int index) {
        return list[index];
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
        String ret = "";
        for (int i = 0; i < list.length; i++) {
            switch (list[i]) {
                case 0b00:
                    ret = ret + 'A';
                break;
                case 0b11:
                    ret = ret + 'T';
                break;
                case 0b01:
                    ret = ret + 'C';
                break;
                case 0b10:
                    ret = ret + 'G';
                break;
            }
        }

        return ret;
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

        for (int i = 0; i < this.getSize(); i++) {
            if (this.get(i) != otherList.get(i)) {
                return false;
            }
        }

        return true;
    }
    
}