import java.awt.Point;
import proiect1.filesystem.DiskFullException;
import proiect1.filesystem.PhileInterface;


class Phile implements PhileInterface{
    
    Console console;
    Point entry;
    int mode;
    int position;
	
    Phile(Console console, Point entry, int mode){
            this.console = console;
            this.entry = entry;
            this.mode = mode;
    }

    //vezi PhileInterface
    public int read (byte buffer[], int readCount) throws proiect1.filesystem.IllegalAccessException
    {
        return 0;
    }

    //vezi PhileInterface
    public int write (byte buffer[], int writeCount) throws proiect1.filesystem.IllegalAccessException, DiskFullException
    {
        return 0;
    }

    //vezi PhileInterface
    public void seek (int position){}

    //vezi PhileInterface
    public int getSize()
    {
        return 0;
    }

    //vezi PhileInterface
    public int getMode()
    {
        return 0;
    }
    
}

