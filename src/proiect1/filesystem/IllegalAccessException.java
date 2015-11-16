package proiect1.filesystem;

/**
 * Exceptie aruncata cand se incearca scrierea intr-un fisier Read-Only sau cand se incearca citirea dupa EOF.
 */
public class IllegalAccessException extends Exception {
    public IllegalAccessException() {}
    public IllegalAccessException( String message) { super( message);}
}
