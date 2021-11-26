package cs321.btree;

public class BTreeNode {
    // A boolean which is true if this node is a leaf and otherwise false
    private boolean leaf;
    // Array of treeObjects to store in node
    private TreeObject array[];
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
    }

    /**
     * Insert a BTreeObject into the node
     * @param obj The object being inserted
     */
    public void insert(TreeOjbect obj) {
        if (array[array.length - 1] != null) {
            throw new BTreeException("Node is full, split before adding more elements");
        }

        // Empty list insertion
        if (size == 0) {
            array[0] = obj;
        }
        // Non empty list insertion
        else {
            for (int i = 0; i < size; i++) {
                // If the object is a duplicate of one already in the list, copy the
                // children of the one in the list to the object
                if (obj.equals(array[i]) == 0) {
                    obj.setLeft(array[i].getLeft());
                    obj.setRight(array[i].getRight());
                }

                // If the object is less than or equal to the object at index i, insert before
                // that index
                if (obj.equals(array[i]) == -1 || obj.equals(array[i]) == 0) {

                    // Shift elements over to make room for new insertion
                    for (int j = array.length - 1; j >= i; j--) {
                        array[j] = array[j - 1];
                    }

                    //Insert element
                    array[i] = obj;
                    break;
                }

                // If the whole array has been iterated through and no placement was found,
                // insert at the end of the array
                if (i == size - 1) {
                    array[size] = obj;
                }
            }
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
