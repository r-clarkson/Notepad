import java.io.File;
import java.util.*;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class Note{

  String name;

  /** constructor will set filename to identify file by and then pass the file to be parsed in a separate function**/
  public Note(File note){

    name = note.getName();
    parseNote(note);

  }
  /** function will turn files text line by line into strings, create patterns for each type of identifier and match with these strings
  then the matches will be stored in a list**/
  public void parseNote(File note){

    try{
      //turns each line from the file into a string, given the character encoding type and filepath
      List<String> lines = Files.readAllLines(note.toPath(), StandardCharsets.ISO_8859_1);
      //for now prints each line but eventually we put in the pattern stuff in here
      for (int i = 0; i < lines.size(); i ++){

        System.out.println(lines.get(i));

      }

    }
    catch (IOException e){
      //prints stack trace if there's an exception
      e.printStackTrace();

    }


  }

  public String getName(){
    return name;
  }
}
