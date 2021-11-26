package cs321.btree;
import org.junit.Test;

import cs321.create.DNASequence;

public class BTreeNodeTest {
    
    @Test
    public void degree_BTreeNode_ExpectNoException(){
        try{
            BTreeNode node = new BTreeNode(5);
        }catch(Exception e){
            assert(false);
        }
    }

    @Test
    public void emptyNodeObject_insert_ExpectNoException(){
        try{
            BTreeNode node = new BTreeNode(5);
            node.insert(new TreeObject(new DNASequence("AAA")));
        }catch(Exception e){
            assert(false);
        }
    }

    @Test
    public void notEmptyNodeObject_insert_ExpectNoException(){
        try{
            BTreeNode node = new BTreeNode(5);
            node.insert(new TreeObject(new DNASequence("AAA")));
            node.insert(new TreeObject(new DNASequence("AAA")));
        }catch(Exception e){
            assert(false);
        }
    }

    @Test
    public void notEmptyNodeObject_insertGreatest_ExpectNoException(){
        try{
            BTreeNode node = new BTreeNode(5);
            node.insert(new TreeObject(new DNASequence("AAA")));
            node.insert(new TreeObject(new DNASequence("TTT")));
        }catch(Exception e){
            assert(false);
        }
    }
}
