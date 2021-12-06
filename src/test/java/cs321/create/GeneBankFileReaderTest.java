package cs321.create;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

public class GeneBankFileReaderTest
{
    private GeneBankFileReader reader;

    //Tests for constructor below
    @Test
    public void NonExistentFile_newConstructor_FileNotFoundException() throws GeneBankFileException{
        try{
            reader = new GeneBankFileReader("Not a real file", 10);
        }catch(FileNotFoundException e){
            assert(true);
        }
    }

    @Test
    public void ExistentFile_newConstructor_expectNoException() throws GeneBankFileException{
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
        }catch(GeneBankFileException e){
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
    public void ValidSequence_EndOfFile_ReturnNull() throws FileNotFoundException, GeneBankFileException{
        reader = new GeneBankFileReader("src/test/java/cs321/create/ValidTestFileShort.txt", 200);
        assertEquals(null, reader.getNextSequence());
    }

    @Test
    public void ValidSequence_getNextSequence_ReturnDNASequence() throws FileNotFoundException, GeneBankFileException{
        reader = new GeneBankFileReader("src/test/java/cs321/create/ValidTestFile.txt", 10);
        DNASequence seq = new DNASequence("GATCCTCCAT");
        assert(seq.equals(reader.getNextSequence()) == 0);
    }

    @Test
    public void twoSequences_getNextSequence2_properSequences() throws FileNotFoundException, GeneBankFileException{
        reader = new GeneBankFileReader("src/test/java/cs321/create/ValidTestFile.txt", 3);
        DNASequence seq = new DNASequence("GAT");
        DNASequence seq2 = new DNASequence("ATC");
        assert(seq.equals(reader.getNextSequence()) == 0 && seq2.equals(reader.getNextSequence()) == 0);
    }


    //Tests for getSequenceLength method below
    @Test
    public void GeneBankFileReader_getSequenceLength_expectNoException() throws FileNotFoundException, GeneBankFileException{
        reader = new GeneBankFileReader("src/test/java/cs321/create/ValidTestFile.txt", 2);
        assertEquals(2, reader.getSequenceLength());
    }

    //Tests for setSequenceLength method below
    @Test
    public void GeneBankFileReader_setSequenceLength_expectNoException() throws FileNotFoundException, GeneBankFileException{
        reader = new GeneBankFileReader("src/test/java/cs321/create/ValidTestFile.txt", 2);
        reader.setSequenceLength(5);
        
        assertEquals(5, reader.getSequenceLength());
    }
}
