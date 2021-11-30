package cs321.btree;
import static org.junit.Assert.assertEquals;

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

    @Test
    public void leafNode_isLeaf_returnTrue() throws BTreeException{
        BTreeNode node = new BTreeNode(5);
        node.insert(new TreeObject(new DNASequence("AAA")));
        node.insert(new TreeObject(new DNASequence("TTT")));
        assertEquals(true, node.isLeaf());
    }

    @Test
    public void notLeafNode_isLeaf_returnFalse() throws BTreeException{
        BTreeNode node = new BTreeNode(5);
        BTreeNode childNode = new BTreeNode(5);

        TreeObject obj = new TreeObject(new DNASequence("AAA"));

        node.setChild(0, childNode);
        node.insert(obj);
        assertEquals(false, node.isLeaf());
    }

    @Test
    public void multipleObjectsNode_getNumObjects_returnNumElements() throws BTreeException{
        BTreeNode node = new BTreeNode(5);
        node.insert(new TreeObject(new DNASequence("AAA")));
        node.insert(new TreeObject(new DNASequence("TTT")));
        assertEquals(2, node.getNumElements());
    }

    @Test
    public void emptyNodeObject_getNumElements_return0(){
        BTreeNode node = new BTreeNode(5);
        assertEquals(0, node.getNumElements());
    }
    
    @Test
    public void multipleObjectsNode_getValidIndex_ExpectNoExceptions() throws BTreeException{
        BTreeNode node = new BTreeNode(5);
        node.insert(new TreeObject(new DNASequence("AAA")));
        node.insert(new TreeObject(new DNASequence("TTT")));

        try{
            node.getElement(1);
        }catch(Exception e){
            assert(false);
        }
    }

    @Test
    public void nodeObject_getInvalidIndex_IndexOutOfBoundsException(){
        BTreeNode node = new BTreeNode(5);

        try{
            node.getElement(10);
            assert(false);
        }catch(Exception e){
            assert(true);
        }
    }
}
