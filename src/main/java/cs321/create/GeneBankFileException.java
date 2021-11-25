package cs321.create;

/**
 * Exception class thrown when a GeneBankFile is improperly formatted
 * @author Connor Espino
 */
public class GeneBankFileException extends Exception{
    public GeneBankFileException(String message)
    {
        super(message);
    }
}
