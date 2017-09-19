import java.nio.file.*;
import java.util.*;
import java.io.*;
/** consolidates the given notebook's maps into a list of maps to be a little cleaner
has multiple functions for generating different types of reports
**/
public class Reports{
  Scanner scanner;
  LinkedList<HashMap<String,LinkedList<String>>> maps;
  Notebook notebook;
  /** adds the notebook's hashmaps to the new list of maps so they can be more easily iterated through (its a little cleaner)**/
  public Reports(Notebook notebook){
    Scanner scanner = new  Scanner(System.in);
    maps = new LinkedList<HashMap<String,LinkedList<String>>>();
    maps.add(notebook.getListType('#'));
    maps.add(notebook.getListType('@'));
    maps.add(notebook.getListType('!'));
    maps.add(notebook.getListType('^'));
    maps.add(notebook.getListType('f'));
    notebook = this.notebook;
  }
  /** based on the users input from main, generates that report
  **/
  public void generateReport(int input){
    clearScreen();
    switch(input){
      case 1:
        System.out.println("NOTES WITH ONE OR MORE MENTION(S) (@)");
        printNotesWithOneOrMoreMentions(maps.get(1),false);
        break;
      case 2:
        System.out.println("NOTES ORGANIZED BY MENTION (@)");
        printNotesWithOneOrMoreMentions(maps.get(1),true);
        break;
      case 3:
        System.out.println("ALL KEYWORDS (#)");
        printNotesWithOneOrMoreMentions(maps.get(0),false);
        break;
      case 4:
        System.out.println("NOTES ORGANIZED BY KEYWORD (#)");
        printNotesWithOneOrMoreMentions(maps.get(0),true);
        break;
      case 5:
        clearScreen();
        System.out.println("Please enter the keyword or mention you would like to find notes for, including the symbol: ");
        String userInput = scanner.next();
        printSpecificMention(userInput);
      case 6:
        break;
      default:
        System.out.println("Please enter a valid input.");
    }
  }
  /**
  iterates through each map to search for a specific mention and prints out the notes associated with that mention
  **/
  public void printSpecificMention(String mention){
    for (int i = 0; i < maps.size(); i++){
      if(maps.get(i).containsKey(mention)){
        System.out.println("Notes found for " + mention + " ");
        System.out.print(" " + maps.get(i).get(mention) + "\n");
      }
    }
  }
  /**
  takes the type of map and boolean for whether to print keys or not (just notes or notes with their mention/keyword)
  for each key in the map, calls iterateLists to go through the key's LL of note names
  **/
  public void printNotesWithOneOrMoreMentions(Map<String, LinkedList<String>> mapType, boolean showKey){
    for (String key : mapType.keySet()) {
      if(showKey){
        System.out.println("\nKEY: " + key);
      }
      iterateLists(mapType,key);
    }
  }
  /**
  iterates through given key's LL, while keeping track of which note names have already been printed (hence the extra LL) in order to avoid duplicates
  **/
  public void iterateLists(Map<String, LinkedList<String>> mapType, String key){
    List<String> noteNames = new LinkedList<String>();
    for (int i = 0; i < mapType.get(key).size(); i++){
        if (!noteNames.contains(mapType.get(key).get(i))){
          System.out.print(mapType.get(key).get(i) + "\n");
          noteNames.add(mapType.get(key).get(i));
        }
    }
  }
  /** simple function to clear screen, the less repetitive code the better
  https://stackoverflow.com/questions/10241217/how-to-clear-console-in-java **/
  public void clearScreen(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
