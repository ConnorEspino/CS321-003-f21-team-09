package cs321.search;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import cs321.btree.BTreeException;
import cs321.btree.BTreeNode;
import cs321.create.DNASequence;

public class GeneBankSearchBTree {
    private static GeneBankSearchBTreeArguments arguments;

    //Change when you know metadata size
    private static int METADATA_SIZE = 8;
    private static LinkedListCache cache;


    /**
     * Returns the frequency of subsequence query that occur in Btree
     * 
     * @param query The DNA Sequence we are searching for, represented in binary
     * @param x     The root node to start searching from
     * @return int How many occurances of subsequence query exist in BTree
     * @throws BTreeException
     * @throws IOException
     */
    public static int find(BTreeNode x, long query) throws IOException, BTreeException {
        //Search starting at the end of the node.
        for(int i = (2*arguments.getDegree()) -1; i > 0; i--){
            //If we find a match, return the frequency of that sequence
            if(query == x.getElement(i).getKey()){
                return x.getElement(i).getFrequency();
            }
            //If the thing we're searching for is greater than the current element, check right child.
            if(query > x.getElement(i).getKey() && !x.isLeaf()){
                BTreeNode child = x.diskRead(x.getChildAddress(i+1), cache);
                find(child, query);
            }else{
                return (Integer) null;
            }
        }
        return 0;
        
    }

    public static void main(String[] args) throws Exception {
        arguments = new GeneBankSearchBTreeArguments(args);
        Scanner queryScan = new Scanner(arguments.getQueryFile());

        RandomAccessFile treeFile = arguments.getTreeFile();
        treeFile.seek(METADATA_SIZE);
        
        if(arguments.useCache()){
            cache = new LinkedListCache(arguments.getCacheSize());
        }
        BTreeNode dummyNode = new BTreeNode(0, treeFile, 0);
        BTreeNode x = dummyNode.diskRead(METADATA_SIZE, cache);

        //Search file for frequency of each query in the query file
        while(queryScan.hasNextLine()){
            String line = queryScan.nextLine();
            System.out.println(find(x, new DNASequence(line).getLong()) + " " + line );
        }

    }

}
