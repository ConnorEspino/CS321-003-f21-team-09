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
     * @throws InvalidFormatException
     */
    public GeneBankFileReader(String readFile, int length) throws FileNotFoundException, GeneBankFileException{

        //Lets us place the file anywhere and not have any issues with relative directories
        String filePath = new File(readFile).getAbsolutePath();

        file = new File(filePath);
        scan = new Scanner(file);

        //Places the scanner at the start of the DNA Sequence
        try{
            while(!scan.nextLine().equals("ORIGIN      "));
        }catch(NoSuchElementException e){
            throw new GeneBankFileException("File is not formatted for DNA Sequence reading...");
        }
        
        //Forces the scanner to go character by character
        scan.useDelimiter("");

        //Sets the sequence length
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
        return(new DNASequence(build.toString()));
    }

    /**
     * Gets the current sequence length
     * @return int The length of each sequence being read from the file
     */
    public int getSequenceLength(){
        return seqLength;
    }

    /**
     * Sets the length of each sequence to read from the file
     * @param newLength The new sequence length
     */
    public void setSequenceLength(int newLength){
        seqLength = newLength;
    }

}
