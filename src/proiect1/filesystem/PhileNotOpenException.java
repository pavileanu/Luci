package proiect1.filesystem;

/**
 * Exceptie aruncata la incercarea de a opera asupra unui fisier care nu este deschis.
 */
public class PhileNotOpenException extends Exception {
    public PhileNotOpenException() {}
    public PhileNotOpenException( String message) { super( message);}
}
