package notepad.src.main.java.code;
import java.nio.file.*;
import java.util.*;
import java.io.*;

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
    NotebookManager notebookManager = new NotebookManager();
    clearScreen();
    getCommand(notebook ,notebookManager);
  }

  /**
  * If the user wants to put in their own filepath, this function will retrieve it
  * Asks user for filepath, and then puts those files into the notes directory
  **/
  public static boolean getFilePath(){
    File noteFolder;
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
    /** Catches null pointers from filepath not being correct */
    catch (NullPointerException e){
      System.out.println("File not found.");
      return false;
    }
    catch (NoSuchElementException f){
      System.out.println("File not found.");
      return false;
    }
    return true;
  }

  /** Looks at txt files from the local notes directory and will pass each one to the Note class to become a Note
  * for each file in the directory, makes a new note, and then the note is added to the notebook with passToMap
  * https://stackoverflow.com/questions/1844688/read-all-files-in-a-folder
  * TODO: function was made boolean for testing but is that still necessary?
  **/
  public static boolean passFiles(Notebook notebook) {
    File folder = new File(".." + File.separator + "Notepad" + File.separator + "notes" + File.separator);
    try{
      /** passes each file to passfiles recursively to become a note */
      for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
          passFiles(notebook);
        }
        else {
          /** turns text file into note object and then passes object's parsed identifiers */
          Note n = new Note(fileEntry);
          for (int i = 0; i < n.getIdentifierLists().size(); i++){
            notebook.passToMap(n,n.getIdentifierLists().get(i));
          }
        }
      }
    }
    catch (NullPointerException e){
      System.out.println("There has been an error in the notes directory. Please restart the program.");
      return false;
    }
    return true;
  }

  /** Reads text file containing menu or commands and prints it to the screen https://stackoverflow.com/questions/15695984/java-print-contents-of-text-file-to-screen **/
  public static boolean printMenu(File menu){
    clearScreen();
    try  {
      BufferedReader br = new BufferedReader(new FileReader(menu));
      String line = null;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
      return true;
    }
    catch (IOException e){
      System.out.println("Error. Terminating program.");
      System.exit(0);
    }
    return false;
  }

  /**
  * This method retrieves the user's desired action and performs it
  * in most cases, the method recursively calls itself so that the user can continue to make/edit/report notes
  **/
  public static void getCommand(Notebook notebook,NotebookManager notebookManager){
    //clearScreen();
    System.out.println("Enter command below, or for help type 'help'");

    /* splits command into parts so that each can be handed off to the correct class/method */
    String[] commands = null;
    commands = scanner.nextLine().split(" ");
    if (!(commands.length>=1)){
      System.out.println("Please enter at least two arguments.");
      getCommand(notebook, notebookManager);
    }
    boolean cont = true;
    clearScreen();
    /* performs action based on first command */
    switch (commands[0]) {
      case "help":
      printMenu(new File(".." + File.separator + "Notepad" + File.separator + "resources" + File.separator + "commands.txt"));
      break;
      case "add":
      notebookManager.editNote(commands[1]);
      break;
      case "filepath":
      getFilePath();
      break;
      case "delete":
      notebookManager.editNote("-x");
      break;
      case "search":
      passFiles(notebook);
      Reports report = new Reports(notebook);
      cont = report.generateReport(commands[1]);
      break;
      case "quit":
      cont = false;
      break;
      default:
      System.out.println("Command not recognized. Please try again.");
      break;
    }
    if (!cont){
      terminateProgram();
    }
    else if (cont){
      getCommand(notebook,notebookManager);
    }
  }

  /** Clears the console https://stackoverflow.com/questions/10241217/how-to-clear-console-in-java */
  public static void clearScreen(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  /** Closes the program */
  public static void terminateProgram(){
    System.out.println("Terminating...");
    System.exit(0);
  }
}
