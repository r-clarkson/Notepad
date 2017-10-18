package src.main.java;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class GeneralReporter{
  HashMap<String, LinkedList<String>> mapType;
  boolean showKey;
  boolean showValue;
  boolean found;

  public GeneralReporter(HashMap<String, LinkedList<String>> mapType, boolean showKey, boolean showValue){
    this.mapType = mapType;
    this.showKey = showKey;
    this.showValue = showValue;
    found = false;

    printNotesWithOneOrMoreMentions();
  }

  /**
  * Takes the type of map and boolean for whether to print keys or not (just notes or notes with
  * their mention/keyword) for each key in the map, calls iterateLists to go through the key's LL
  * of note names
  * Returns false if the map is null (useful for testing)
  */
  public boolean printNotesWithOneOrMoreMentions() {
    if (mapType!=null){
      for (String key : mapType.keySet()) {
        if (showKey) {
          System.out.println("* " + key);
          found = true;
        }
        if (showValue) {
          iterateLists(key);
        }
      }
      return true;
    }
    return false;
  }

  /**
  * Iterates through given key's LL, while keeping track of which note names have already been
  * printed (hence the extra LL) in order to avoid duplicates
  */
  public boolean iterateLists(String key) {
    List<String> noteNames = new LinkedList<String>();
    for (int i = 0; i < mapType.get(key).size(); i++) {
      if (!noteNames.contains(mapType.get(key).get(i))) {
        System.out.println("\n*" + mapType.get(key).get(i));
        noteNames.add(mapType.get(key).get(i));
        found = true;
      }
    }
    return (noteNames.size()!=0) ? true : false;
  }

  /** returns true if something was found to report */
  public boolean getFound(){
    return found;
  }
}
