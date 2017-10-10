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
    getCommand(notebook,notebookManager);
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
  public static void getCommand(Notebook notebook,NotebookManager notebookManager){
    //NotebookManager notebookManager = new NotebookManager();
    clearScreen();
    System.out.println("Enter command below, or for help type 'help'");
    //splits command up into each part and examines it one part at a time
    String[] commands = null;
    commands = scanner.nextLine().split(" ");

    switch (commands[0]) {
      case "help":
      printMenu(new File(".." + File.separator + "Notepad" + File.separator + "resources" + File.separator + "commands.txt"));
      commands = scanner.next().split(" ");
      break;
      case "add":
      notebookManager.editNote(commands[1]);
      break;
      case "filepath":
      getFilePath();
      passFiles(notebook);
      break;
      case "delete":
      notebookManager.editNote(commands[1]);
      break;
      case "search":
      //pass switch to report object and generate report based on that
      passFiles(notebook);
      Reports report = new Reports(notebook);
      //report.generatethisreport
      report.generateReport(commands[1]);
      getCommand(notebook,notebookManager);
      break;
      case "quit":
      break;
      default:
      System.out.println("Command not recognized. Please try again.");
      getCommand(notebook,notebookManager);
      break;
    }

  }
  public static void clearScreen(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
