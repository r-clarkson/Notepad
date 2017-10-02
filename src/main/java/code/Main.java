package notepad.src.main.java.code;
import java.nio.file.*;
import java.util.*;
import java.io.*;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;



/**  MainClass initiates process by passing on files in the "notes" folder to be made into note objects, which are then put into a notebook
Reports are then generated based on user input and the said notebook
**/
public class Main{

  /** Notebook object is created,
  filepath is taken from user (or not, depending on their input).
  files then passed to be parsed, and afterwards, reports gathered
  **/

  public static void main(String[] args) throws Exception{
    /** code derived from sphinx4 examples at https://github.com/cmusphinx/sphinx4/blob/master/sphinx4-samples/src/main/java/edu/cmu/sphinx/demo/dialog/DialogDemo.java */
    Configuration configuration = new Configuration();
    //set up recognizer to be for english
    configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
    configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
    configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

    LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
    // Start recognition process with microphone
    recognizer.startRecognition(true);
    //get the result from the recognizer and print it if the word is not stop
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("Dependencies loaded. Say some words and see them printed! Say stop clearly (sometimes twice) to stop recording. (Scroll up after you say stop cuz the screen clears)");
    SpeechResult result = recognizer.getResult();
    System.out.println(result.getHypothesis());
    //quit printing if word is stop
    if (result.getHypothesis() == "stop"){
      recognizer.stopRecognition();
    }

    Notebook notebook = new Notebook();
    passFiles(getFilePath(),notebook);
    generateReports(notebook);
  }

  /**
  prints the start menu to see if the user wants to put in their own filepath,
  then guides the user through getting the folder correct
  returns the folder of notes to main so they can be passed to passFiles
  **/
  public static File getFilePath(){
    printStartMenu();
    File noteFolder;
    Scanner scanner = new Scanner(System.in);
    int input = 0;
    if(scanner.hasNextInt()){
      input = scanner.nextInt();
    }

    /** different cases for user input */
    try{
      switch(input){
        /** user inputs direct filepath */
        case 1:
        System.out.println("Please enter your ABSOLUTE filepath:");
        noteFolder = new File(scanner.next());
        if (!noteFolder.isDirectory()){
          System.out.println("This is not a directory. Try again.");
          getFilePath();
        }
        break;
        /** user has put notes in the notes folder */
        case 2:
        noteFolder = new File(".." + File.separator + "Notepad" + File.separator + "notes" + File.separator);
        break;
        /** user wants to exit */
        case 3:
        System.exit(0);
        default:
        return null;
      }
      return noteFolder;
    }

    /** catches null pointers from filepath not being correct */
    catch (NullPointerException e){
      System.out.println("File not found. Please try again.");
      getFilePath();
    }
    return null;
  }

  /** Looks at txt files from the local notes directory and will pass each one to the Note class to become a Note
  for each file in the directory, makes a new note, and then the note is added to the notebook with passToMap
  https://stackoverflow.com/questions/1844688/read-all-files-in-a-folder
  **/
  public static boolean passFiles(final File folder,Notebook notebook) {
    try{
      /** passes each file to passfiles to become a note */
      for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
          passFiles(fileEntry,notebook);
        }
        else {
          Note n = new Note(fileEntry);
          for (int i = 0; i < n.getIdentifierLists().size(); i++){
            notebook.passToMap(n,n.getIdentifierLists().get(i));
          }
        }
      }
      return true;
    }
    catch (NullPointerException e){
      System.out.println("File not found. Please try again.");
      getFilePath();
      return false;
    }
  }

  /** Makes reports object and interacts with user via text input to generate reports with the object
  **/
  public static void generateReports(Notebook notebook){
    Reports report = new Reports(notebook);
    Scanner scanner = new Scanner(System.in);
    /** prints report menu and waits for input */
    printMenu();
    int userInput = scanner.nextInt();

    if (userInput == 7){
      System.exit(0);
    }

    report.generateReport(userInput);
    /** asks user if they want more reports or to exit */
    continueScreen(notebook);

    userInput = scanner.nextInt();

    if (userInput == 1){
      generateReports(notebook);
    }
    else{
      System.exit(0);
    }
  }

  /** text menu for types of reports that can be generated **/
  public static void printMenu(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("Please choose the report to be generated, or exit by pressing 7: \n");
    System.out.println("1: all notes containing one or more mentions");
    System.out.println("2: all notes organized by mention");
    System.out.println("3: report of all keywords");
    System.out.println("4: notes organized by keywords");
    System.out.println("5: search for #, @, or !");
    System.out.println("6: notes organized in topological order");
    System.out.println("7: EXIT");
  }

  /** text menu for user decision to continue or exit **/
  public static void continueScreen(Notebook notebook){
    System.out.println("Would you like to generate another report?");
    System.out.println("1: YES \n2: NO");
  }

  /** prints start menu **/
  public static void printStartMenu(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("THERE ARE TWO OPTIONS TO GET STARTED:\n1: Enter the ABSOLUTE filepath of the notes' DIRECTORY (not a txt file) below\n2: Put your notes in the 'notes' directory included in this package, then run this program from the 'Notepad' directory. Only select this option if your notes are already in the notes directory.\n3: Exit");
  }
}
