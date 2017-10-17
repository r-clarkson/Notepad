package notepad.src.main.java.code;
import java.util.regex.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class NoteDeleter{
  Scanner scan;

  public NoteDeleter(){
    scan = null;
  }

  /**
  * Iterates through the note directory looking for the mention that is to be deleted
  * When a text file is found that contains the mention, calls method removeMentionFromFile
  **/

  public void deleteNoteMentions(String mentionID){
    String newMention = mentionID.replace("!", "^");
    File [] txtFiles = new File(".." + File.separator + "Notepad" + File.separator + "notes").listFiles();
    for (File cur : txtFiles){
      System.out.println(newMention + " searching" + " in file " + cur.getName());
      try{
        scan = new Scanner(cur);
        while (scan.hasNext()){
          if (scan.next().contains(newMention)){
            removeMentionFromFile(cur,newMention);
          }
        }
      } catch (FileNotFoundException e){
        System.out.println("Error openning BufferedReader or PrintWriter");
      }
    }
  }

  /**
  * This method takes in the file which contains the "newMention" that needs to be removed
  * A temporary text file is created and contents of the given file are copied, except for the newMention
  * This leaves the temp file the same as the original but without the unwanted text
  * The temp file is then renamed to the original file, esentially replacing it
  * https://stackoverflow.com/questions/5360209/how-to-delete-a-specific-string-in-a-text-file
  **/

  public void removeMentionFromFile(File file, String newMention){
    try{
      File temp = File.createTempFile("tmp", ".txt", file.getParentFile());
      BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1));
      PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp), StandardCharsets.ISO_8859_1));
      for (String line; (line = reader.readLine()) != null;) {
        if (line.contains(newMention)){
          line = line.replace(newMention, "");
        }
        System.out.println(line);
        writer.println(line);
      }
      writer.close();
      temp.renameTo(file);
    }
    catch (IOException e){
      System.out.println("Error.");
    }
  }

  /**
  * This function is used when the user wants to delete an entire note
  * It returns a given files !uniqueTitle in order to remove references to it in other notes
  * (you dont want references to a note that doesn't exist)
  * same logic from here : https://stackoverflow.com/questions/5360209/how-to-delete-a-specific-string-in-a-text-file
  **/
  
  public String deleteNote(File filename){
    String charset = "UTF-8";
    String retLine = null;
    BufferedReader reader = null;
    try{
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), charset));
      for (String line; (line = reader.readLine()) != null;) {
        if (line.matches("\\![-a-zA-Z0-9_]+")){
          retLine = line;
        }
      }
      filename.delete();
    }
    catch (IOException e){
      System.out.println("Error openning BufferedReader or PrintWriter");
    }
    return retLine;
  }
}
