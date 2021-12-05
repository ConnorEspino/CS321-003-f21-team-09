package cs321.btree;

import cs321.create.DNASequence;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static org.junit.Assert.assertEquals;

public class BTreeTest
{
    // HINT:
    //  instead of checking all intermediate states of constructing a tree
    //  you can check the final state of the tree and
    //  assert that the constructed tree has the expected number of nodes and
    //  assert that some (or all) of the nodes have the expected values
    @Test
    public void btreeDegree4Test() throws IOException, BTreeException {
        //TODO instantiate and populate a bTree object
        RandomAccessFile file = new RandomAccessFile("src/test/java/cs321/btree/test", "rw");
        BTree bTree = new BTree(2, file, 2);
        int expectedNumberOfNodes = 2;

        TreeObject insert = new TreeObject(new DNASequence("A"));
        bTree.BTreeInsert(insert);
        TreeObject insert2 = new TreeObject(new DNASequence("T"));
        bTree.BTreeInsert(insert2);
        TreeObject insert3 = new TreeObject(new DNASequence("G"));
        bTree.BTreeInsert(insert3);
        TreeObject insert4 = new TreeObject(new DNASequence("G"));
        bTree.BTreeInsert(insert4);
        // it is expected that these nodes values will appear in the tree when
        // using a level traversal (i.e., root, then level 1 from left to right, then
        // level 2 from left to right, etc.)
        String[] expectedNodesContent = new String[]{
                "A, G, T"      //root content
                //first child of root content
                //second child of root content
        };

        assertEquals(expectedNumberOfNodes, bTree.getNumberOfNodes());
        for (int indexNode = 0; indexNode < expectedNumberOfNodes; indexNode++)
        {
            // root has indexNode=0,
            // first child of root has indexNode=1,
            // second child of root has indexNode=2, and so on.
            assertEquals(expectedNodesContent[indexNode], bTree.getArrayOfNodeContentsForNodeIndex(indexNode).toString());
        }
    }

}
