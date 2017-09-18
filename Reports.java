import java.util.*;
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
  }

  /**
  iterates through each map in the list and prints it using printmap function
  **/
  public void printAllMaps(){
    for (int i = 0; i < maps.size(); i++){
      printMap(maps.get(i));
    }
  }
  /**
  prints a specific type of map simply by using the getter function in notebook class
  **/
  public void printTypeOfMap(char type){
    printMap(notebook.getListType(type));
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
  public void printMap(Map<String, LinkedList<String>> mapType){
    for (String key : mapType.keySet()) {
      System.out.println("\nKEY: " + key + " ");
      for (int i = 0; i < mapType.get(key).size(); i++){
        System.out.print( mapType.get(key).get(i) + "\n");
      }
    }
  }
}
