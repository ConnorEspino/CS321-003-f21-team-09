package cs321.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import cs321.btree.BTree;
import cs321.btree.BTreeNode;
import cs321.btree.TreeObject;
import cs321.common.ParseArgumentException;
import cs321.common.ParseArgumentUtils;
import cs321.create.DNASequence;

public class GeneBankSearchBTree {
    private int useCache;
    private int cacheSize;
    private int debugLevel;
    private int degree;
    private FileChannel file;
    private ByteBuffer buffer;

    //Change when you know metadata size
    private static int METADATA_SIZE = 8;
    private LinkedListCache cache;

    public void main(String[] args) throws Exception {
        if(args.length < 3){
            //print usage message & exit
        }
        Scanner queryScan;
        
        RandomAccessFile treeFile = new RandomAccessFile(args[1], "r");
        File queryFile = new File(args[2]);

        try{
            int useCache = Integer.parseInt(args[0]);
            queryScan = new Scanner(queryFile);
        }catch(Exception e){
            //Print usage message & exit
        }

        //Set cache size
        //TODO: Ask about implementing the cache properly
        if(args.length > 3 && useCache == 1){
            int cacheSize = Integer.parseInt(args[3]);
            cache = new LinkedListCache(cacheSize);
        }

        //Set debug level
        if(args.length > 4){
            int debugLevel = Integer.parseInt(args[4]);
        }else if(useCache == 0){
            int debugLevel = Integer.parseInt(args[3]);
        }
        
        //Open Btree file
        //TODO: Implement correctly when BTree class is complete
        BTree tree; //Equals the bTree file


        //Search file for frequency of each query in the query file
        while(queryScan.hasNextLine()){
            String line = queryScan.nextLine();
            System.out.println(line + ": " + frequencyOf(tree, new DNASequence(line).getInt()));
        }

    }

    /**
     * Returns the frequency of subsequence query that occur in Btree
     * 
     * @param query The DNA Sequence we are searching for, represented in binary
     * @param x     The root node to start searching from
     * @return int How many occurances of subsequence query exist in BTree
     */
    public int find(BTreeNode x, int query) {
        

    }

    //TODO Find out how to combine this into a tree. Specifically, how we read children in the file. 
    public TreeObject diskRead(long diskAddress) throws IOException {
        file.position(diskAddress);
        buffer.clear();
    
        file.read(buffer);
        buffer.flip();

        TreeObject x;

        int numElem = buffer.getInt();

        byte isLeaf = buffer.get();
        boolean leaf = false;
        if (isLeaf == 1) leaf = true;

        long bases = buffer.getLong();
        int frequency = buffer.getInt();
        x = new TreeObject(new DNASequence(bases), frequency);


        // for(int i = 0; i < (2*degree) - 1; i++){
        //     long address = buffer.getLong();
        //     //Do we have to store children arrays in binary file? 
        //     //Do we just insert these objects into a BTree and the children are taken care of?
        //     x.setChild(i, address);
        // }
        
        return x;
        }

}
