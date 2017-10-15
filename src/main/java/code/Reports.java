package notepad.src.main.java.code;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.nio.charset.StandardCharsets;

/**
* consolidates the given notebook's maps into a list of maps to be a little cleaner has multiple
* functions for generating different types of reports
*/
public class Reports {
  Scanner scanner;
  Notebook notebook;
  LinkedList<String> notesList;
  static Pattern END_OF_SENTENCE = Pattern.compile("\\.\\s+");
  /**
  * adds the notebook's hashmaps to the new list of maps so they can be more easily iterated
  * through (its a little cleaner)*
  */
  public Reports(Notebook n) {
    scanner = new Scanner(System.in);
    notebook = n;
  }
  /** The big, the bad, and ugly
  * switch statement to generate different types of reports based on the option the user typed in (sw variable)
  */
  public boolean generateReport(String sw) {
    /** TODO: have an option to just do them all or combinations instead of choosing one */
    if (sw.equals("-t")){
      TSort topologicalSort = new TSort();
      topologicalSort.tSort(notebook.getNotesList(), notebook.getMaps());
      return true;
    }
    /** do these if the user did not choose topological sort */
    else if (sw.equals("-a") || sw.equals("-w") || sw.equals("-l") || sw.equals("-o") || sw.equals("-s")){
      String data = getData();
      clearScreen();
      return printInformation(data,sw);
    }
    else{
      System.out.println("Command not recognized.");
    }
    return false;
  }

  /**
  * Iterates through each map to search for a specific mention and prints out the notes associated
  * with that mention
  */
  public boolean printSpecificMention(String mention) {
    for (int i = 0; i < notebook.getMaps().size(); i++) {
      if (notebook.getMaps().get(i).containsKey(mention)) {
        System.out.println("Notes found for " + mention + " ");
        System.out.print(" " + notebook.getMaps().get(i).get(mention) + "\n");
        return true;
      }
    }
    return false;
  }

  /**
  * Takes the type of map and boolean for whether to print keys or not (just notes or notes with
  * their mention/keyword) for each key in the map, calls iterateLists to go through the key's LL
  * of note names
  * Returns false if the map is null (useful for testing)
  */
  public void printNotesWithOneOrMoreMentions(HashMap<String, LinkedList<String>> mapType, boolean showKey, boolean showValue) {
    for (String key : mapType.keySet()) {
      if (showKey) {
        System.out.println("* " + key);
      }
      if (showValue) {
        iterateLists(mapType, key);
      }
    }
  }

  public void getFileForSentences(String mention){
    ArrayList<String> fileName = new ArrayList<String>();
    String answer;
    for (int i = 0; i < notebook.getMaps().size(); i++) {
      if (notebook.getMaps().get(i).containsKey(mention)) {
        for (int j = 0; j < notebook.getMaps().get(i).get(mention).size(); j++){
          fileName.add(notebook.getMaps().get(i).get(mention).get(j));
        }
      }
    }
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
        e.printStackTrace();

      }
    }
  }
  /**
  * Finds sentences containing a given mention within the note files from printSpecificMention
  * Reference: https://codereview.stackexchange.com/questions/90474/extracting-a-sentence-containing-a-specific-word-from-a-longer-text
  */
  public static String getSentence(String text, String mention) {
    final String lcword = mention.toLowerCase();
    return END_OF_SENTENCE.splitAsStream(text).filter(s -> s.toLowerCase().contains(lcword)).findAny().orElse("");
  }
  /**
  * Iterates through given key's LL, while keeping track of which note names have already been
  * printed (hence the extra LL) in order to avoid duplicates
  */
  public boolean iterateLists(HashMap<String, LinkedList<String>> mapType, String key) {
    List<String> noteNames = new LinkedList<String>();
    for (int i = 0; i < mapType.get(key).size(); i++) {
      if (!noteNames.contains(mapType.get(key).get(i))) {
        System.out.println("\n*" + mapType.get(key).get(i));
        noteNames.add(mapType.get(key).get(i));
      }
    }
    return (noteNames.size()!=0) ? true : false;
  }
  /**
  * Clears the console https://stackoverflow.com/questions/10241217/how-to-clear-console-in-java *
  */
  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  /**  retrieves user's input on whether to conitnue or not */
  public boolean getContinue(){
    System.out.println("1: Return\n2: Quit");
    if (scanner.hasNext()){
      switch (scanner.nextInt()){
        case 1:
        clearScreen();
        return true;
        case 2:
        clearScreen();
        return false;
        default:
        System.out.println("Command not recognized. Please try again.");
        getContinue();
      }
    }
    return false;
  }
  /** returns the type of identifier the user wants to search by */
  public String getData(){
    System.out.println("Enter the type of identifier ('#','@','^','!', or 'url') you would like to organize by, or type 'all' for all");
    String data = scanner.next();
    return data;
  }
  /** prints the report */
  public boolean printInformation(String data, String sw){
    String[] types = {"keywords (#)","mentions (@)","references (^)","titles (!)","urls"};
    if (data.equals("all")){
      for (String type : types){
        System.out.println("\n");
        printInformation(type,sw);
      }
    }
    else if (!data.equals("all")){
      switch (sw) {
        case "-a":
        System.out.println("N O T E S   W I T H   O N E   O R   M O R E   " + data);
        printNotesWithOneOrMoreMentions(notebook.getListType(data), false, true);
        break;
        /** this one will be the same as the one below...*/
        case "-l":
        System.out.println("A L L   " + data);
        printNotesWithOneOrMoreMentions(notebook.getListType(data), true, false);
        break;
        case "-o":
        System.out.println("N O T E S   O R G A N I Z E D   B Y   " + data);
        printNotesWithOneOrMoreMentions(notebook.getListType(data), true, true);
        break;
        case "-w":
        clearScreen();
        String userInput = null;
        System.out.println("Enter the word you would like to search for of type " + data);
        data = data.concat(scanner.next());
        printSpecificMention(data);
        break;
        case "-s":
        System.out.println("Enter the word you would like to search for of type " + data);
        data = data.concat(scanner.next());
        getFileForSentences(data);
        break;
        default:
        System.out.println("Please enter a valid second argument.");
        return false;
      }
    }
    return true;
  }
}
