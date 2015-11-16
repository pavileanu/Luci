package proiect1.filesystem;

/**
 * Este lansata cand se incearca denumirea unui fisier cu un nume care deja a fost alocat (in directorul respectiv)
 */
public class PhileAlreadyExistsException extends Exception {
    public PhileAlreadyExistsException() {}
    public PhileAlreadyExistsException( String message) { super( message);}
}
