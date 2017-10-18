package src.main.java;
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
* This class determines which edit the user wants to make and to which file (if one specifically)
* Then it hands off the edit to the correct editor object which manipulates the actual text files in the notes directory
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

  public boolean editNote(String type){
    File filepath = new File(".." + File.separator + "Notepad" + File.separator + "notes" + File.separator);
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
      noteDeleter.deleteNoteMentions(getNoteMention());
      break;
      case "-e":
      noteAdder.appendToNote(getNoteFile());
      break;
      default:
      System.out.println("Option not recognized.");
      return false;
    }
    return true;
  }

  /**
  * Method to create and return new text file if user is adding a file
  * Otherwise will return the file the user wishes to edit if the name already exists
  * There is also an option to list all current files (if you want to edit and need to see what you can choose from)
  */

  public File getNoteFile(){
    String input;
    File noteFile = new File(".." + File.separator + "Notepad" + File.separator + "notes");
    System.out.println("Please enter the name of the note you would like to edit/create/delete (do not include filetype). Or for a list of current notefilenames, please type 'list'.");

    if (scan.hasNext()){
      input = scan.nextLine();
    }
    else{
      return null;
    }

    try{
      /** lets user print existing files/notes or creates a new file (if it doesnt exist already) based on name entered */
      if (input.equals("list")){
        listFiles(noteFile);
      }
      else {
        noteFile = new File(".." + File.separator + "Notepad" + File.separator + "notes" + File.separator + input + ".txt");
        if (!noteFile.exists()){
          Files.createFile(Paths.get(noteFile.getPath()));
        }
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

  /**
  * Gets users input of what they want to delete from the notebook
  **/

  public String getNoteMention(){
    String text;
    System.out.println("Please type what you would like to delete from your notebook. If it is an identifier, include the symbol");
    if (scan.hasNext()){
      text = scan.nextLine();
    }
    else{
      text = null;
    }
    return text;
  }

  /**
  * Lists current files in given directory
  **/

  public boolean listFiles(File noteFile){
    if (noteFile.isDirectory()){
      for (final File fileEntry : noteFile.listFiles()) {
        System.out.println("File: " + fileEntry.getName());
      }
      getNoteFile();
      return true;
    }
    return false;
  }
}
