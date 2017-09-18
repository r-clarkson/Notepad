import java.io.File;
import java.util.*;
import java.util.regex.*;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
/** Takes an individual note file, parses it and puts types of identifiers in their respective lists **/
public class Note{

  String name;
  LinkedList<LinkedList<String>> identifierLists;
  List<Pattern> patterns;
  /** constructor will set filename to identify file by and then pass the file to be parsed in a separate function**/
  public Note(File note){
    //make a list for each type
    identifierLists = new LinkedList<LinkedList<String>>();

    patterns = new ArrayList<Pattern>();
    patterns.add(Pattern.compile("#[-a-zA-Z0-9_]+"));
    patterns.add(Pattern.compile("@[-a-zA-Z0-9_]+"));
    patterns.add(Pattern.compile("![-a-zA-Z0-9_]+"));
    patterns.add(Pattern.compile("((http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+)\\.[a-z]{3})"));

    name = note.getName();
    for (int i = 0; i < patterns.size(); i++){
      identifierLists.add(parseNote(note,patterns.get(i)));
    }
  }
  /** function will turn files text line by line into strings, create patterns for each type of identifier and match with these strings
  then the matches will be stored in a list
  passes pattern, list for type of identifier, and the list of lines to another parsing function which goes through for each type of pattern
  https://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
  https://stackoverflow.com/questions/10432543/extract-hash-tag-from-string
  **/
  public LinkedList<String> parseNote(File note, Pattern pattern){
    LinkedList<String> identifierList = new LinkedList<String>();
    try{
      //turns each line from the file into a string, given the character encoding type and filepath
      List<String> lines = Files.readAllLines(note.toPath(), StandardCharsets.ISO_8859_1);

      for (int i = 0; i < lines.size(); i++){

        Matcher match = pattern.matcher(lines.get(i));

        while(match.find()){
          identifierList.add(match.group());
        }
      }
    }
    catch (IOException e){
      //prints stack trace if there's an exception
      e.printStackTrace();
    }
    return identifierList;
  }

  /** Getters and setters **/
  public String getName(){
    return name;
  }
  public List<LinkedList<String>> getIdentifierLists(){
    return identifierLists;
  }
  public void setIdentifierList(LinkedList<LinkedList<String>> identifierList){
    identifierList = this.identifierLists;
  }
}
