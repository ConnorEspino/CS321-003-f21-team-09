package cs321.create;

/**
 * A class used for holding the inputed DNA sequence as integer values.
 * 
 * @author Ken R.
 * 
 */
public class DNASequence {
    private long list;
    private String sequenceString = "";

    /** 
     * Constructor for the DNASequence class, using a string
     *      as an input.
     * 
     * @param sequence - DNA sequence (string) to input.
     */
    public DNASequence(String sequence) {
        sequenceString = sequence;
        size = 0;
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
        }
    }

    /**
     * Construct for the DNASequence class, using a long
     *      as an input rather than a string.
     * 
     * @param sequence - DNA sequence (long) to input.
     */
    public DNASequence(Long sequence) {
        list = sequence;
    }

    /**
     * Returns the DNA sequence as a String.
     * 
     * @return String - The current DNA sequence in String form.
     */
    public String toString() {
        if(sequenceString == "") {
            String listString = String.valueOf(list);
            
            for (int i = 0; i < listString.length(); i++) {
                switch (listString.substring(i, i+1)) {
                    case "00":
                        sequenceString = sequenceString + 'A';
                    break;
                    case "11":
                        sequenceString = sequenceString + 'T';
                    break;
                    case "01":
                        sequenceString = sequenceString + 'C';
                    break;
                    case "10":
                        sequenceString = sequenceString + 'G';
                    break;
                }
                i++;
            }
        }

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
     * @return int - Returns 0 if both lists are equal, -1 if the first list is greater, 
     *      and 1 if the second list is greater.
     */
    public int equals(DNASequence otherList) {
        if (this.getLong() < otherList.getLong()) {
            return -1;
        } else if (this.getLong() > otherList.getLong()) {
            return 1;
        } else {
            return 0;
        }
    }
}
