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
  static Scanner scanner = new Scanner(System.in);

  /** Notebook object is created,
  filepath is taken from user (or not, depending on their input).
  files then passed to be parsed, and afterwards, reports gathered
  **/

  public static void main(String[] args) throws Exception{
    Notebook notebook = new Notebook();
    // something like NotebookManager notebookManager = new NotebookManager();
    getCommand(notebook);
    System.out.println("Terminating...");
    System.exit(0);
  }

  /**
  prints the start menu to see if the user wants to put in their own filepath,
  then guides the user through getting the folder correct
  returns the folder of notes to main so they can be passed to passFiles
  **/
  public static void getFilePath(){
    File noteFolder;
    /** different cases for user input */
    try{
      System.out.println("Please enter your ABSOLUTE filepath:");
      noteFolder = new File(scanner.next());
      if (!noteFolder.isDirectory()){
        System.out.println("This is not a directory. Try again.");
        getFilePath();
      }
      for (final File fileEntry : noteFolder.listFiles()) {
        fileEntry.renameTo(new File(".." + File.separator + "Notepad" + File.separator + "notes" + File.separator));
      }
    }
    /** catches null pointers from filepath not being correct */
    catch (NullPointerException e){
      System.out.println("File not found. Please try again.");
      getFilePath();
    }
  }

  /** Looks at txt files from the local notes directory and will pass each one to the Note class to become a Note
  for each file in the directory, makes a new note, and then the note is added to the notebook with passToMap
  https://stackoverflow.com/questions/1844688/read-all-files-in-a-folder
  **/
  public static boolean passFiles(Notebook notebook) {
    File folder = new File(".." + File.separator + "Notepad" + File.separator + "notes" + File.separator);
    try{
      /** passes each file to passfiles to become a note */
      for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
          passFiles(notebook);
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

  /** text menu for types of reports that can be generated **/
  public static void printMenu(File menu){
    clearScreen();
    try  {
      BufferedReader br = new BufferedReader(new FileReader(menu));
      String line = null;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
    }
    catch (IOException e){
      System.out.println("Error. Terminating program.");
      System.exit(0);
    }
  }

  /** gets users command and performs proper action **/
  public static void getCommand(Notebook notebook){
    //NotebookManager notebookManager = new NotebookManager();
    clearScreen();
    System.out.println("Enter command below, or for help type 'help'");
    String command = scanner.next();
    while (!command.equals("q")){
      switch (command) {
        case "help":
        printMenu(new File(".." + File.separator + "Notepad" + File.separator + "resources" + File.separator + "commands.txt"));
        command = scanner.next();
        break;
        case "add":
        //something like notebookManager.addNote(Notebook notebook);
        break;
        case "add -d":
        //notebookManager.addDictatedNote(Notebook notebook);
        break;
        case "add -i":
        //notebookManager.enterData(Notebook notebook);
        break;
        case "filepath":
        passFiles(notebook);
        break;
        case "delete":
        //notebookManager.deleteNote(Notebook notebook);
        break;
        case "delete -i":
        //notebookManager.deleteData(Notebook notebook);
        break;
        case "report":
        passFiles(notebook);
        Reports report = new Reports(notebook);
        //report.generatethisreport
        report.generateReport(scanner.nextInt());
        getCommand(notebook);
        break;
        default:
        System.out.println("Please try again.");
        getCommand(notebook);
        break;
      }
    }
  }
  public static void clearScreen(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
