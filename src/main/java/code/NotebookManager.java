package notepad.src.main.java.code;

import java.util.*;
import notepad.src.main.java.code.Notebook;
import java.io.*;


/*
Due to the construction of the Note class, we will create a txt file,
then pull the necessary data from the txt file to create the Note object.
*/

public class NotebookManager{


  //TODO: would you to like to analyze existing note or create a note?

  public Note addIdentifier(String identifier){
    Scanner scan = new Scanner(System.in);
    char dataType = identifier.charAt(0);
    Note newNote = null;
    /** each of these cases should add the identifier value to the hashmap for that type of identifier as well as the note name that they wanted to add it to */
    switch (dataType){
      case '@':
      /** go to hashmap for @mentions and add the key value (the string identifier parameter). Then add the note name to this key value's LL in the hashmap */
      //add note name to the LL by using I/O to retrieve text file, appending the identifier to the note, and then returning the note to main so it can be added to notebook
      //instantiate new note with the edited text file: newNote = Note(editedFile)

      break;
      case '^':
      break;
      case '#':
      //TODO: how do we want to link notes??
      break;
      /** w for www. in urls */
      default:
      System.out.println("Incorrect entry please try again!");
      break;
    }
    return newNote;

  }

/** takes in a filename that the user wants to start as a text file and asks them to type what they want to add.
* then saves the text that they wrote into the text file, makes it into a note object, and returns it to main so it can be added into the notebook */
  public Note writesNoteFile(String filename, String stringToWrite){
    Note note = null;
    try{
      PrintWriter writer = new PrintWriter(filename, "UTF-8");
      writer.print(stringToWrite);
      writer.close();
      System.out.println("File written properly!");
      //turn new texxt file into note with note = new Note(textfile)
    } catch (IOException e) {
      System.out.println("File did not write properly");
    }
    return note;
  }




}
