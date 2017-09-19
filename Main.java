import java.nio.file.*;
import java.util.stream.*;
import java.io.*;


/**  MainClass initiates process by passing on files in the "notes" folder to be made into note objects, which are then put into a notebook
Reports are then generated based on user input and the said notebook
**/
public class Main{

  /** Filepath is taken from parent folder
  Notebook object initiated, files passed to be parsed, and afterwards, reports gathered
  **/
  public static void main(String[] args){
    File noteFolder = new File(".." + File.separator + "Notepad" + File.separator + "notes" + File.separator);
    Notebook notebook = new Notebook();
    passFiles(noteFolder,notebook);
    generateReports(notebook);
  }

  /** Looks at txt files from the local notes directory and will pass each one to the Note class to become a Note
  for each file in the directory, makes a new note, and then the note is added to the notebook with passToMap
  https://stackoverflow.com/questions/1844688/read-all-files-in-a-folder
  **/
  public static void passFiles(final File folder,Notebook notebook) {
    for (final File fileEntry : folder.listFiles()) {
      if (fileEntry.isDirectory()) {
        passFiles(fileEntry,notebook);
      }
      else {
        Note n = new Note(fileEntry); //it passes the file on to Note for parsing of the text
        for (int i = 0; i < n.getIdentifierLists().size(); i++){
          notebook.passToMap(n,n.getIdentifierLists().get(i));
        }
      }
    }
  }

  /** Makes reports object so that the maps from the notebook can be searched
  **/
  public static void generateReports(Notebook notebook){
    Reports report = new Reports(notebook);
    //report.printSpecificMention("#hey");
  }
}
