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

    @Test
    public void equalTreeObjects_equals_return0(){
        TreeObject obj1 = new TreeObject(new DNASequence("AAAA"));
        TreeObject obj2 = new TreeObject(new DNASequence("AAAA"));

        assertEquals(0, obj1.equals(obj2));
    }

    @Test
    public void greaterThanTreeObjects_equals_return1(){
        TreeObject obj1 = new TreeObject(new DNASequence("TTTT"));
        TreeObject obj2 = new TreeObject(new DNASequence("AAAA"));

        assertEquals(1, obj1.equals(obj2));
    }

    @Test
    public void lessThanTreeObjects_equals_returnNegative1(){
        TreeObject obj1 = new TreeObject(new DNASequence("AAAA"));
        TreeObject obj2 = new TreeObject(new DNASequence("TTTT"));

        assertEquals(-1, obj1.equals(obj2));
    }
}
