package src.main.java;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.nio.charset.StandardCharsets;

/**
* This class includes functions which go through a notebook's HashMaps searching for a specific identifier
* The object is passed the mention to be searched for, the notebook, and a boolean
* The boolean determines whether only notenames where the identifier is found are returned, or sentences in which they are found as well
**/

public class SpecificReporter{
  String mention;
  boolean showSentence;
  Notebook notebook;
  boolean found;

  public SpecificReporter(String mention, boolean showSentence, Notebook notebook){
    this.notebook = notebook;
    this.mention = mention;
    this.showSentence = showSentence;
    found = false;
    if (showSentence){
      getFileForSentences();
    }
    else{
      printSpecificMentionNoteNames();
    }

    if (!found){
      System.out.println("\nNo matches found for " + mention);
    }
  }
  /**
  * Given the mention (which was passed into the constructor),
  * this method searches for files which contain it, and adds those filenames to an arraylist.
  * Then the arraylist containing the filenames of files which contain the mention is passed to getSentenceLines
  **/

  public boolean getFileForSentences(){
    ArrayList<String> fileName = new ArrayList<String>();
    if (notebook != null && notebook.getMaps() != null){
      for (int i = 0; i < notebook.getMaps().size(); i++) {
        if (notebook.getMaps().get(i).containsKey(mention)) {
          for (int j = 0; j < notebook.getMaps().get(i).get(mention).size(); j++){
            fileName.add(notebook.getMaps().get(i).get(mention).get(j));
            found = true;
          }
        }
      }
      getSentenceLines(fileName);
      return true;
    }
    return false;
  }

  /**
  * Given an arraylist of filenames which contain the mention the user is looking for,
  * this method searches with a scanner through each line of the file looking for the mention
  * if a line contains the mention, then getSentence is called (which returns as a string)
  * this returned string is then the answer and is printed
  **/

  public boolean getSentenceLines(ArrayList<String> fileName){
    String answer;
    if (fileName != null){
      for (int k = 0; k < fileName.size(); k++){
        try {
          List<String> lines = Files.readAllLines(Paths.get("." + File.separator + "notes" + File.separator + fileName.get(k)), StandardCharsets.ISO_8859_1);
          for (int l = 0; l <lines.size(); l++){
            answer = getSentence(lines.get(l),mention);
            if (!answer.equals("")){
              found = true;
              System.out.println("\nSentence:" + answer);
              System.out.println("  * found in " + fileName.get(k));
            }
          }
        }
        catch (IOException e ){
          System.out.println("IO Error");
          return false;
        }
      }
      return true;
    }
    return false;
  }

  /**
  * Finds sentences containing a given mention within the note files from printSpecificMention
  * Reference: https://codereview.stackexchange.com/questions/90474/extracting-a-sentence-containing-a-specific-word-from-a-longer-text
  * returns string
  */

  public static String getSentence(String text, String mention) {
    Pattern END_OF_SENTENCE = Pattern.compile("\\.\\s+");
    final String lcword = mention.toLowerCase();

    return END_OF_SENTENCE.splitAsStream(text).filter(s -> s.toLowerCase().contains(lcword)).findAny().orElse("");
  }

  /**
  * Iterates through each map to search for a specific mention and prints out the notes associated
  * with that mention
  */
  public boolean printSpecificMentionNoteNames() {
    if (notebook != null && notebook.getMaps() != null){
      for (int i = 0; i < notebook.getMaps().size(); i++) {
        if (notebook.getMaps().get(i).containsKey(mention)) {
          found = true;
          System.out.println("\nNotes found for " + mention + " ");
          System.out.print(" " + notebook.getMaps().get(i).get(mention) + "\n");
        }
      }
      return true;
    }
    return false;
  }

  /** returns true if search was fruitful */

  public boolean getFound(){
    return found;
  }
}
