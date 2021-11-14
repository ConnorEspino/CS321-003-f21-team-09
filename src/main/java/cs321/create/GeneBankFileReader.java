package cs321.create;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GeneBankFileReader {
    File file;
    Scanner scan;
    int seqLength;

    /**
     * Constructor for GeneBankFileReader class
     * 
     * @param readFile File to read from
     * @param length The length of each sequence to read from the file
     * @throws FileNotFoundException
     */
    public GeneBankFileReader(File readFile, int length) throws FileNotFoundException{
        file = readFile;
        scan = new Scanner(file);
        seqLength = length;
    }

    /**
     * Returns the next DNA Sequence of the given length
     * 
     * @return DNASequence The next sequence in the given file, null if no more sequences
     */
    public DNASequence getNextSequence(){
        
    }

    /**
     * Gets the current sequence length
     * @return int The length of each sequence being read from the file
     */
    public int getSequenceLength(){

    }

    /**
     * Sets the length of each sequence to read from the file
     */
    public void setSequenceLength(){

    }

}
