package proiect1.filesystem;

/**
 * A fost depasit numarul maxim admis de fisiere deschise.
 */
public class TooManyOpenPhilesException extends Exception {
    public TooManyOpenPhilesException() {}
    public TooManyOpenPhilesException( String message) { super( message);}
}
