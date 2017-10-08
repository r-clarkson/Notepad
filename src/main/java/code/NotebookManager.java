package notepad.src.main.java.code;

import java.util.*;
import notepad.src.main.java.code.Notebook;
import Scanner.*;

/* 
Due to the construction of the Note class, we will create a txt file,
then pull the necessary data from the txt file to create the Note object.
*/

public class NotebookManager{

    
    //TODO: would you to like to analyze existing note or create a note?
    
    public void noteCreationProcess(){
        Scanner scan = new Scanner(System.in)
        String stringToWrite = null;
        char dataType = 'X';
        String value = null
        System.out.println("Before you enter text, you must add your note data.")
        System.out.println("What type of data would you like to add?");
        dataType = scan.next():
        boolean runLinkProcess = False;
        while !(dataType.toUpper().equal('E')):
            switch dataType:
            data_type = raw_input("\tMention -> M\n\tHashtag? -> H\n\tLink a Note -> L\n\tFinish and create note? -> F\n\tAbandon note -> A\n")
            case data_type.upper() == 'M':
                System.out.println("Who would you like to mention? Include @: ")
                value =scan.nextln();
                stringToWrite.append(value,'\n')
            case data_type.upper() == 'H':
                System.out.println("What would you like to tag? Include #, one tag at a time: ")
                value = scan.nextln()
                stringToWrite.append(value,'\n')
            case data_type.upper() == 'L':
                //TODO: how do we want to link notes??
                stringToWrite.append(value,'\n')
            case data_type.upper() == 'F':
                System.out.println("What would you like to name your note?");
                String filename = scan.nextln();
                // TODO: note creation process of Note object
            case data_type.upper() == 'A':
                System.out.println("Note creation aborted!");
                break;
            default: 
              System.out.println("Incorrect entry please try again!");
    }
    
    public void captureNoteBody(){
        char value = null;
        System.out.println("How would you like to enter the text? Dictate or Type? (D or T)\n");
        char decision = scan.nextln();
        if decision.toUpper().equals('D'):
            // start dictation module
        else if decision.toUpper().equals('T'):
            // create note from typing
        else:
            System.out.println("Desicion not recognized. Please try again");
            captureNoteBody()
        
    }
    
    public void writesNoteFile(String filename, String stringToWrite){
        try{
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.print(stringToWrite);
            writer.close();
            System.out.println("File written properly!");
        } catch (IOException e) {
            System.out.println("File did not write properly");
        }
    }
    
    
    
    
}