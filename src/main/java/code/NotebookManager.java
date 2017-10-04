package notepad.src.main.java.code;

import java.util.*;
import notepad.src.main.java.code.Notebook;

public class NotebookManager{
    
    
    // TODO: 
    
    
    public boolean writesNoteFile(){
        boolean written = null
        try{
            PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
            writer.println("The first line");
            writer.println("The second line");
            writer.close();
            written = True
        } catch (IOException e) {
            System.out.println("File did not write properly")
            written = False
        }
        return written
    }
    
    
    
    
}