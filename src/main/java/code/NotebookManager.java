package notepad.src.main.java.code;
import java.util.*;
import notepad.src.main.java.code.Notebook;
import java.io.*;

/*
Due to the construction of the Note class, we will create a txt file,
then pull the necessary data from the txt file to create the Note object.
*/
public class NotebookManager{
  Scanner scan = new Scanner(System.in);
  /** given the user's input, adds a note of that type */
  public void editNote(String type){
    if (type.equals("-t")){
      addTypedNote(getNoteFile());
    }
    else if (type.equals("-d")){
      addDictatedNote(getNoteFile());
    }
    else if (type.equals("-i")){
      addIdentifier(getNoteFile());
    }
  }
  /** adds a specific identifier to a note and returns the note */
  public Note addIdentifier(File filename){
    System.out.println("Please enter the identifier you would like to add, and include its type symbol ()#/@/!/^/www.)");
    String identifier = scan.next();
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
      case 'w':
      /** w for www. in urls */
      break;
      default:
      System.out.println("Incorrect entry please try again!");
      break;
    }
    return newNote;

  }

/** takes in a filename that the user wants to start as a text file and asks them to type what they want to add.
* then saves the text that they wrote into the text file, makes it into a note object, and returns it to main so it can be added into the notebook */
  public Note addTypedNote(File filename){
    Note note = null;

    try{
      PrintWriter writer = new PrintWriter(filename, "UTF-8");
      writer.close();
      System.out.println("File written properly!");
      //turn new texxt file into note with note = new Note(textfile)
    } catch (IOException e) {
      System.out.println("File did not write properly");
    }
    return note;
  }

  public void deleteNote(File filename){

  }

  public void addDictatedNote(File filename){

  }

public File getNoteFile(){
  File noteFile = null;
  System.out.println("Please enter the name of the note you would like to add to or create. For a list of current note filenames, please type 'list'.");
  if (scan.next().equals("list")){
    //print filenames of files in the notes folder
  }
  else{
    //create new text file (in notes folder) with filename entered and return the string of it
  }
  return noteFile;
}


}
