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
     * Sets the first int of the sequence to a new value.
     * 
     * @param newChar - New value to add (must be A, T, C, or G).
     * @return int - Returns zero on success, non-zero on fail.
     */
    public int setFirst(char newChar) {
        int ret;

        switch (newChar) {
            case 'A':
                list[0] = (DNA.A);
                ret = 0;
            break;
            case 'T':
                list[0] = (DNA.T);
                ret = 0;
            break;
            case 'C':
                list[0] = (DNA.C);
                ret = 0;
            break;
            case 'G':
                list[0] = (DNA.G);
                ret = 0;
            break;
            default:
                ret = 1;
        }

        return ret;
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
     * Sets the last int of the sequence to a new value.
     * 
     * @param newChar - New value to add (must be A, T, C, or G).
     * @return int - Returns zero on success, non-zero on fail.
     */
    public int setLast(char newChar) {
        int ret;

        switch (newChar) {
            case 'A':
                list[size-1] = (DNA.A);
                ret = 0;
            break;
            case 'T':
                list[size-1] = (DNA.T);
                ret = 0;
            break;
            case 'C':
                list[size-1] = (DNA.C);
                ret = 0;
            break;
            case 'G':
                list[size-1] = (DNA.G);
                ret = 0;
            break;
            default:
                ret = 1;
        }

        return ret;
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
     * Sets the specified int of the sequence to a new value.
     * 
     * @param i - Index of value to replace.
     * @param newChar - New value to add (must be A, T, C, or G).
     * @return int - Returns zero on success, non-zero on fail.
     */
    public int set(int i, char newChar) {
        int ret;

        switch (newChar) {
            case 'A':
                list[i] = (DNA.A);
                ret = 0;
            break;
            case 'T':
                list[i] = (DNA.T);
                ret = 0;
            break;
            case 'C':
                list[i] = (DNA.C);
                ret = 0;
            break;
            case 'G':
                list[i] = (DNA.G);
                ret = 0;
            break;
            default:
                ret = 1;
        }

        return ret;
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
        String ret = null;
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
    
}
