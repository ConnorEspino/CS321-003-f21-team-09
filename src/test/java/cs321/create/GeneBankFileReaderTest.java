package cs321.create;

import cs321.create.GeneBankFileReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Test;

public class GeneBankFileReaderTest
{
    private GeneBankFileReader reader;

    //Tests for constructor below
    @Test
    public void NonExistentFile_newConstructor_FileNotFoundException() throws InvalidFormatException{
        try{
            reader = new GeneBankFileReader("Not a real file", 10);
        }catch(FileNotFoundException e){
            assert(true);
        }
    }

    @Test
    public void ExistentFile_newConstructor_expectNoException() throws InvalidFormatException{
        try{
            reader = new GeneBankFileReader("src/test/java/cs321/create/ValidTestFile.txt", 2);
            assert(true);
        }catch(FileNotFoundException e){
            assert(false);
        }
    }

    @Test
    public void ImproperFile_newConstructor_InvalidFormatException() throws FileNotFoundException{
        try{
            reader = new GeneBankFileReader("src/test/java/cs321/create/InvalidTestFile.txt", 2);
            assert(false);
        }catch(InvalidFormatException e){
            assert(true);
        }
    }

    //Tests for getNextSequence method below

    // Determined to be unnecessary test since constructor checks for proper formatting of file
    // @Test
    // public void InvalidDNAFile_ReturnNull() throws FileNotFoundException{
    //     reader = new GeneBankFileReader("InvalidTestFile.txt", 2);
    //     assertEquals(null, reader.getNextSequence());
    // }

    @Test
    public void ValidSequence_EndOfFile_ReturnNull() throws FileNotFoundException{
        reader = new GeneBankFileReader("ValidTestFileShort.txt", 200);
        assertEquals(null, reader.getNextSequence());
    }

    @Test
    public void ValidSequence_getNextSequence_ReturnDNASequence(){
        reader = new GeneBankFileReader("src/test/java/cs321/create/ValidTestFile.txt", 10);
        //TODO Update when DNASequence class gets implemented
        DNASequence seq = new DNASequence("GATCCTCCAT");

        assertEquials(seq, reader.getNextSequence());
    }

    //Tests for getSequenceLength method below
    @Test
    public void GeneBankFileReader_getSequenceLength_expectNoException() throws FileNotFoundException, InvalidFormatException{
        reader = new GeneBankFileReader("src/test/java/cs321/create/ValidTestFile.txt", 2);
        assertEquals(2, reader.getSequenceLength());
    }

    //Tests for setSequenceLength method below
    @Test
    public void GeneBankFileReader_setSequenceLength_expectNoException() throws FileNotFoundException, InvalidFormatException{
        reader = new GeneBankFileReader("src/test/java/cs321/create/ValidTestFile.txt", 2);
        reader.setSequenceLength(5);
        
        assertEquals(5, reader.getSequenceLength());
    }
}
