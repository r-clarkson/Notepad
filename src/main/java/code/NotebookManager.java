package notepad.src.main.java.code;
import java.util.*;
import notepad.src.main.java.code.Notebook;
import java.io.*;
import java.nio.file.*;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

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
    String identifier = "Appended Data: " + scan.next();
    char dataType = identifier.charAt(15);
    Note newNote = null;
    //https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
    try {
      Files.write(Paths.get(filename.getPath()), identifier.getBytes(), StandardOpenOption.APPEND);
      newNote = new Note(filename);
    }
    catch (IOException e) {
      //exception handling left as an exercise for the reader
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

  public Note addDictatedNote(File filename){
    Configuration configuration = new Configuration();
    //set up recognizer to be for english
    configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
    configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
    configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
    try {
    LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
    // Start recognition process with microphone
    recognizer.startRecognition(true);
    //get the result from the recognizer and print it if the word is not stop
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("Dependencies loaded. Dictate your note and type 'stop' to stop.");

    SpeechResult result = recognizer.getResult();

    System.out.println(result.getHypothesis());
    Files.write(Paths.get(filename.getPath()), result.getHypothesis().getBytes(), StandardOpenOption.APPEND);
    if (scan.next()=="stop"){
      recognizer.stopRecognition();
    }
  }

  catch (IOException e){
    System.out.println("Error.");
  }
    Note newNote = new Note(filename);

    return newNote;
  }

  public File getNoteFile(){
    File noteFile = null;
    System.out.println("Please enter the name of the note you would like to edit to or create (do not include file type, only the name). For a list of current note filenames, please type 'list'.");
    String input = scan.next();
    if (input.equals("list")){
      //print filenames of files in the notes folder

    }
    else{
      //create new text file (in notes folder) with filename entered and return it
      noteFile = new File(".." + File.separator + "Notepad" + File.separator + "notes" + File.separator + input + ".txt");
    }
    return noteFile;
  }


}
