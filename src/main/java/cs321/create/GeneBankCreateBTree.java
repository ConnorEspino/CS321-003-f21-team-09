package cs321.create;

import cs321.btree.BTree;
import cs321.common.ParseArgumentException;

import java.io.*;
import java.util.List;

public class GeneBankCreateBTree {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello world from cs321.create.GeneBankCreateBTree.main");
        GeneBankCreateBTreeArguments geneBankCreateBTreeArguments = parseArgumentsAndHandleExceptions(args);
        BTree createdBTree = new BTree(geneBankCreateBTreeArguments);

        createdBTree.BTreeInsert(sequence);
    }

    private static GeneBankCreateBTreeArguments parseArgumentsAndHandleExceptions(String[] args) {
        GeneBankCreateBTreeArguments geneBankCreateBTreeArguments = null;
        try {
            geneBankCreateBTreeArguments = parseArguments(args);
        }
        catch (ParseArgumentException e) {
            printUsageAndExit(e.getMessage());
        }
        return geneBankCreateBTreeArguments;
    }

    private static void printUsageAndExit(String errorMessage) {
        System.out.println("USAGE: <0/1(no/with Cache)> <degree> <gbk_file> <subsequence_length> [<cache_size>] [<debug_level>]");
        System.exit(1);
    }

    public static GeneBankCreateBTreeArguments parseArguments(String[] args) throws ParseArgumentException {
        boolean useCache = Boolean.parseBoolean(args[0]);
        int degree = Integer.parseInt(args[1]);
        String gbkFileName = args[2];
        int subsequenceLength = Integer.parseInt(args[3]);
        int cacheSize = Integer.parseInt(args[4]);
        int debugLevel = Integer.parseInt(args[5]);
        GeneBankCreateBTreeArguments parsedArgs = new GeneBankCreateBTreeArguments(useCache, degree, gbkFileName, subsequenceLength, cacheSize, debugLevel);
        return parsedArgs;
    }

}
