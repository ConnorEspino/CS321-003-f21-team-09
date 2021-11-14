package cs321.create;

import cs321.create.GeneBankFileReader;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

public class GeneBankFileReaderTest
{
    private GeneBankFileReader reader;

    @Test
    public void InvalidDNAFile_ReturnNull() throws FileNotFoundException{
        reader = new GeneBankFileReader("InvalidTestFile.txt", 2);
        assertEquals(null, reader.getNextSequence());
    }

    @Test
    public void ValidSequence_EndOfFile_ReturnNull() throws FileNotFoundException{
        reader = new GeneBankFileReader("ValidTestFileShort.txt", 200);
        assertEquals(null, reader.getNextSequence());
    }

    @Test
    public void ValidSequence_getNextSequence_ReturnDNASequence(){
        reader = new GeneBankFileReader("ValidTestFile.txt", 10);
        //TODO Update when DNASequence class gets implemented
        DNASequence seq = new DNASequence("GATCCTCCAT");
        
        assertEquials(seq, reader.getNextSequence());
    }

}
