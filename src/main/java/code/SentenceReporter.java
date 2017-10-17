package notepad.src.main.java.code;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.nio.charset.StandardCharsets;

public class SentenceReporter{
  String mention;
  Notebook notebook;

  public SentenceReporter(String mention, Notebook notebook){
    mention = this.mention;
    notebook = this.notebook;
    getFileForSentences();
  }

  public boolean getFileForSentences(){
    ArrayList<String> fileName = new ArrayList<String>();

    for (int i = 0; i < notebook.getMaps().size(); i++) {
      if (notebook.getMaps().get(i).containsKey(mention)) {
        for (int j = 0; j < notebook.getMaps().get(i).get(mention).size(); j++){
          fileName.add(notebook.getMaps().get(i).get(mention).get(j));
        }
      }
    }
    getSentenceLines(fileName);
    return true;
  }

  public boolean getSentenceLines(ArrayList<String> fileName){
    String answer;
    for (int k = 0; k < fileName.size(); k++){
      try {
        List<String> lines = Files.readAllLines(Paths.get(".." + File.separator + "Notepad" + File.separator + "notes" + File.separator + fileName.get(k)), StandardCharsets.ISO_8859_1);
        for (int l = 0; l <lines.size(); l++){
          answer = getSentence(lines.get(l),mention);
          if (!answer.equals("")){
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

  /**
  * Finds sentences containing a given mention within the note files from printSpecificMention
  * Reference: https://codereview.stackexchange.com/questions/90474/extracting-a-sentence-containing-a-specific-word-from-a-longer-text
  */

  public static String getSentence(String text, String mention) {
    Pattern END_OF_SENTENCE = Pattern.compile("\\.\\s+");
    final String lcword = mention.toLowerCase();

    return END_OF_SENTENCE.splitAsStream(text).filter(s -> s.toLowerCase().contains(lcword)).findAny().orElse("");
  }
}
