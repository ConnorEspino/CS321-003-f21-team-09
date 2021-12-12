package cs321.create;

/**
 * Utility methods dealing with DNA sequences and its compact representation as long variables.
 * 
 * @author Connor Espino
 */
public class SequenceUtils{

    /**
     * Converts a DNA string to a long of pairs of binary values
     * @param DNAString The string to convert to binary
     * @return long The binary representation of the given DNA sequence
     */
    public static long DNAStringToLong(String DNAString)  {
        long dnaLong = 0;
        for (int i = 0; i < DNAString.length(); i++) {
            dnaLong = dnaLong << 2;
            switch (DNAString.charAt(i)) {
                case 'A':
                    dnaLong |= (DNA.A);
                    break;
                case 'T':
                    dnaLong |= (DNA.T);
                    break;
                case 'C':
                    dnaLong |= (DNA.C);
                    break;
                case 'G':
                    dnaLong |= (DNA.G);
                    break;
            }
        }

        return dnaLong;
    }

    /**
     * Converts a binary long to a string of DNA bases.
     * @param sequence The long sequence to convert to a string
     * @param seqLength The length of the binary values stored in the long
     * @return String The string representation of the given long sequence
     */
    public static String longToDNAString(long sequence, int seqLength) {
        StringBuilder dnaString = new StringBuilder();
        for(int i = 0; i < seqLength; i++){ 
            int temp = (int) ((sequence & (0b11 << 2 * i)) >> 2 * i);
            switch(temp){
                case DNA.A:
                    dnaString.append("A");
                    break;
                case DNA.T:
                    dnaString.append("T");
                    break;
                case DNA.C:
                    dnaString.append("C");
                    break;
                case DNA.G:
                    dnaString.append("G");
                    break;
            }
        }
        return dnaString.reverse().toString();
    }


    /**
     * Returns the complement of the given long sequence
     * @param sequence The DNA sequence as a binary long
     * @param seqLength The number of bits that are used as part of the Sequence
     * @return long The complement of the given DNA long sequence
     */
    public static long getComplement(long sequence, int seqLength) {
        long retSeq = ~sequence;
        long andSeq = 0;

        //Create a binary sequence "mask" to only get the number of bits specified by the seqLength
        for(int i = 0; i < seqLength; i++){
            andSeq = andSeq << 1;
            andSeq++;
        }

        return retSeq & andSeq;
    }
}