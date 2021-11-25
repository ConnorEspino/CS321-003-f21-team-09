package cs321.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cs321.btree.BTree;
import cs321.common.ParseArgumentException;
import cs321.common.ParseArgumentUtils;
import cs321.create.DNASequence;

public class GeneBankSearchBTree {
    private static int useCache;
    private static int cacheSize;
    private static int debugLevel;
    private static LinkedListCache cache;

    public static void main(String[] args) throws Exception {
        if(args.length < 3){
            //print usage message & exit
        }
        Scanner queryScan;
        
        File treeFile = new File(args[1]);
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
    public int frequencyOf(BTreeObject x, int query) {
        int count = 0;
        
        //TODO correct when BTreeObject class is implemented

        // Iterate through each object stored in the current node
        for (int i = 0; i < x.getNumElements(); i++) {

            // If the value is less than the current element and the left child exists,
            // count frequency starting from the left child
            if (query < x.i && x.left != null) {
                count += frequencyOf(x.left, query);
            } else if (query == x.i) {
                // If the value is equal to the current element increase count and search left
                // and right children recursively
                count++;
                count += frequencyOf(x.left, query);
                count += frequencyOf(x.right, query);
            }
        }
        return count;

    }

}
