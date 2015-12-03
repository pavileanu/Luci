import proiect1.filesystem.*;
import java.io.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.Date;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Scanner;

public class Console extends Frame implements FileSystem{
	static final int BLOCK_SIZE = 1024;		// [bytes]
	static final int ROOT_ENTRY_LENGTH = 32;	// [bytes]
	static final byte DESCRIPTOR = (byte)0;		// usual: F6 HEX
        
        static String currentDiskPath;
        static String currentPath = "$: ";

        
public static void addDiskRefernce(String output){
    BufferedWriter  writer = null;
    try {
        //writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("log.txt")));
        writer = new BufferedWriter(new FileWriter("disks.txt",true));
        writer.write(output + "\n");
    } catch (IOException ex) {
        // report
    } finally {
        try {
            writer.close();
        } catch (Exception ex) {/*ignore*/
            ex.printStackTrace();
        }
    }
}

public void abcd(String a, String b)
{
   System.out.println("test");
}


public static void runtimeCall(String command, Console c){
    try{
    String[] arguments = command.split("\\s+");
    
       
    if(arguments.length == 2){
        Method m = Console.class.getMethod(arguments[0], String.class);
        m.invoke(c, arguments[1]);
    }
    else if(arguments.length == 1){
        Method m = Console.class.getMethod(arguments[0]);
        m.invoke(c);
    }
    else if(arguments.length == 3){
        Class[] array={String.class, String.class};
        Method m = Console.class.getMethod(arguments[0], array);
        m.invoke(c, arguments[1], arguments[2]);
    }
    
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}

public void takePath(){
    System.out.print(currentPath+": ");
}

public static void main(String[]args){
   
    System.out.print(currentPath);
    Console c = new Console();
    Scanner sc = new Scanner(System.in);
    String input;
    while(true){
      input = sc.nextLine();
      if(input.equals(""))
        { 
            c.takePath();
            continue;  
        }    
      if(input.equals("exit"))
        {
          System.out.println("Consola a fost inchisa!"); 
          break; 
        }
      runtimeCall(input, c);
    }
    
    
    
    /*c.newDisk("Root3");
    //c.allDisks();
    c.load("Root2");
    runtimeCall("chdir a", c);
    
    //c.chdir("a");
    //c.chdir("adwa");
    //c.mkdir("b");
    //c.mkfile("fisier");
    System.out.println(currentPath);*/
}	

//mesajele afisate la lansarea in executie a simulatorului
public void copyright(){}

//constructor; trebuie sa permita lansarea comenzilor new (pentru un nou sistem) sau load (pentru incarcarea unui sistem existent pe HDD)		
Console(){}

/*
shell este metoda ce asigura interactivitatea programului; trebuie sa implementeze comenzile: 
new (creaza un nou sistem)                                  Facut
load (incarca un sistem existent)                           Facut
format (formateaza diskul)                
save (salveaza diskul pe HDD)           
shutdown (inchide sistemul)                                 Facut
mkdir (creaza un director)                                  Facut
mkfile (creaza si deschide un fisier)                       Facut
copy (copiaza un fisier/director la o cale indicata)
delete (sterge un fisier sau director)                      Facut
move (muta un fisier/director la o cale indicata) 
rename (schimba numele unui fisier/director) 
cd / chdir (schimba directorul curent)                      Facut
up (un director mai sus)                                    Facut
ls (listeaza continutul directorului curent; optiunea -a afiseaza si fisierele/directoarele ascunse, optiunea -l afiseaza in format lung
([atribute nume <DIR> dimensiune data timpul], <DIR> apare numai la directoare, data si timpul se refera la momentul 
la care a fost creat sau modificat(dupa prima modificare)), -al (format lung in care sunt afisate si fisierele/directoarele 
ascunse))                                                   Facut
attr (afiseaza si permite setarea atributelor fisierelor/directoarelor: Read-Only, Hidden, Encrypted)
encrypt (cripteaza un fisier)
decrypt (decripteaza un fisier criptat)
open (deschide un fisier)
edit (editeaza un fisier deschis in modul Read-Write)
view (afiseaza continutul unui fisier deschis in modul Read-Only)
close x (inchide fisierul cu calea x)
close (inchide toate fisierele deschise)
df (afiseaza dimensiunea spatiului liber)
date (afiseaza data sistemului)
time (afiseaza timpul sistemului)
info (afiseaza informatii despre disk)
help / h (afiseaza lista tuturor comenzilor cu o scurta descriere pt fiecare)
label (afiseaza eticheta discului)
chlabel (schimba eticheta discului)
clean (curata toate datele din blocurile care in FAT sunt indicate ca fiind sterse)
exec (lanseaza in executie un fisier de pe HDD)
save x (salveaza fisierul cu numele (sau calea) x pe HDD)
lf (incarca un fisier de pe HDD in diskul virtual)
*/
public void shell(){}

//====================COMMANDS========================//]

//creaza un nou disk si un nou sistem de fisiere pe acesta
public void newDisk(String diskName){
    try
    {
        File newDisk = new File(System.getProperty("user.dir").toString() + "/" + diskName);
        if (newDisk.exists()) {
            System.out.println("Unable to create " + newDisk.getAbsolutePath());
        }
        else {
            newDisk.mkdirs();
            System.out.println("New disk created:" + diskName);
            addDiskRefernce(diskName);
            currentDiskPath = System.getProperty("user.dir").toString() + "\\" + diskName;
            currentPath = currentDiskPath;
        }
    }
    finally{
       takePath();
    }
 }

public void allDisks(){
    try{
        System.out.println("Discurile existente in sistem:");
        String line; 
        int i=0;
        BufferedReader br = new BufferedReader(new FileReader("disks.txt"));
        while ((line = br.readLine()) != null) {
            i++;
            System.out.println("" + i + ":" + line);
        }
    }
    catch(Exception e){
    }
    finally{
       takePath();
    }
}

//incarca un disk existent de pe HDD; trebuie folosit un obiect de tip FileDialog
public void load(String diskName){
    try{
        String line; boolean ok=false;
        BufferedReader br = new BufferedReader(new FileReader("disks.txt"));
        while ((line = br.readLine()) != null) {
            if(line.equals(diskName))
                ok=true;
        }
        if(ok==true)
        {   
            System.out.println("Discul " + diskName + " a fost incarcat");
            currentDiskPath = System.getProperty("user.dir").toString() + "\\" + diskName;
            currentPath = currentDiskPath;
        }
        else System.out.println("Discul nu exista!");
    }
    catch(Exception e){
    }
    finally{
       takePath();
    }
}

//formateaza un disk
public void format(DiskInterface disk){}

//inchide sistemul de fisiere
public void shutdown(){
    System.out.println("Consola a fost inchisa!");
    System.exit(0);
}

public void mkdir(String newDirectoryName){
    try
    {
        File newDirectory = new File(currentPath + "\\" + newDirectoryName);
        if (newDirectory.exists()) {
            System.out.println("Unable to create " + newDirectory.getAbsolutePath());
        }
        else {
            newDirectory.mkdir();
            System.out.println("Directory" + newDirectoryName + "created");
        }
    }
    finally{
       takePath();
    }
}

/*
salveaza diskul pe HDD (primii 4 octeteti vor contine semnatura de disk (la alegere) (aceasta semnatura trebuie verifica 
la incarcare), urmatorii 2 octeti vor contine data salvarii, urmatorii 2 octeti vor contine timpul salvarii, urmatorii
4 octeti vor contine numarul de intrari din ROOT folosite, urmatorii 4 octeti vor contine dimensiunea diskului (numarul
de blocuri))
*/
void save() {}

//creaza si deschide fisierul cu numele specificat
public void mkfile(String fileName){
    try
    {
        File newFile = new File(currentPath + "\\" + fileName + ".txt");
        if (newFile.exists()) {
            System.out.println("Unable to create " + newFile.getAbsolutePath());
        }
        else {
            newFile.createNewFile();
            System.out.println("Directory" + fileName + "created");
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }
    
    finally{
       takePath();
    }
 }

//copiaza frompath la topath
void copy(String frompath, String topath) throws PhileNotFoundException{}

//deschide fisierul name in modul mode
void openFile(String entry, int mode){}

// OVERLOAD OVERLOAD OVERLOAD :((((((((((((
public void openFile(String entry){
    try{
            String targetPath = currentPath + "\\" + entry;
            File file = new File(targetPath);
            if(!file.exists()){
               System.out.println("Fisierul nu exista");  
               return;
            }
            Desktop dk=Desktop.getDesktop();
            // Open a file
            dk.open(file);
    }
    catch(Exception e)
    {
    }
    finally{
       takePath();
    }
}

//deschide fereastra editorului pentru fisierul openPhilePath
public void edit(String entry) throws PhileNotOpenException{
    try{
            String targetPath = currentPath + "\\" + entry;
            File file = new File(targetPath);
            if(!file.exists()){
               System.out.println("Fisierul nu exista");  
               return;
            }
            Desktop dk=Desktop.getDesktop();
            // Open a file
            dk.edit(file);
    }
    catch(Exception e)
    {
    }
    finally{
       takePath();
    }
} 

//deschide fereastra editorului pentru fisierul openPhilePath, in modul view (poate fi doar citit)
void view(String openPhilePath) throws PhileNotOpenException{} 

//encripteaza/decripteaza fisierul necriptat/criptat cu intrarea in ROOT entry
boolean encrypt_decrypt(Point entry){ return true; }

//lanseaza in executie un fisier de pe HDD, selectat prin intermediul unui FileDialog
void exec(){}

//inchide fisierul cu calea path
void close(String path){}

//inchide toate fisierele deschise
void closeAll(){}

//sterge un fisier/director cu numele name
public void delete(String name) throws PhileNotFoundException{
        
}

//sterge continutul tuturor blocurilor din zona de date, care in FAT figureaza ca fiind sterse
void clean(){}

//returneaza marimea spatiului liber de pe disk
void df(){}

//schimba directorul curent
public void chdir(String path){
    try{
        File currentDirectory = new File(currentPath);
        String[] allDirectories = currentDirectory.list();
        boolean directoryExist = false;
        for (String item : allDirectories) {
            if(item.equals(path))
            {
                directoryExist = true;
            }
        }
        if(directoryExist == true)
            currentPath = currentPath + "\\" + path;
        else
            System.out.println("The directory doesn't exists");
    }
    finally{
       takePath();
    }
}

//navigam up cu un director
public void up(){
    // Nu putem face up din disk
    if(currentPath.equals(currentDiskPath))    
        return;
    int lastIndex = currentPath.lastIndexOf("\\");
    currentPath = currentPath.substring(0, lastIndex
    );
    takePath();    
}



//listeaza continutul directorului curent (suporta 3 optiuni: -a, -l, -al)
public void ls(String param){} // JAVA NO IMPLICIT PARAMETERS COMMON

public void ls(){   // OVERLOADING :((((
    File folder = new File(currentPath);
    File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
          if (listOfFiles[i].isFile()) {
            System.out.println("File: " + listOfFiles[i].getName());
          } else if (listOfFiles[i].isDirectory()) {
            System.out.println("Directory: " + listOfFiles[i].getName());
          }
    }
    takePath();
}

//afiseaza/seteaza atributele unui fisier/director
void attr(String name){}

//afiseaza lista comenzilor
void help(){}

/*
afiseaza informatii despre disk (Disk label, Bytes per Sector, Sectors per Block [Cluter], Number of Blocks,
Fat type, Media Descriptor Byte, FAT Start Block, FAT Blocks Occupied, Root Dir Start Block, Root Dir Blocks Occupied,
Data Start Block, Data Blocks Occupied, Max Root Entries Count, Max DISK Entries, DISK Size, Used Bytes Count, 
Free Bytes Count si Free Entries Count)
*/
void info(){}

//scrie fisierul name de pe DISK pe HDD
void saveFileOnHDD(String name ) {}

//incarca un fisier de pe HDD in diskul virtual (foloseste un FileDialog)
void loadFilefromHDD() throws DiskFullException{ }

//================TEST FUNCTIONS===================//

//returneaza true daca fisierul/directorul cu intrarea entry este Read-Only si false in caz contrar
public boolean isReadOnly(String entry){
        
    try{
            String targetPath = currentPath + "\\" + entry;
            File file = new File(targetPath);
            if(!file.exists())
               System.out.println("Fisierul nu exista");          
            if(!file.canWrite())
            {
                System.out.println(entry + " este Read Only");
                return true;
            }
            else {
                System.out.println(entry + " nu este Read Only");
                return false;
            }
        }
    catch(Exception e){
        return false;
    }
    
    finally{
       takePath();
    }
  
}

//returneaza true daca fisierul/directorul cu intrarea entry este Hidden si false in caz contrar
public boolean isHidden(String entry){ 
        
    try{
            String targetPath = currentPath + "\\" + entry;
            File file = new File(targetPath);
            if(!file.exists())
                System.out.println("Fisierul nu exista");
            if(file.isHidden())   
            {
                System.out.println(entry + " este hidden");
                return true;
            }
            else {
                System.out.println(entry + " nu este hidden");
                return false;
            }
        }
    catch(Exception e){
        return false;
    }
    
    finally{
       takePath();
    }
  
}

//returneaza true daca entry reprezinta un director si false in caz contrar
public boolean isDirectory(String entry){ 
    
    try{
            String targetPath = currentPath + "\\" + entry;
            File file = new File(targetPath);
            if(!file.exists())
                System.out.println("Fisierul nu exista");
            if(file.isDirectory())   
            {
                System.out.println(entry + " este un director");
                return true;
            }
            else {
                System.out.println(entry + " nu este un director");
                return false;
            }
        }
    catch(Exception e){
        return false;
    }
    
    finally{
       takePath();
    }
             
}

//returneaza true daca fisierul cu intrarea entry este criptat si false in caz contrar
boolean isEncrypt(String entry){ return true; }

//returneaza true daca entry reprezinta un fisier/director care a fost sters si false in caz contrar
boolean isDelete(String entry){ return true; }

//returneaza true daca entry reprezinta un fisier/director care a fost modificat si false in caz contrar
boolean isModified(String entry){ return true; }

//returneaza intrarea cu numele name din directorul DirEntry (daca nu exista returneaza null)
Point exists(Point DirEntry, String name){ return null; }

//returneaza intrarea cu numele name din directorul directorul curent (daca nu exista returneaza null)
Point exists(String path){ return null; }

//returneaza true daca path reprezinta un fisier deschis si false in caz contrar
int isOpen(String path){ return 0; } 

//listeaza caile fisierelor deschise
void listOpenFiles(){ }

//returneaza true daca buffer reprezinta EOF si false in caz contrar
//param buffer trebuie sa fie o matrice cu macar 4 elemente. Metoda testeaza daca primele 4 elemente coincid cu -1 si in 
//acest caz returneaza true; in caz contrar returneaza false.
boolean isEOF (byte[] buffer) throws ArrayIndexOutOfBoundsException{ return true; }

//========================SET FUNCTIONS===========================//

//stabileste zonele diskului: BOOT, FAT, ROOT, DATA (la dimensiuni mai mari de 64K, DATA poate fi 95% din DISK)
void set_FAT_ROOT_Sizes(DiskInterface disk){}

//seteaza directorul curent
void setDir(){}

//seteaza octetul de stare a fisierului/directorului cu intrarea entry la valoarea b
void setStatus(Point entry, byte b){}

//seteaza numele fisierului/directorului cu intrarea entry la name
void setName(Point entry, String name){}

//seteaza dimensiunea fisierului cu intrarea entry la size
void setSize(Point entry, int size){}

//seteaza data fisierului/directorului cu intrarea entry
void setDate(Point entry){}

//seteaza timpul fisierului/directorului cu intrarea entry
void setTime(Point entry){}

//seteaza indexul blocului de start al fisierului/directorului cu intrarea entry la index
void setIndexStartBlock(Point entry, int index){}

//seteaza atributele fisierului/directorului cu intrarea entry
void setAttributes(Point entry, boolean readonly, boolean hidden, boolean directory, boolean encrypt){}

//seteaza atributele fisierului/directorului cu intrarea entry la b
void setAttributes(Point entry, int b){}

//seteaza directorul parinte al fisierului/directorului cu intrarea entry
void setDirectory(Point entry, Point DirEntry){}

//========================GET FUNCTIONS=====================//

//returneaza indexul blocului care are intrarea (in FAT) FAT_Entry 
int getBlockIndex(Point FAT_Entry){ return 0; }

//returneaza intrarea din FAT corespunzatoare indexului indexBlock
Point getFATEntry(int indexBlock){ return null; }

//returneaza indexul intrarii ROOT_Entry
int getIndexROOTEntry(Point ROOT_Entry){ return 0; }

//returneaza intrarea din ROOT cu indexul index
Point getROOTEntry(int index){ return null; }

//returneaza indexul continut intr-o intrare din FAT
int getFATEntryContent(Point FAT_Entry){ return 0; }

//returneaza octetul de stare al intrarii entry
byte getStatus(Point entry){ return 0;}

//returneaza numele continut in intrarea entry
String getName(Point entry){ return "";}

//returneaza intrarea corespunzatoare lui path (in cazul in care nu exista, returneaza null)
Point getEntry(String path){ return null; } 

//returneaza dimensiunea continuta in intrarea entry
String getSize(Point entry){ return "";}

//returneaza data continuta in intrarea entry
String getDate(Point entry){ return "";}

//returneaza data din numarul q
String getDate(int q){ return ""; }

//returneaza timpul continut in intrarea entry
String getTime(Point entry){ return ""; }

//returneaza timpul din numarul q
String getTime(int q){ return ""; }

//returneaza indexul blocului de start al intrarii entry
int getStartBlockIndex(Point entry){ return 0; }

//returneaza octetul atributelor al intrarii entry
byte getAttributes(Point entry){ return 0; }

//returneaza directorul parinte al intrarii entry
Point getDirectory(Point entry){ return null; }

//returneaza calea corespunzatoare intrarii entry
String getPath(Point entry){ return ""; }

//returneaza calea corespunzatoare lui name din directorul DirEntry
String getPath(Point DirEntry, String name){ return ""; }

//returneaza sfarsitul(EOF) intrarii entry
Point getEOF(Point entry){ return null; }

//returneza numarul de fisiere deschise
int getOpenFilesCount(){ return 0; } 

//returneaza numarul de intrari libere din ROOT
int getFreeEntriesCount(){ return 0; }

//returneaza intrarea din ROOT corespunzatoare pozitiei entry, din zona de date
Point getROOTEntryOfDirEntry(Point entry){ return null; }

//returneaza pozitiile intrarilor continute in directorul DirEntry
Point[] getDirEntries(Point DirEntry){ return null; }

//returneaza intrarile din ROOT continute in directorul DirEntry
Point[] getDirROOTEntries(Point DirEntry){ return null; }

//returneaza intrarile din ROOT continute in directorul curent
Point[] getDirROOTEntries(){ return null; }

//returneaza numarul de intrari continute in directorul DirEntry
int getDirEntriesCount(Point DirEntry){ return 0; }

//returneaza numarul de intrari continute in directorul curent
int getDirEntriesCount(){ return 0; }

//returneaza intrarile corespunzatoare directoarelor din directorul DirEntry
Point[] getDirectories (Point DirEntry){ return null; }

//returneaza intrarile corespunzatoare fisierelor din directorul DirEntry
Point[] getFiles (Point DirEntry){ return null; }

//returneaza intrarile corespunzatoare directoarelor din directorul curent
Point[] getDirectories (){ return null; }

//returneaza intrarile corespunzatoare fisierelor din directorul curent
Point[] getFiles (){ return null; }

//=======================TOOLS1=========================//

//folosita in load; face setarile initiale ale unui disk
public void startup(DiskInterface disk){}

//descrisa in interfata FileSystem
public PhileInterface create(String name) throws PhileAlreadyExistsException, TooManyOpenPhilesException, DiskFullException
{
    return null;
}

//descrisa in interfata FileSystem
public PhileInterface open(String name, int mode) throws PhileNotFoundException, TooManyOpenPhilesException, DiskFullException 
{
    return null;
}

//descrisa in interfata FileSystem
public void close(PhileInterface phile) throws PhileNotOpenException{}

//cauta si returneaza prima intrare libera din FAT
Point search_first_empty_FATentry(Point start) throws BadBlockException, DiskFullException
{
    return null;
}

//scrie in intrarea FAT_Entry numarul nextIndexBlock
void writeFATEntry(Point FAT_Entry, int nextIndexBlock){}

//Scrie in zona de date a directorului DirEntry numarul data, reprezentand indexul unei intrari introduse in acest director;
//data va fi scrisa pe pozitia entry sau, daca entry==null, va fi scrisa pe prima pozitie libera din DirEntry
void write_EntryIndex_in_DirectoryData (Point DirEntry, int data, Point entry){} 

//adauga un nou block, relativ la fat_entry (folosita cand se scrie in fisiere)
int addBlock (Point fat_entry) 
{
    return 0;
}

//adauga o noua intrare in ROOT cu numele name; tip poate lua valorile "directory" si "file"
//intrarea va fi in directorul curent si va avea ca intrare FAT, prima intrare FAT libera
void new_root_entry(String name, String tip){}

//adauga o noua intrare in ROOT cu numele name; tip poate lua valorile "directory" si "file"
//intrarea va fi in directorul DirEntry si va avea ca intrare FAT, prima intrare FAT libera
void new_root_entry(Point DirEntry, String name, String tip){}

//adauga o noua intrare in ROOT cu numele name; tip poate lua valorile "directory" si "file"
//intrarea va fi in directorul DirEntry si va avea ca intrare FAT intrarea FAT_Entry
void new_root_entry(Point FAT_Entry, Point DirEntry, String name, String tip) throws DiskFullException{}

//sterge din ROOT intrarea entry
void delete_root_entry(Point entry) throws BadBlockException {}

//sterge directorul entry cu toate fisierele si subdirectoarele sale
void deleteDirectory(Point entry) {}

//sterge intrarea entry, corespunzatoare unui fisier
void deleteEntry (Point entry) {}

//adauga newentry la coada tree, reprezentand calea spre directorul curent
void appendTree (Point newentry){}

//sterge ultimul element din coada tree
void delete_last_TreeEntry (){}

//returneaza un nume valid de fisier sau director(alphanumeric, cu lungimea <=14 si care nu a mai fost dat si altui 
//fisier/director din directorul curent)
String name(String s){ return null; }

//=======================TOOLS2=========================//

//testeaza daca sirul s este alphanumeric
boolean testalphanumerical(String s){ return true; }

//cripteaza/decripteaza sirul s
String encrypt_decrypt (String s) { return ""; }

//cripteaza/decripteaza matricea de caractere ac
byte[] encrypt_decrypt (char[] ac) { return null; }

//cripteaza/decripteaza matricea de octeti buffer
byte[] encrypt_decrypt (byte[] buffer) { return null; }

//transforma matricea de caractere chars intr-o matrice de octeti
byte[] charsToBytes(char[] chars){ return null;}

//transforma matricea de octeti chars intr-o matrice de octeti bytes, intr-o matrice de caractere
char[] bytesToChars(byte[] bytes){ return null; }

//buffer.length=4; transforma buffer intr-un intreg fara semn
int readUnsignedInt(byte[] buffer) throws ArrayIndexOutOfBoundsException
{
    return 0;
}

//buffer.length=2; transforma buffer intr-un intreg fara semn
int readUnsignedShort(byte[] buffer) throws ArrayIndexOutOfBoundsException
{
    return 0;
}

//returneaza octetii unui numar intreg de tipul short
byte[] getBytes(char c)
{
    return null;
}

//returneaza un caracter din doi octeti
char getChar(byte[] b)
{
    return 'a';
}

//returneaza octetii unui numar intreg
byte[] getBytes(int i){ return null; }

//scrie numarul i cu virgule (ex. 100,000,000)
String write_with_commas (int i)
{
    return "";
}
    
}