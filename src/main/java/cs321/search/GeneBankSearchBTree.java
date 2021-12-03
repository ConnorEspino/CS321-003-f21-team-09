package cs321.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

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
    private LinkedListCache<BTreeNode> cache;


    /**
     * Returns the frequency of subsequence query that occur in Btree
     * 
     * @param query The DNA Sequence we are searching for, represented in binary
     * @param x     The root node to start searching from
     * @return int How many occurances of subsequence query exist in BTree
     */
    public int find(long address, long query) {
        BTreeNode x = diskRead(address);
        //If we're using a cache, search that first
        if(useCache == 1){
            try{
                //We're searching for a long, but can only access Node objects from the cache
                cache.getObject(query);
            }catch (Exception e){

            }
            cache.addObject(x);
        }

        //Search starting at the end of the node.
        for(int i = (2*degree) -1; i > 0; i--){
            //If we find a match, return the frequency of that sequence
            if(query == x.getElement(i).getKey()){
                return x.getElement(i).getFrequency();
            }
            //If the thing we're searching for is greater than the current element, check right child.
            if(query > x.getElement(i).getKey() && x.getChildAddress(i+1) != 0){
                //TODO: Double check the check for if the child exists
                find(x.getChildAddress(i+1), query);
            }
        }
        
    }

    public void main(String[] args) throws Exception {
        if(args.length < 3){
            //print usage message & exit
        }
        Scanner queryScan;
        
        RandomAccessFile treeFile = new RandomAccessFile(args[1], "r");
        File queryFile = new File(args[2]);

        //Check if user asked for cache and create scanner for file
        try{
            int useCache = Integer.parseInt(args[0]);
            queryScan = new Scanner(queryFile);
        }catch(Exception e){
            //Print usage message & exit
        }

        //Set cache size
        if(args.length > 3 && useCache == 1){
            try{
                int cacheSize = Integer.parseInt(args[3]);
                cache = new LinkedListCache<BTreeNode>(cacheSize);
            }catch(Exception e){
                //Print usage message and exit
            }
        }

        //Set debug level
        if(args.length > 4){
            debugLevel = Integer.parseInt(args[4]);
        }else if(useCache == 0 && args.length > 3){
            debugLevel = Integer.parseInt(args[3]);
        }else{
            debugLevel = 0;
        }

        //End of argument parsing
        
        //Open Btree file
        //TODO Do I create an entire Btree and then search through, or do I search through as Im building the btree?
        //TODO Do I only search by reading the disk or should I build A Btree while searching and search that until I come to something that hasn't been read yet
        //TODO The cache doesn't make sense to me since we need to read from the disk to get an object that we check if its in the cache already

        //Search file for frequency of each query in the query file
        while(queryScan.hasNextLine()){
            String line = queryScan.nextLine();
            System.out.println(line + ": " + find(tree.root(), new DNASequence(line).getLong()));
        }

    }

}
