package proiect1.filesystem;

public class Disk implements DiskInterface {
    public byte[][] disk;
    private int diskSize;

    public Disk( int diskSize) {
	this.diskSize = diskSize; 
	disk = new byte[diskSize][];
    }
    
    public void format() {disk = new byte[diskSize][];}
    
    public int getSize() {return diskSize;}
    
    public void writeBlock( byte[] buffer, int blockNum) throws BadBlockException {
	if( blockNum < 0 || blockNum >= diskSize) throw new BadBlockException( "Illegal block number: " + blockNum);
	if( buffer.length != BLOCK_SIZE) throw new BadBlockException( "Wrong buffer size: " + buffer.length);
	disk[blockNum] = buffer;
    }
    
    public byte[] readBlock( int blockNum) throws BadBlockException {
	if( blockNum < 0 || blockNum >= diskSize) throw new BadBlockException( "Illegal block number: " + blockNum);
	byte[] block = new byte[BLOCK_SIZE];
	for( int i=0; i<BLOCK_SIZE; i++) block[i] = disk[blockNum][i];
	return block;
    }  
}
