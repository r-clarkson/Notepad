package notepad.src.main.java.code;
import java.util.*;
import java.util.logging.Logger;
import java.io.*;
import java.nio.file.*;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import java.util.regex.*;
import java.nio.charset.StandardCharsets;


/**
* Due to the construction of the Note class, we will create a txt file with whatever method the user chose,
* then the text file will be returned after it is turned into a note object
*/

public class NotebookManager{
  Scanner scan;
  NoteAdder noteAdder;
  NoteDeleter noteDeleter;

  public NotebookManager(){
    scan = new Scanner(System.in);
    noteAdder = new NoteAdder();
    noteDeleter = new NoteDeleter();
  }


  /**
  * Based on user's second argument, performs the correct edit to the notebook
  * Uses this classes functions to get the files and further input...
  * ...as well as (mostly) the noteAdder and noteDelete objects to perform the edits
  **/

  public void editNote(String type){
    File filepath = null;
    switch (type){
      case "-t":
      noteAdder.addTypedNote(getNoteFile());
      break;
      case "-d":
      noteAdder.addDictatedNote(getNoteFile());
      break;
      case "-x":
      noteDeleter.deleteNoteMentions(noteDeleter.deleteNote(getNoteFile()));
      break;
      case "-i":
      noteDeleter.removeMentionFromFile(getNoteFile(),getNoteMention());
      case "-e":
      noteAdder.appendToNote(getNoteFile());
      break;
      default:
      System.out.println("Option not recognized.");
      break;
    }
  }

  /**
  * Method to create and return new text file if user is adding a file
  * Otherwise will return the file the user wishes to edit if the name already exists
  * There is also an option to list all current files (if you want to edit and need to see what you can choose from)
  */

  public File getNoteFile(){
    File noteFile = new File(".." + File.separator + "Notepad" + File.separator + "notes");
    System.out.println("Please enter the name of the note you would like to edit/create/delete (do not include file type, only the name).\nOr for a list of current notefilenames, please type 'list'.");

    String input = scan.nextLine();

    try{
      if (input.equals("list") && noteFile.isDirectory()){
        for (final File fileEntry : noteFile.listFiles()) {
          System.out.println("File: " + fileEntry.getName());
        }
        getNoteFile();
      }

      else {
        noteFile = new File(".." + File.separator + "Notepad" + File.separator + "notes" + File.separator + input + ".txt");
        if (!noteFile.exists()){
          Files.createFile(Paths.get(noteFile.getPath()));
        }
        System.out.println(noteFile.getName() + " retrieved.");
      }
    }
    catch (NullPointerException e){
      System.out.println("Error.");
    }
    catch (IOException i){
      System.out.println("Error.");
    }
    return noteFile;
  }

  /** gets users input of what they want to delete from the notebook **/
  public String getNoteMention(){
    System.out.println("Please type what you would like to delete from your notebook. If it is an identifier, include the symbol");
    String text = scan.nextLine();
    return text;
  }
}
