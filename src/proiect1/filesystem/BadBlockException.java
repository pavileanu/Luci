package proiect1.filesystem;

/**
 * Exceptie aruncata cand buffer nu are dimensiunea proiect1.filesystem.DiskInterface.BLOCK_SIZE
 * sau cand numarul blocului este ilegal.
 */
public class BadBlockException extends Exception {
    public BadBlockException() {}
    public BadBlockException( String message) { super( message);}
}
