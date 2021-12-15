package cs321.create;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * A class for reading DNA Sequences of a certain length from a .gbk file 
 * 
 * @author Connor Espino
 */
public class GeneBankFileReader {
    private File file;
    private Scanner scan;
    private int seqLength;
    private StringBuilder build;

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

        //Initialize the String builder object
        build = new StringBuilder();

        //Build the initial DNA sequence for the given length
        for(int i = 0; i < seqLength; i++){
            //Get the next character and append to current sequence
            String validChar = nextValidChar();

            //Check if we're at the end of the file. Return null if we are
            if(validChar != null){
                build.append(validChar);
            }else{
                throw new GeneBankFileException("Error: No valid sequences of length " + length);
            }
        }
        
    }

    /**
     * Returns the next DNA Sequence of the given length
     * 
     * @return DNASequence The next sequence in the given file, null if no more sequences
     */
    public DNASequence getNextSequence(){
        String retSeq = build.toString();

        //Delete the first character of the current sequence
        build.deleteCharAt(0);
        
        //Get the next character and append to current sequence
        String validChar = nextValidChar();

        //Check if we're at the end of the file. Return null if we are
        if(validChar != null){
            build.append(validChar);
        }else{
            return null;
        }

        return(new DNASequence(retSeq.toUpperCase()));
    }

    /**
     * Gets the next valid character from the GeneBank file
     * 
     * @return String The next valid character as a string
     */
    public String nextValidChar(){
        String nextChar = scan.next();
        
        //Check if the scanner has reached the end of the readable DNA sequence
        if(nextChar.equals("/")){
            //Places the scanner at the start of the next DNA Sequence if it exists
            try{
                while(!scan.nextLine().equals("ORIGIN      "));
            }catch(NoSuchElementException e){
                    return null;
            }
        }

        //If the next character is not a DNA base then try adding the next character
        while(!nextChar.equals("a") && !nextChar.equals("c") && !nextChar.equals("t") && !nextChar.equals("g")){
            nextChar = scan.next();
            //Check if the scanner has reached the end of the readable DNA sequence
            if(nextChar.equals("/")){
                //Places the scanner at the start of the next DNA Sequence if it exists
                try{
                    while(!scan.nextLine().equals("ORIGIN      "));
                }catch(NoSuchElementException e){
                    return null;
                }
            }
        }
        return nextChar;
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