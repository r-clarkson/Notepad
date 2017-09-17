import java.io.File;
import java.util.*;
import java.util.regex.*;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class Note{

  String name;
  public List<String> topicMentionList;
  List<String> individualMentionList;
  List<String> uniqueMentionList;
  List<String> referenceMentionList;
  List<String> urlList;

  /** constructor will set filename to identify file by and then pass the file to be parsed in a separate function**/
  public Note(File note){

    //make a list for each type
    topicMentionList = new ArrayList<String>();
    individualMentionList = new ArrayList<String>();
    uniqueMentionList = new ArrayList<String>();
    referenceMentionList = new ArrayList<String>();
    urlList = new ArrayList<String>();

    name = note.getName();
    parseNote(note);

  }
  /** function will turn files text line by line into strings, create patterns for each type of identifier and match with these strings
  then the matches will be stored in a list
  passes pattern, list for type of identifier, and the list of lines to another parsing function which goes through for each type of pattern
  https://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
  https://stackoverflow.com/questions/10432543/extract-hash-tag-from-string
  **/
  public void parseNote(File note){
    //declare patterns here for each type of identifier
    Pattern topicMention = Pattern.compile("#[-a-zA-Z0-9_]+");
    Pattern individualMention = Pattern.compile("@[-a-zA-Z0-9_]+");
    Pattern uniqueMention = Pattern.compile("![-a-zA-Z0-9_]+");
    Pattern referenceMention = Pattern.compile("\\^[-a-zA-Z0-9_]+");
    //Pattern url = Pattern.compile("((http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-z]{3})");

    try{
      //turns each line from the file into a string, given the character encoding type and filepath
      List<String> lines = Files.readAllLines(note.toPath(), StandardCharsets.ISO_8859_1);

      setTopicMention(identifierMatcher(lines,topicMentionList,topicMention));
      setIndividualMention(identifierMatcher(lines,individualMentionList,individualMention));
      setUniqueMention(identifierMatcher(lines,uniqueMentionList,uniqueMention));
      setReferenceMention(identifierMatcher(lines,referenceMentionList,referenceMention));
      //identifierMatcher(lines,urlList,url);

    }
    catch (IOException e){
      //prints stack trace if there's an exception
      e.printStackTrace();

    }

  }
  /**
  Given the list of lines, identifier list, and pattern, matches for the size of the note. Then adds matches to the list of the identifier.
  **/
  public List<String> identifierMatcher(List<String> lines, List<String> identifierList, Pattern p){


    for (int i = 0; i < lines.size(); i++){

      Matcher match = p.matcher(lines.get(i));

      while(match.find()){

        identifierList.add(match.group());

      }
    }

    return identifierList;
  }

  public String getName(){

    return name;

  }
  public List<String> getTopicMention(){
    return topicMentionList;
  }
  public List<String> getIndividualMention(){
    return individualMentionList;
  }
  public List<String> getUniqueMention(){
    return uniqueMentionList;
  }
  public List<String> getReferenceMention(){
    return referenceMentionList;
  }
  public List<String> getURL(){
    return urlList;
  }

  public void setTopicMention(List<String> topicMentionList){

    topicMentionList = this.topicMentionList;

  }
  public void setIndividualMention(List<String> individualMentionList){

    individualMentionList = this.individualMentionList;

  }
  public void setUniqueMention(List<String> uniqueMentionList){

    uniqueMentionList = this.uniqueMentionList;

  }
  public void setReferenceMention(List<String> referenceMentionList){

    referenceMentionList = this.referenceMentionList;

  }
  public void setURL(List<String> urlMentionList){

    urlList = this.urlList;

  }
}
