import java.awt.Event;
import java.awt.Frame;
import java.awt.Point;
import java.awt.TextArea;


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
