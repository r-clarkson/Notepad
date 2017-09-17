import java.io.File;
import java.util.*;
import java.util.regex.*;
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
  then the matches will be stored in a list
  https://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
  https://stackoverflow.com/questions/10432543/extract-hash-tag-from-string
  **/
  public void parseNote(File note){
    //declare patterns here for each type of identifier
    Pattern topicMention = Pattern.compile("#[-a-zA-Z0-9_]+");
    //make a list for each type
    List<String> topicMentionList = new ArrayList<String>();

    try{
      //turns each line from the file into a string, given the character encoding type and filepath
      List<String> lines = Files.readAllLines(note.toPath(), StandardCharsets.ISO_8859_1);
      //declare a matcher to use to search for the patterns
      Matcher m;

      for (int i = 0; i < lines.size(); i ++){
        //look through each line and add any matches to the proper arraylist
        m = topicMention.matcher(lines.get(i));

        while(m.find()){
          topicMentionList.add(m.group());
        }

      }
      //test function for now, prints out the results so we can check them...
      for (int i =0; i < topicMentionList.size(); i++){

        System.out.println(topicMentionList.get(i));

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
