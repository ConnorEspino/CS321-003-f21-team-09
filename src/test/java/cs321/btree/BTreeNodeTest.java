package cs321.btree;
import org.junit.Test;

public class BTreeNodeTest {
    
    @Test
    public void degree_BTreeNode_ExpectNoException(){
        try{
            BTreeNode node = new BTreeNode(5);
        }catch(Exception e){
            assert(false);
        }
    }
}
