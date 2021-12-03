package cs321.search;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * A class for parsing the command line arguments when executing the GeneBankSearchBTree class
 * 
 * @author Connor Espino
 */
public class GeneBankSearchBTreeArguments
{  
    private int useCache;
    private int cacheSize;
    private int debugLevel;
    private int degree;
    private File queryFile;
    private RandomAccessFile treeFile;

    /**
     * Constructor for GeneBankSearchBTreeArguments class
     * @param args The arguments given by the user
     */
    public GeneBankSearchBTreeArguments(String args[]){
        if(args.length < 3){
            System.out.println("GeneBankSearchBTreeArguments: Error reading args");
            System.out.println("Usage: GeneBankSearchBTree <0/1(no/with Cache)> <btree_file> <query_file> [<cache_size>] [<debug_level>]");
            return;
        }

        //Check if user asked for cache, create required files
        try{
            useCache = Integer.parseInt(args[0]);
            treeFile = new RandomAccessFile(args[1], "r");
            queryFile = new File(args[2]);
        }catch(Exception e){
            System.out.println("GeneBankSearchBTreeArguments: Error reading args " + e);
            System.out.println("Usage: GeneBankSearchBTree <0/1(no/with Cache)> <btree_file> <query_file> [<cache_size>] [<debug_level>]");
            return;
        }

        //Set cache size
        if(args.length > 3 && useCache == 1){
            try{
                cacheSize = Integer.parseInt(args[3]);
            }catch(Exception e){
                System.out.println("GeneBankSearchBTreeArguments: Error reading args " + e);
                System.out.println("Usage: GeneBankSearchBTree <0/1(no/with Cache)> <btree_file> <query_file> [<cache_size>] [<debug_level>]");
                return;
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
    }

    
}
