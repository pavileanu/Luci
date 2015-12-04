package proiect1.filesystem;

import java.util.Date;

public class Disk implements DiskInterface {

    public byte[][] disk;
    public String name;
    private int diskSize;
    private final int rootBegin;
    private final int fatBegin = 1;

    public Disk( int diskSize, String name) {
	this.diskSize = diskSize; 
        this.name = name;
	disk = new byte[diskSize][];
        rootBegin = diskSize * 7/10;
        disk[0] = new byte[BLOCK_SIZE];
        initializeFat();
        initializeRoot();
    }
    
    private void initializeFat(){
       for(int i = fatBegin; i < rootBegin; i++){
           disk[i] = new byte[4];
       }
    }
    
    private void initializeRoot(){
       for(int i = rootBegin; i < diskSize; i++){
           disk[i] = new byte[32];
       }       
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
    
    // lineFrom must be smaller than lineTo, this method is for deleting files from fat
    public void moveData(int lineFrom, int lineTo, int limitMoving){
        for(int i = lineFrom; i < limitMoving - (lineTo - lineFrom); i++)
          disk[i] = disk[i + lineTo - lineFrom];
    }
    
    public void populateRootBlock(int state, String name, int dimmension, Date updatedAt, Date createdAt, int indexFat, int attribute, int parent){
       
    }
    
    public int freeRootIndex(){
        for(int i = rootBegin; i < diskSize; i++)
            if(disk[i][0] == 0)
                return i;
        return -1;
    }
    
    public int freeFatIndex(){
        for(int i=fatBegin; i < rootBegin; i++)
            if(disk[i][0] == 0)
                return i;
        return -1;
    }
    
    
    public int findFile(String name){
        return 0;
    }
}
