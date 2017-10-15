package notepad.src.main.java.code;
import java.util.*;
import java.util.logging.Logger;
import notepad.src.main.java.code.Notebook;
import java.io.*;
import java.nio.file.*;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

/**
* Due to the construction of the Note class, we will create a txt file with whatever method the user chose,
* then the text file will be returned after it is turned into a note object
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

  /**
  * Adds a specific type of identifier to a note the user chooses by filename
  * This is done by appending the desired identifier to the note, deleting the previous version from the notebook,
  * and then re-adding the edited note https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
  */
  public Note addIdentifier(File filename){
    System.out.println("Please enter the identifier you would like to add, and include its type symbol ()#/@/!/^/www.)");
    String identifier = "Appended Data: " + scan.next();
    char dataType = identifier.charAt(15);
    Note newNote = null;
    try {
      //TODO: delete old version of note
      Files.write(Paths.get(filename.getPath()), identifier.getBytes(), StandardOpenOption.APPEND);
      newNote = new Note(filename);
    }
    catch (IOException e) {
      //TODO: better options here. user should be able to quit or go back to main menu or something.
      System.out.println("Error. Please try again.");
    }
    return newNote;
  }

  /**
  * TODO: Method will use getNoteFile to get the text file to be written to.
  * then the user will type what they wish to add and it will be written to the text file.
  * previous note deleted and then new note returned so it can be re-added to the notebook in main
  */
   public Note addTypedNote(File filename){
    Note note = null;
    try{
      PrintWriter writer = new PrintWriter(filename, "UTF-8");
      System.out.println("Enter the body of your note: ");    
      //testing only appended one word to the file so a while loop should fix it...
      String body;
      while(scan.hasNextLine()){
        body = scan.nextLine();
        writer.write(body);
      }
      writer.close();
      System.out.println("File written properly!");
      //read in text file and from that create a Note obj
    } catch (IOException e) {
      System.out.println("File did not write properly");
    }
    return note;
  }

  /**
  * TODO: Method should iterate through hashmaps' LLs of notebook and delete references of the given filename
  * if the LL of a certain key in the HM is only size 1, then delete the key because it only existed due to that file
  */
  public void deleteNote(File filename){

  }

  /**
  * Using Sphinx4 API, record from user's microphone and then add recording to a text file
  * See inside for more details
  */
  public Note addDictatedNote(File filename){
    /** set up a configuration for english, and set recording variable to true */
    Configuration configuration = new Configuration();
    boolean record = true;
    configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
    configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
    configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

    try {
        /** clear the screen and give user instructions */
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Dependencies loaded. Dictate your note and type 'stop' to stop.");
        /* somehwat messy fix to keep log messages from sphinx4 appearing in the console https://stackoverflow.com/questions/35560969/disable-console-mess-in-cmusphinx4 */
        Logger cmRootLogger = Logger.getLogger("default.config");
        cmRootLogger.setLevel(java.util.logging.Level.OFF);
        String conFile = System.getProperty("java.util.logging.config.file");
        if (conFile == null) {
          System.setProperty("java.util.logging.config.file", "ignoreAllSphinx4LoggingOutput");
        }
        ArrayList<SpeechResult> results = new ArrayList<SpeechResult>();
        /** initialize recognizer which uses the microphone to start speech recognition */
        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
        recognizer.startRecognition(true);
        /** result from recognizer is taken and the hypothesis of the word is written to the file */
        SpeechResult result = recognizer.getResult();
        results.add(result);
        //TODO: there should be a better flow here/better way to keep recording going and then stop on command
        if (scan.next()=="stop"){
          recognizer.stopRecognition();
        }

        for (int i = 0; i < results.size(); i++){
          Files.write(Paths.get(filename.getPath()), results.get(i).getHypothesis().getBytes(), StandardOpenOption.APPEND);
        }
        /** recording is stopped when user types the command */
    }
    catch (IOException e){
      //TODO: better options here. user should be able to quit or go back to main menu or something.
      System.out.println("Error.");
    }
    /** create new note from the text file which was added to and then return it */
    Note newNote = new Note(filename);
    return newNote;
  }

  /**
  * Method to create and return new text file if user is adding a file
  * Otherwise will return the filename the user wishes to edit
  */
  public File getNoteFile(){

    File noteFile = new File(".." + File.separator + "Notepad" + File.separator + "notes");
    System.out.println("Please enter the name of the note you would like to edit to or create (do not include file type, only the name). For a list of current note filenames, please type 'list'.");
    String input = scan.nextLine();
    /** lists files available to edit or creates filename user typed in */
    try{
      if (input.equals("list")){
        if (noteFile.isDirectory()){
          for (final File fileEntry : noteFile.listFiles()) {
            System.out.println("File: " + fileEntry.getName());
          }
        }
        getNoteFile();
      }
      // TODO: only functionality for creating a new note at the moment. need to be able to edit existing notes as well.
      else{
        //create new text file (in notes folder) with filename entered and return it
        noteFile = new File(".." + File.separator + "Notepad" + File.separator + "notes" + File.separator + input + ".txt");
        Files.createFile(Paths.get(noteFile.getPath()));
        System.out.println(noteFile.getName() + " created in notes directory.");
      }
    }
    catch (NullPointerException e){
      System.out.println("Null Pointer Error.");
    }
    catch (IOException i){
      System.out.println("IO Error.");
    }
    return noteFile;
  }
}
