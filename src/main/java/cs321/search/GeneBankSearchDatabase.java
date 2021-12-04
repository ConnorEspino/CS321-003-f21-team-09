package cs321.search;

import java.io.File;
import cs321.btree.BTree;
import cs321.common.ParseArgumentException;
import cs321.common.ParseArgumentUtils;

/**
 * A class used for searching the stored Gene Bank database.
 * 
 * @author Ken R.
 * 
 */

public class GeneBankSearchDatabase {

    public static void main(String[] args) throws Exception {
        String databasePath = null;
        File queryFile;
        int debugLevel = -1;
        
        if (args.length != 2 || args.length != 3) {
            System.err.println("Usage: GeneBankSearchDatabase.java <path_to_SQLite_"
            + "database> <query_file> [<debug_level>]");
            System.exit(1);
        }

        databasePath = args[0];
        if (databasePath == null) {
            System.err.println("Error: Database path invalid");
            System.err.println("Usage: GeneBankSearchDatabase.java <path_to_SQLite_"
            + "database> <query_file> [<debug_level>]");
            System.exit(1);
        }

        try {
            queryFile = new File(args[2]);
        } catch (Exception e) {
            System.err.println("Error: File \'" + args[1] + "\' not found");
            System.exit(1);
        }

        if (args.length != 3) {
            debugLevel = 0;
        } else {
            if (Integer.parseInt(args[2]) != 0 && Integer.parseInt(args[2]) != 1) {
                System.err.println("Error: Debug level not found");
                System.exit(1);
            }
            debugLevel = Integer.parseInt(args[2]);
            if (debugLevel == -1) {
                System.err.println("Error: Debug level invalid");
                System.err.println("Usage: GeneBankSearchDatabase.java <path_to_SQLite_"
                + "database> <query_file> [<debug_level>]");
                System.exit(1);
            }
        }
    }

}
