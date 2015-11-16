import proiect1.filesystem.*;
import java.io.*;
import java.awt.*;
import java.util.Date;
import java.net.URL;
import java.net.MalformedURLException;

public class Console extends Frame implements FileSystem{
	static final int BLOCK_SIZE = 1024;		// [bytes]
	static final int ROOT_ENTRY_LENGTH = 32;	// [bytes]
	static final byte DESCRIPTOR = (byte)0;		// usual: F6 HEX

	
public static void main(String[]args){}	

//mesajele afisate la lansarea in executie a simulatorului
void copyright(){}

//constructor; trebuie sa permita lansarea comenzilor new (pentru un nou sistem) sau load (pentru incarcarea unui sistem existent pe HDD)		
Console(){}

/*
shell este metoda ce asigura interactivitatea programului; trebuie sa implementeze comenzile: 
new (creaza un nou sistem)
load (incarca un sistem existent) 
format (formateaza diskul)
save (salveaza diskul pe HDD) 
shutdown (inchide sistemul)
mkdir (creaza un director)
mkfile (creaza si deschide un fisier)
copy (copiaza un fisier/director la o cale indicata)
delete (sterge un fisier sau director) 
move (muta un fisier/director la o cale indicata) 
rename (schimba numele unui fisier/director) 
cd / chdir (schimba directorul curent) 
up (un director mai sus)
ls (listeaza continutul directorului curent; optiunea -a afiseaza si fisierele/directoarele ascunse, optiunea -l afiseaza in format lung
([atribute nume <DIR> dimensiune data timpul], <DIR> apare numai la directoare, data si timpul se refera la momentul 
la care a fost creat sau modificat(dupa prima modificare)), -al (format lung in care sunt afisate si fisierele/directoarele 
ascunse))
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
void shell(){}

//====================COMMANDS========================//]

//creaza un nou disk si un nou sistem de fisiere pe acesta
void newDisk(){}

//incarca un disk existent de pe HDD; trebuie folosit un obiect de tip FileDialog
void load(){}

//formateaza un disk
public void format(DiskInterface disk){}

//inchide sistemul de fisiere
public void shutdown(){}

/*
salveaza diskul pe HDD (primii 4 octeteti vor contine semnatura de disk (la alegere) (aceasta semnatura trebuie verifica 
la incarcare), urmatorii 2 octeti vor contine data salvarii, urmatorii 2 octeti vor contine timpul salvarii, urmatorii
4 octeti vor contine numarul de intrari din ROOT folosite, urmatorii 4 octeti vor contine dimensiunea diskului (numarul
de blocuri))
*/
void save() {}

//creaza si deschide fisierul cu numele specificat
void mkfile(String name){}

//copiaza frompath la topath
void copy(String frompath, String topath) throws PhileNotFoundException{}

//deschide fisierul name in modul mode
void openFile(String name, int mode){}

//deschide fereastra editorului pentru fisierul openPhilePath
void edit(String openPhilePath) throws PhileNotOpenException{} 

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
public void delete(String name) throws PhileNotFoundException{}

//sterge continutul tuturor blocurilor din zona de date, care in FAT figureaza ca fiind sterse
void clean(){}

//returneaza marimea spatiului liber de pe disk
void df(){}

//schimba directorul curent
void chdir (String path){}

//listeaza continutul directorului curent (suporta 3 optiuni: -a, -l, -al)
void ls(String param){}

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
void saveFileOnHDD(String name) {}

//incarca un fisier de pe HDD in diskul virtual (foloseste un FileDialog)
void loadFilefromHDD() throws DiskFullException{ }

//================TEST FUNCTIONS===================//

//returneaza true daca fisierul/directorul cu intrarea entry este Read-Only si false in caz contrar
boolean isReadOnly(Point entry){ return true; }

//returneaza true daca fisierul/directorul cu intrarea entry este Hidden si false in caz contrar
boolean isHidden(Point entry){ return true; }

//returneaza true daca entry reprezinta un director si false in caz contrar
boolean isDirectory(Point entry){ return true; }

//returneaza true daca fisierul cu intrarea entry este criptat si false in caz contrar
boolean isEncrypt(Point entry){ return true; }

//returneaza true daca entry reprezinta un fisier/director care a fost sters si false in caz contrar
boolean isDelete(Point entry){ return true; }

//returneaza true daca entry reprezinta un fisier/director care a fost modificat si false in caz contrar
boolean isModified(Point entry){ return true; }

//returneaza intrarea cu numele name din directorul DirEntry (daca nu exista returneaza null)
Point exists(Point DirEntry, String name){ return null; }

//returneaza intrarea cu numele name din directorul directorul curent (daca nu exista returneaza null)
Point exists(String path){ return null; }

//returneaza true daca name reprezinta un director si false in caz contrar
boolean isDirectory(String name){ return true; }

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


//==============================================================================//


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


//==============================================================================//

//fereastra de editare a fisierelor

class Edit extends Frame{
	TextArea text;
	Console console;
	Point PhileEntry;
	Phile phile;
	String name;
	int index;
	int mode;

//constructor
Edit(Console console, int index, int mode){}

//adauga bara cu meniuri (aceasta va contine meniul File cu submeniurile Save si Exit pt modul edit si numai
//meniul Exit, pt modul View)
void addMenuBar(){}

public boolean handleEvent(Event e)
{
    return true;
}

//scrie continutul fisierului in TextArea
void load(){}

//salveaza modificarile facute (sterge intreg continutul, seteaza position=0, scrie noul continut)
void save(){}

//folosita in save; scrie fisierul prin intermediul lui phile 
void write(){}

//folosita in save; sterge continutul fisierului inaintea salvarii
void delete(Point EOF){}

//inchide fereastra editorului (fara a se iesi din program)
void close(){}

}



















