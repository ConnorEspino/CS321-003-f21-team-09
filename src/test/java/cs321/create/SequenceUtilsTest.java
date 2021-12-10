package cs321.create;

import org.junit.Test;

import static org.junit.Assert.*;

public class SequenceUtilsTest
{
    SequenceUtils util = new SequenceUtils();
    @Test
    public void longtoDNASequenceTest(){
        long seqLong = 13;
        String seqString = "AATC";
        assertEquals(seqString, SequenceUtils.longToDNAString(seqLong, 4));
    }

}
