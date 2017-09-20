package src;

import java.io.File;
import java.util.*;
import java.util.regex.*;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
/** Takes an individual note file, parses it and puts types of identifiers in their respective lists
  Keeps track of all note names (notes list) for topological sort (we must know notes that do not have any references and are thus not saved by other processes)
  Iterates through list of patterns, matches items within a line of the given note based on the patterns
  These matches are stored in the List of LLs, where the list is the identifier type (@,#,etc) and the LL contains all of the occurences of these in the notes
  In Main, these lists are passed to Notebook to be looked at as a whole by being combined with other notes' lists
**/
public class Note{

  String name;
  LinkedList<LinkedList<String>> identifierLists;
  List<Pattern> patterns;

  /** constructor initializes lists, adds patterns to their respective list, and parses the note for each type of pattern**/
  public Note(File note){
    identifierLists = new LinkedList<LinkedList<String>>();

    patterns = new ArrayList<Pattern>();
    patterns.add(Pattern.compile("#[-a-zA-Z0-9_]+"));
    patterns.add(Pattern.compile("@[-a-zA-Z0-9_]+"));
    patterns.add(Pattern.compile("![-a-zA-Z0-9_]+"));
    patterns.add(Pattern.compile("\\^[-a-zA-Z0-9_]+"));
    //patterns.add(Pattern.compile("((http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+)\\.[a-z]{3})"));

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
      List<String> lines = Files.readAllLines(note.toPath(), StandardCharsets.ISO_8859_1);

      for (int i = 0; i < lines.size(); i++){

        Matcher match = pattern.matcher(lines.get(i));

        while(match.find()){
          identifierList.add(match.group());
        }
      }
      if (pattern == Pattern.compile("\\^[-a-zA-Z0-9_]+")){

      }
    }
    catch (IOException e){
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
