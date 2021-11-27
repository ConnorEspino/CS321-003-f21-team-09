package cs321.btree;

public class BTreeNode {
    // A boolean which is true if this node is a leaf and otherwise false
    private boolean leaf;
    // Array of treeObjects to store in node
    private TreeObject array[];
    //Array of child nodes
    private BTreeNode children[];
    // The number of elements currently in the node
    private int size;

    /**
     * Constructor for BTreeNode
     * @param degree The degree of the BTree that the node is in 
     */
    public BTreeNode(int degree) {
        size = 0;
        leaf = true;
        // Initialize array with a size of 2t-1
        array = new TreeObject[(2 * degree) - 1];
        children = new BTreeNode[2*degree];
    }

    /**
     * Insert a BTreeObject into the node
     * @param obj The object being inserted
     */
    public void insert(TreeOjbect obj) {
        if (array[array.length - 1] != null) {
            throw new BTreeException("Node is full, split before adding more elements");
        }

        if(leaf){
            int i = size;
            while(size >= 0 && obj.getKey() < array[i].getKey()){
                array[i+1] = array[i];
                i--;
            }
            //Disk-write(This node)
        }else{
            
        }

        // Increase the size of the array after insertion
        size++;
    }

    /**
     * Checks whether or not any element in the node has children
     * @return boolean True if the node has no Objects that have children, false otherwise
     */
    public boolean isLeaf() {
        for (int i = 0; i < size; i++) {
            // Update leaf boolean
            if (array[i].getLeft() != null || array[i].getRight() != null) {
                leaf = false;
            }
        }
        return leaf;
    }

    /**
     * Returns the number of objects stored in this node
     * 
     * @return int The number of TreeObjects stored in the node
     */
    public int getNumObjects(){
        return size;
    }

    /**
     * Returns the TreeObject at the given index
     * @param index The index of the object to access
     * @return TreeObject The object at the given index
     * @throws IndexOutOfBoundsException
     */
    public TreeObject get(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return array[index];
    }

}
