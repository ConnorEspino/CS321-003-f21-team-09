package cs321.btree;

import java.io.IOException;
import java.io.RandomAccessFile;

import cs321.create.DNASequence;
import cs321.search.LinkedListCache;

public class BTreeNode {
    // A boolean which is true if this node is a leaf and otherwise false
    private boolean leaf;
    // Array of treeObjects to store in node
    private TreeObject array[];
    //Array of child nodes
    private long children[];
    // The number of elements currently in the node
    private int size;
    private int maxChildIndex;
    //Degree of the BTree
    private int degree;
    private RandomAccessFile file;
    //Address of the BTreeNode
    private long address;
    LinkedListCache cache;


    /**
     * Constructor for BTreeNode
     * @param degree The degree of the BTree that the node is in 
     * @param file  The file to read and write the node to
     * @param address The address in the File to read and write the node to
     */
    public BTreeNode(int degree, RandomAccessFile file, long address) {
        size = 0;
        leaf = true;
        this.degree = degree;
        this.file = file;
        this.address = address;
        maxChildIndex = 0;
        // Initialize array with a size of 2t-1
        array = new TreeObject[(2 * degree) - 1];
        children = new long[2*degree];
    }

    /**
     * Constructor for BTreeNode without adress intially set to a value
     * @param degree The degree of the BTree that the node is in 
     * @param file  The file to read and write the node to
     * 
     */
    public BTreeNode(int degree, RandomAccessFile file){
        size = 0;
        leaf = true;
        this.degree = degree;
        this.file = file;
        maxChildIndex = 0;
        // Initialize array with a size of 2t-1
        array = new TreeObject[(2 * degree) - 1];
        children = new long[2*degree];
    }

    /**
     * Writes the node information to the address of the BTreeNodein the binary data file
     * @param cache The cache to add the node to when writing
     * @throws IOException
     */
    public void diskWrite(LinkedListCache cache) throws IOException{
        file.seek(address);
        file.writeInt(size);
        for(int i = 0; i < size; i++){
            file.writeLong(array[i].getKey());
            file.writeInt(array[i].getFrequency());
        }
        if(leaf){
            file.writeInt(1);
        }else{
            file.writeInt(0);
            for(int i = 0; i < maxChildIndex; i++){
                file.writeLong(children[i]);
            }
        }
        //Add this node object to the cache
        if(cache != null){
            cache.addObject(this);
        }
    }

    /**
     * Writes the node information to the address of the BTreeNodein the binary data file
     * @param cache The cache to add the node to when writing
     * @param address The address to write the BTreeNode object to in the file
     * @throws IOException
     */
    public void diskWrite(LinkedListCache cache, long address) throws IOException{
        file.seek(address);
        file.writeInt(size);
        for(int i = 0; i < size; i++){
            file.writeLong(array[i].getKey());
            file.writeInt(array[i].getFrequency());
        }
        if(leaf){
            file.writeInt(1);
        }else{
            file.writeInt(0);
            for(int i = 0; i < maxChildIndex; i++){
                file.writeLong(children[i]);
            }
        }
        //Add this node object to the cache
        if(cache != null){
            cache.addObject(this);
        }
    }

    /**
     * Reads Node information and creates Node object using data from the binary file
     * @return BTreeNode Node created from reading binary data
     * @throws IOException
     * @throws BTreeException
     */
    public BTreeNode diskRead(long address, LinkedListCache cache) throws IOException, BTreeException {
        if (address == 0) return null;
        BTreeNode x  = null;

        //Check if the cache exists
        if(cache != null){
            //If the cache exists search for the address
            BTreeNode dummyNode = new BTreeNode(degree, file, address);
            x = cache.getObject(dummyNode);
        }
        //If the cache didn't find any matches or there was no cache, make a new Node
        if(x == null){
            x = new BTreeNode(degree, file, address);
        }else{
            //If the cache found a match, return that node
            return x;
        }
        
        file.seek(address);

        int n = file.readInt();

        x.size = n;

        for(int i = 0; i < n; i++){
            long value = file.readLong();
            int freq = file.readInt();
            TreeObject obj = new TreeObject(new DNASequence(value), freq);
            x.array[i] = obj;
        }

        //If there are children, read the children and store them in the file.
        if(file.readByte() == 0){
            for(int i = 0; i < maxChildIndex; i++){
                x.children[i] = file.readLong();
            }
        }

        return x;
    }

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
                if (obj.compareTo(array[i]) == 0) {
                    array[i].increaseFrequency();
                }

                // If the object is greater than the object at index i, insert after that index.
                if (obj.compareTo(array[i]) == 1) {

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

    public TreeObject[] getArray(){
        return array;
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
     * @param address The child node to store at the given array//change this
     * @throws IndexOutOfBoundsException
     */
    public void setChildAddress(int index, long address){
        if(index < 0 || index >= (2*degree)){
            throw new IndexOutOfBoundsException("Invalid index");
        }
        children[index] = address;
        leaf = false;
        if(index > maxChildIndex)
            maxChildIndex = index;
    }

    public int getNumChildren(){
        return maxChildIndex;
    }

    public long getAddress(){        
        return address;
    }

    public boolean equals(BTreeNode obj){
        return (address == obj.getAddress());
    }

    public BTreeNode BTreeSplitChild(BTreeNode TreeNode, int index) throws BTreeException, IOException {
        if(index < 0 || index >= (2*degree-1)){
            throw new IndexOutOfBoundsException("Invalid index");
        }

        BTreeNode y = TreeNode;
        BTreeNode z = y;
        z.leaf = y.leaf;
        z.size = degree - 1;
        for (int j = 0; j <= degree/2 ; j++) {
            z.array[j] = y.array[(j + degree)-1];
        }
        if (!y.isLeaf()) {
            for (int j = 0; j < degree; j++) {
                z.children[j] = y.children[(j + degree)-1];
            }
        }
        y.size = degree -1;
        for (int j = (TreeNode.getNumElements())-2; j > index; j--) {
            TreeNode.children[j+1] = TreeNode.children[j];
        }
        TreeNode.setChildAddress(index, z.address);
//        TreeNode.children[index+1] = z.array[degree-1].getKey();
        for (int t = TreeNode.getNumElements()-2; t > index-1; t--) {
            TreeNode.array[t+1] = TreeNode.array[t];
        }
        TreeNode.array[index] = y.array[degree];
        TreeNode.size++;
//        y.diskWrite(null);
//        z.diskWrite(null);
//        TreeNode.diskWrite(null);
        return z;
    }

    public String toString(){
        String retStr = "";
        for(int i = 0; i < size; i++){
            if(i != 0){
                retStr += ", ";
            }
            retStr += array[i].toString();
        }
        return retStr;
    }

}
