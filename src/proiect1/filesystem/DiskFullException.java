package proiect1.filesystem;

/**
 * Este aruncata cand nu mai este spatiu pe disk sau cand nu mai sunt intrari disponibile.
 */
public class DiskFullException extends Exception {
    public DiskFullException() {}
    public DiskFullException( String message) { super( message);}
}
