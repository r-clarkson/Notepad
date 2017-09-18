import java.util.*;

public class Reports{
  LinkedList<HashMap<String,LinkedList<String>>> maps;
  Notebook notebook;

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
  an example function for how we might print a report
  **/
  public void printAllMaps(){
    for (int i = 0; i < maps.size(); i++){
      printMap(maps.get(i));
    }
  }
  public void printTypeOfMap(char type){
    printMap(notebook.getListType(type));
  }

  public void printSpecificMention(String mention){
    for (int i = 0; i < maps.size(); i++){
      if(maps.get(i).containsKey(mention)){
        System.out.println("Notes found for " + mention + " ");
        System.out.print(" " + maps.get(i).get(mention) + "\n");
      }
    }
  }

  public void printMap(Map<String, LinkedList<String>> mapType){
    for (String key : mapType.keySet()) {
      System.out.println("\nKEY: " + key + " ");
      for (int i = 0; i < mapType.get(key).size(); i++){
        System.out.print( mapType.get(key).get(i) + "\n");
      }
    }
  }

}
