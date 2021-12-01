package cs321.btree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class BTreeNode {
    // A boolean which is true if this node is a leaf and otherwise false
    private boolean leaf;
    // Array of treeObjects to store in node
    private TreeObject array[];
    //Array of child nodes
    private long children[];
    // The number of elements currently in the node
    private int size;
    //Degree of the BTree
    private int degree;
    private RandomAccessFile file;
    private long address;
    private ByteBuffer buffer;

    /**
     * Constructor for BTreeNode
     * @param degree The degree of the BTree that the node is in 
     */
    public BTreeNode(int degree, RandomAccessFile file, long address) {
        size = 0;
        leaf = true;
        this.degree = degree;
        this.file = file;
        this.address = address;
        // Initialize array with a size of 2t-1
        array = new TreeObject[(2 * degree) - 1];
        children = new long[2*degree];
    }

    public void diskWrite() throws IOException{
        file.seek(address);
        file.writeInt(size);
        for(int i = 0; i < size; i++){
            file.writeLong(array[i].getKey());
        }
        if(leaf){
            file.writeInt(1);
        }else{
            file.writeInt(0);
            for(int i = 0; i < (size*2) - 1; i++){
                file.writeLong(children[i]);
            }
        }
    }

    // public BTreeNode diskRead() throws IOException{
    //     file.seek(address);
    //     buffer.clear();
    
    // }

    /**
     * Insert a BTreeObject into the node
     * CAUTION: This assumes the Node is not currently full, a check that should be done before inserting in the BTree class.
     * @param obj The object being inserted
     * @throws BTreeException If the node is full
     */
    public void insertNonFull(TreeObject obj) throws BTreeException {
        if (array[array.length - 1] != null) {
            throw new BTreeException("Node is full, split before adding more elements");
        }

        // Empty list insertion
        if (size == 0) {
            array[0] = obj;
            size++;
        }
        // Non empty list insertion
        else {
            for (int i = size-1; i >= 0; i--) {
                // If the object is a duplicate of one already in the list, increase the frequency of the one already in the list
                if (obj.equals(array[i]) == 0) {
                    array[i].increaseFrequency();
                }

                // If the object is greater than the object at index i, insert after that index.
                if (obj.equals(array[i]) == 1) {

                    // Shift elements over to make room for new insertion
                    for (int j = size; j > i+1; j--) {
                        array[j] = array[j - 1];
                    }

                    //Shift over elements in the children array
                    for(int j = (degree*2) - 1; j > i+1; j--){
                        children[j] = children[j-1];
                    }
                    //Copy the last child node to the next spot over
                    children[i+1] = children[i];

                    //Insert element
                    array[i+1] = obj;
                    // Increase the size of the array after insertion
                    size++;
                    break;
                }

                // If the whole array has been iterated through and no placement was found,
                // insert at the beginning of the array
                if (i == 0) {
                    // Shift elements over to make room for new insertion
                    for (int j = size; j > 0; j--) {
                        array[j] = array[j - 1];
                    }
                    array[0] = obj;
                    // Increase the size of the array after insertion
                    size++;
                }
            }
        }        
    }

    /**
     * Checks whether or not any element in the node has children
     * @return boolean True if the children array is empty, false otherwise
     */
    public boolean isLeaf() {
        return leaf;
    }

    /**
     * Returns the number of objects stored in this node
     * 
     * @return int The number of TreeObjects stored in the node
     */
    public int getNumElements(){
        return size;
    }

    /**
     * Returns the TreeObject at the given index
     * @param index The index of the object to access
     * @return TreeObject The object at the given index
     * @throws IndexOutOfBoundsException
     */
    public TreeObject getElement(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return array[index];
    }

    /**
     * Returns the child at the given index of the children array
     * @param index The index of the child to access
     * @return BTreeNode The child node stored at the index
     * @throws IndexOutOfBoundsException
     */
    public long getChildAddress(int index){
        if(index < 0 || index >= (2*degree)){
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return children[index];
    }

    /**
     * Sets the child node at the index to be the given node
     * @param index The index of the children array to store the child node
     * @param node The child node to store at the given array
     * @throws IndexOutOfBoundsException
     */
    public void setChildAddress(int index, long address){
        if(index < 0 || index >= (2*degree)){
            throw new IndexOutOfBoundsException("Invalid index");
        }
        children[index] = address;
        leaf = false;
    }

}
