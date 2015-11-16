package proiect1.filesystem;

/**
 * Exceptie aruncata la incercarea de a opera asupra unui fisier care nu exista.
 */
public class PhileNotFoundException extends Exception {
    public PhileNotFoundException() {}
    public PhileNotFoundException( String message) { super( message);}
}
