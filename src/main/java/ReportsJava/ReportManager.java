package src.main.java;

import java.io.*;
import java.util.*;

/**
* This class is essentially a big, ugly switch statement which figures out which report to generate via
*  the generalreporter, specificreporter, and tsort classes
**/

public class ReportManager {
  Scanner scanner;
  Notebook notebook;
  LinkedList<String> notesList;
  List<String> types;

  public ReportManager(Notebook notebook) {
    scanner = new Scanner(System.in);
    this.notebook = notebook;
    types = new ArrayList<String>();
    types.add("#");
    types.add("@");
    types.add("^");
    types.add("!");
    types.add("");
  }

  /**
  * Since doing a topological sort does not require further user input,
  *  we address its instance first.
  * If the user does not do tsort, printInformation will be called to do further reports
  **/

  public boolean generateReport(String sw) {
    if (sw.equals("-t")){
      TSort topologicalSort = new TSort(notebook);
      topologicalSort.tSort();
      return true;
    }
    /** these require further user input */
    else if (sw.equals("-w") || sw.equals("-s")){
      SpecificReporter sr = null;
      boolean showSentence = sw.equals("-s") ? true : false;
      System.out.println("Enter the identifier you would like to search for, excluding the symbol" );
      String data = scanner.next();
      for (String type:types){
        type = type.concat(data);
        sr = new SpecificReporter(type,showSentence,notebook);
      }
      return true;
    }
    else{
      return printInformation(getData(),sw);
    }
  }

  /**
  * Uses a big switch statement to determine which report to generate exactly, and then prints it
  * by creating the right report object
  **/

  public boolean printInformation(String data, String sw){
    GeneralReporter gr = null;

    if (data == null){
      return false;
    }
    else if (data.equals("all")){
      for (String type : types){
        System.out.println("\n");
        printInformation(type,sw);
      }
    }
    else if (!data.equals("all")){
      switch (sw) {

        case "-a":
        System.out.println("N O T E S   W I T H   O N E   O R   M O R E   " + data);
        gr = new GeneralReporter(notebook.getListType(data), false, true);
        break;

        case "-l":
        System.out.println("A L L   " + data);
        gr = new GeneralReporter(notebook.getListType(data), true, false);
        break;

        case "-o":
        System.out.println("N O T E S   O R G A N I Z E D   B Y   " + data);
        gr = new GeneralReporter(notebook.getListType(data), true, true);
        break;

        default:
        System.out.println("Please enter a valid second argument.");
        return false;
      }
    }
    if (!gr.getFound()){
      System.out.println("No matches found for " + data);
    }
    return true;
  }

  /** Returns the type of identifier the user wants to search by */
  public String getData(){
    System.out.println("Enter the type of identifier ('#','@','^','!', or 'url') you would like to organize by, or type 'all' for all");
    String data;
    if (scanner.hasNext()){
      data = scanner.next();
    }
    else{
      data = null;
    }
    return data;
  }

  /**
  * Clears the console https://stackoverflow.com/questions/10241217/how-to-clear-console-in-java *
  */
  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
