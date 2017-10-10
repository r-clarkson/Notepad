package notepad.src.main.java.code;

import java.io.*;
import java.nio.file.*;
import java.util.*;
/**
* consolidates the given notebook's maps into a list of maps to be a little cleaner has multiple
* functions for generating different types of reports
*/
public class Reports {
  Scanner scanner;
  Notebook notebook;
  LinkedList<String> notesList;
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
  public void generateReport(String sw) {
    /** TODO: have an option to just do them all or combinations instead of choosing one */
    if (sw.equals("-t")){
      TSort topologicalSort = new TSort();
      topologicalSort.tSort(notebook.getNotesList(), notebook.getMaps());
    }
    /** do these if the user did not choose topological sort */
    else if (!sw.equals("-t")){
      System.out.println("Enter the type of identifier ('#','@','^','!', or 'u' for url) you would like to organize by");
      String data = scanner.next();
      char identifier = data.charAt(0);
      clearScreen();
      switch (sw) {
        case "-a":
        System.out.println("N O T E S   W I T H   O N E   O R   M O R E   " + data);
        printNotesWithOneOrMoreMentions(notebook.getListType(identifier), false, true);
        break;
        /** this one will be the same as the one below...*/
        case "-l":
        System.out.println("A L L   " + data);
        printNotesWithOneOrMoreMentions(notebook.getListType(identifier), true, false);
        break;
        case "-o":
        System.out.println("N O T E S   O R G A N I Z E D   B Y   " + data);
        printNotesWithOneOrMoreMentions(notebook.getListType(identifier), true, true);
        break;
        case "-w":
        clearScreen();
        String userInput = null;
        System.out.println("Enter the word you would like to search for of type " + data);
        data = data.concat(scanner.next());
        printSpecificMention(data);
        break;
        case "":
        TSort topologicalSort = new TSort();
        topologicalSort.tSort(notebook.getNotesList(), notebook.getMaps());
        break;
        default:
        System.out.println("Please enter a valid input.");
      }
    }
    System.out.println("PRESS ANY KEY TO CONTINUE");
    while(!scanner.hasNext()){
      break;
    }
  }

  /**
  * Iterates through each map to search for a specific mention and prints out the notes associated
  * with that mention
  */
  public void printSpecificMention(String mention) {
    for (int i = 0; i < notebook.getMaps().size(); i++) {
      if (notebook.getMaps().get(i).containsKey(mention)) {
        System.out.println("Notes found for " + mention + " ");
        System.out.print(" " + notebook.getMaps().get(i).get(mention) + "\n");
      }
    }
  }

  /**
  * Takes the type of map and boolean for whether to print keys or not (just notes or notes with
  * their mention/keyword) for each key in the map, calls iterateLists to go through the key's LL
  * of note names
  */
  public void printNotesWithOneOrMoreMentions(HashMap<String, LinkedList<String>> mapType, boolean showKey, boolean showValue) {
    for (String key : mapType.keySet()) {
      if (showKey) {
        System.out.println("\nKEY: " + key);
      }
      if (showValue) {
        iterateLists(mapType, key);
      }
    }
  }
  /**
  * Iterates through given key's LL, while keeping track of which note names have already been
  * printed (hence the extra LL) in order to avoid duplicates
  */
  public void iterateLists(HashMap<String, LinkedList<String>> mapType, String key) {
    List<String> noteNames = new LinkedList<String>();

    for (int i = 0; i < mapType.get(key).size(); i++) {
      if (!noteNames.contains(mapType.get(key).get(i))) {
        System.out.println("\n*" + mapType.get(key).get(i));
        noteNames.add(mapType.get(key).get(i));
      }
    }
  }
  /**
  * Clears the console https://stackoverflow.com/questions/10241217/how-to-clear-console-in-java *
  */
  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
