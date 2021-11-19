package cs321.search;

import cs321.btree.BTree;
import cs321.common.ParseArgumentException;
import cs321.common.ParseArgumentUtils;

public class GeneBankSearchBTree {

    public static void main(String[] args) throws Exception {
        // System.out.println("Hello world from cs321.search.GeneBankSearchBTree.main");

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
        for (int i = 0; i < x.getNumNodes(); i++) {

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
