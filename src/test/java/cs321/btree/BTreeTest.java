package cs321.btree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BTreeTest
{
    // HINT:
    //  instead of checking all intermediate states of constructing a tree
    //  you can check the final state of the tree and
    //  assert that the constructed tree has the expected number of nodes and
    //  assert that some (or all) of the nodes have the expected values
    @Test
    public void btreeDegree4Test() {
        //TODO instantiate and populate a bTree object
        BTree bTree = new BTree(6);
        int expectedNumberOfNodes = 3;

        // it is expected that these nodes values will appear in the tree when
        // using a level traversal (i.e., root, then level 1 from left to right, then
        // level 2 from left to right, etc.)
        String[] expectedNodesContent = new String[]{
                "2, 3",      //root content
                "1",           //first child of root content
                "4, 5, 6", //second child of root content
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
