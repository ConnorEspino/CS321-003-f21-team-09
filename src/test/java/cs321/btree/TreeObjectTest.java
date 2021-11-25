package cs321.btree;
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


}
