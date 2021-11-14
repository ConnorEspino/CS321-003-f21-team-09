package cs321.create;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GeneBankFileReader {
    private File file;
    private Scanner scan;
    private int seqLength;

    /**
     * Constructor for GeneBankFileReader class
     * 
     * @param readFile The name of the file to read from
     * @param length The length of each sequence to read from the file
     * @throws FileNotFoundException
     */
    public GeneBankFileReader(String readFile, int length) throws FileNotFoundException{
        file = new File(readFile);
        scan = new Scanner(file);
        //scan.useDelimiter("");
        seqLength = length;
    }

    /**
     * Returns the next DNA Sequence of the given length
     * 
     * @return DNASequence The next sequence in the given file, null if no more sequences
     */
    public DNASequence getNextSequence(){
        StringBuilder build = new StringBuilder();
        String next;

        //Create a string of the DNA sequence of length seqLength
        for(int i = 0; i < seqLength; i++){
            next = scan.next();

            //Check if the scanner has reached the end of the readable DNA sequence
            if(next.equals("/")){
                return null;
            }

            //If the next character is not a DNA base then decrement i and try adding the next character
            if(!next.equals("a") && !next.equals("c") && !next.equals("t") && !next.equals("g")){
                i--;
                continue;
            }

            build.append(next);
        }
        //TODO Update once DNASequence Class is done
        return(new DNASequence(build));
    }

    /**
     * Gets the current sequence length
     * @return int The length of each sequence being read from the file
     */
    public int getSequenceLength(){
        return -1;
    }

    /**
     * Sets the length of each sequence to read from the file
     */
    public void setSequenceLength(){

    }

}
