import java.nio.file.*;
import java.util.stream.*;
import java.io.*;


/**  MainClass initiates process by passing on files in the "notes" folder to be made into note objects, which are then put into a notebook
Will then present options to user on how they would like info sorted, and then will print out that information
**/
public class Main{

  public static void main(String[] args){

    File noteFolder = new File(".." + File.separator + "Notepad" + File.separator + "notes" + File.separator);
    passFiles(noteFolder);

  }


  /** Looks at txt files from the local notes directory and will pass each one to the Note class to become a Note
  https://stackoverflow.com/questions/1844688/read-all-files-in-a-folder
  **/
  public static void passFiles(final File folder) {
    Notebook notebook = new Notebook();

    for (final File fileEntry : folder.listFiles()) {
      if (fileEntry.isDirectory()) {
        passFiles(fileEntry);
      } else {
        Note n = new Note(fileEntry); //it passes the file on to Note for parsing of the text
        //System.out.println(n.getName()); //for now just prints the file name to show its actually creating a file
        //just a little test function here
        //for (int i = 0; i < n.getURL().size(); i++){
          //System.out.println(n.getURL().get(i));
        //}

        notebook.initializeMaps(n);

      }
    }
    notebook.printTopicMentionNotes("#hey");
  }
}
