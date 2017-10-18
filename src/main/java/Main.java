package classes;

import java.nio.file.*;
import java.util.*;
import java.io.*;

/**  MainClass initiates process by passing on files in the "notes" folder to be made into note objects, which are then put into a notebook
 * Reports are then generated based on user input and the said notebook
 * This is by far our largest class, as it contains the functions we need to get the notebook started
 * as well as functions to print things to the screen and get user input
**/
public class Main{
  static Scanner scanner = new Scanner(System.in);
  static File title = new File(".." + File.separator + "Notepad" + File.separator + "resources" + File.separator + "title.txt");
  static File commandsMenu = new File(".." + File.separator + "Notepad" + File.separator + "resources" + File.separator + "commands.txt");
  static File notesDirectory = new File(".." + File.separator + "Notepad" + File.separator + "notes" + File.separator);

  /** Notebook object is created,
  filepath is taken from user (or not, depending on their input).
  files then passed to be parsed, and afterwards, reports gathered
  **/

  public static void main(String[] args) throws Exception{
    clearScreen();
    printMenu(title);
    Notebook notebook = new Notebook();
    NotebookManager notebookManager = new NotebookManager();
    getCommand(notebook,notebookManager);
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
        System.out.println("This is not a directory.");
        return false;
      }
      for (final File fileEntry : noteFolder.listFiles()) {
        fileEntry.renameTo(notesDirectory);
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
  **/

  public static boolean passFiles(Notebook notebook) {
    try{
      /** passes each file to passfiles recursively to become a note */
      for (final File fileEntry : notesDirectory.listFiles()) {
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
      System.out.println("\n");
      return true;
    }
    catch (IOException e){
      System.out.println("Error. Terminating program.");
      System.exit(0);
    }
    return false;
  }

  /**
  * This function takes in user input and passes it to the switch statement which performs actions
  * A boolean is returned and if it is false, the program will terminate
  **/

  public static boolean getCommand(Notebook notebook,NotebookManager notebookManager){
    String[] commands = null;
    System.out.println("Enter command below, or for help type 'help'");
    /* splits command into parts so that each can be handed off to the correct class/method */
    if (!scanner.hasNext()){
      return false;
    }
    else {
      commands = scanner.nextLine().split(" ");
    }

    if (!(commands.length < 1) || commands.length >= 3){
      System.out.println("Please enter two arguments.");
      return getCommand(notebook, notebookManager);
    }
    /* performs action based on first command */
    else if (commands.length == 2 || commands.length == 1){
      clearScreen();
      commandSwitch(commands,notebook,notebookManager);
      return true;
    }
    return false;
  }

  /**
  * This method retrieves the user's desired action and performs it
  * in most cases, the method recursively calls itself so that the user can continue to make/edit/report notes
  **/

  public static boolean commandSwitch(String commands[],Notebook notebook,NotebookManager notebookManager){
    switch (commands[0]) {

      case "help":
      printMenu(commandsMenu);
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
      ReportManager report = new ReportManager(notebook);
      report.generateReport(commands[1]);
      break;

      case "quit":
      terminateProgram();

      default:
      System.out.println("Command not recognized. Please try again.");
      return false;

    }
    getCommand(notebook,notebookManager);
    return true;
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
