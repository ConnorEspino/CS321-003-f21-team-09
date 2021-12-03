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
    private GeneBankSearchBTreeArguments arguments;

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
    public int find(BTreeNode x, long query) {
        
        //If we're using a cache, search that first
        if(arguments.useCache()){
            try{
                //Why search the cache if we already have the node x?
                cache.getObject(x);
            }catch (Exception e){

            }
            cache.addObject(x);
        }

        //Search starting at the end of the node.
        for(int i = (2*arguments.getDegree()) -1; i > 0; i--){
            //If we find a match, return the frequency of that sequence
            if(query == x.getElement(i).getKey()){
                return x.getElement(i).getFrequency();
            }
            //If the thing we're searching for is greater than the current element, check right child.
            if(query > x.getElement(i).getKey() && !x.isLeaf()){
                BTreeNode child = new BTreeNode(arguments.getDegree(), arguments.getTreeFile(), x.getChildAddress(i+1));
                find(child, query);
            }else{
                return (Integer) null;
            }
        }
        return 0;
        
    }

    public void main(String[] args) throws Exception {
        arguments = new GeneBankSearchBTreeArguments(args);
        Scanner queryScan = new Scanner(arguments.getQueryFile());

        //TODO Do I create an entire Btree and then search through, or do I search through as Im building the btree?
        //TODO Do I only search by reading the disk or should I build A Btree while searching and search that until I come to something that hasn't been read yet
        //TODO The cache doesn't make sense to me since we need to read from the disk to get an object that we check if its in the cache already
        RandomAccessFile treeFile = arguments.getTreeFile();
        treeFile.seek(METADATA_SIZE);
        
        if(arguments.useCache()){
            cache = new LinkedListCache<BTreeNode>(arguments.getCacheSize());
        }
        BTreeNode dummyNode = new BTreeNode(0, treeFile, 0);
        BTreeNode x = dummyNode.diskRead(METADATA_SIZE);

        //Search file for frequency of each query in the query file
        while(queryScan.hasNextLine()){
            String line = queryScan.nextLine();
            System.out.println(line + ": " + find(tree.root(), new DNASequence(line).getLong()));
        }

    }

}
