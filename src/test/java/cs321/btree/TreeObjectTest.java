package cs321.btree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cs321.create.DNASequence;

public class TreeObjectTest {
    
    @Test
    public void DNASequence_treeObject_expectNoException(){
        try{
            TreeObject obj = new TreeObject(new DNASequence("AAAA"));
        }catch(Exception exception){
            assert(false);
        }
    }

    @Test
    public void TreeObject_getKey_binaryKey(){
        TreeObject obj = new TreeObject(new DNASequence("AAAA"));
        assertEquals((long) 00000000, obj.getKey());
    }

    

}
