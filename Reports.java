import java.nio.file.*;
import java.util.*;
import java.io.*;
/** consolidates the given notebook's maps into a list of maps to be a little cleaner
has multiple functions for generating different types of reports
**/
public class Reports{


  LinkedList<HashMap<String,LinkedList<String>>> maps;
  Notebook notebook;

  /** adds the notebook's hashmaps to the new list of maps so they can be more easily iterated through**/
  public Reports(Notebook notebook){

    maps = new LinkedList<HashMap<String,LinkedList<String>>>();
    maps.add(notebook.getListType('#'));
    maps.add(notebook.getListType('@'));
    maps.add(notebook.getListType('!'));
    maps.add(notebook.getListType('^'));
    maps.add(notebook.getListType('f'));
    notebook = this.notebook;

/**
    System.out.println("\nNOTES WITH ONE OR MORE KEYWORD(S) (@,#,!,^)");
    for (int i = 0; i < maps.size(); i ++){
      printNotesWithOneOrMoreMentions(maps.get(i));
    }**/
  }

  public void generateReport(int input){

    System.out.print("\033[H\033[2J");
    System.out.flush();

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
        System.out.println("ALL KEYWORDS (!,#,@)");
        break;
      case 4:
        System.out.println("NOTES ORGANIZED BY KEYWORD (!,#,@)");
        printNotesWithOneOrMoreMentions(maps.get(0),true);
        printNotesWithOneOrMoreMentions(maps.get(1),true);
        printNotesWithOneOrMoreMentions(maps.get(2),true);
        break;
      case 5:
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Please enter the keyword or mention you would like to find notes for, including the symbol: ");
        Scanner scanner = new  Scanner(System.in);
        String userInput = scanner.next();
        printSpecificMention(userInput);
      case 6:
        break;
      default:
        System.out.println("Please enter a valid input.");
    }
  }
  /**
  iterates through each map in the list and prints it using printmap function
  **/
  public void printAllMaps(){
    List<String> list = new LinkedList<String>();
    for (int i = 0; i < maps.size(); i++){
      //printNotesWithOneOrMoreMentions(maps.get(i));
      for (String key : maps.get(i).keySet()) {
        for (int j = 0; j < maps.get(i).get(key).size(); j++){
          if (!list.contains(maps.get(i).get(key).get(j))){
            list.add(maps.get(i).get(key).get(j));
            System.out.print( maps.get(i).get(key).get(j) + "\n");
          }
        }
      }
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
  prints a whole map by first getting the key value and printing its associated LL of note names
  does this for each key in a map via foor loops
  **/
  public void printNotesWithOneOrMoreMentions(Map<String, LinkedList<String>> mapType, boolean showKey){
    for (String key : mapType.keySet()) {
      if(showKey){
        System.out.println("\nKEY: " + key);
      }
      iterateLists(mapType,key);
    }
  }

  public void iterateLists(Map<String, LinkedList<String>> mapType, String key){
    List<String> noteNames = new LinkedList<String>();
    for (int i = 0; i < mapType.get(key).size(); i++){
        if (!noteNames.contains(mapType.get(key).get(i))){
          System.out.print(mapType.get(key).get(i) + "\n");
          noteNames.add(mapType.get(key).get(i));
        }
    }
  }
}
