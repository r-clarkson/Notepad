package code;

import java.util.*;
import java.io.*;

public class TSort{
  int sortIndex;

  public TSort(){
    sortIndex = 0;
  }

  /** topological sort of note files
  finds notes without any incoming vertices, prints them, and then deletes those notes' outgoing vertices
  the note that was printed is then deleted from the list of all notes
  then repeats until the notes list has reached 0 notes
  sortIndex is global because it is used in different ways in each of the sort functions
  **/
  public void tSort(LinkedList<String> notesList,LinkedList<HashMap<String,LinkedList<String>>> maps){
    boolean incomingVertices = false;
    while (notesList.size()!=0){
      incomingVertices = findIncomingVertices(notesList,maps);
      if (incomingVertices){
        sortIndex++;
        tSort(notesList,maps);
      }
      else{
        deleteOutgoingVertices(notesList,maps);
        tSort(notesList,maps);
      }
    }
  }
  /** searches through the ! map
  if the sortIndex is listSize, start at 0 to check for notes with new incoming vertices missing from prior deletions
  finds each notes' !value in the !hashmap
  looks for a ^reference key in the ^map that contains the !value of the note (compares strings without their symbols), and returns true if it does
  otherwise, prints the note name because no incoming references were found and then returns false
  **/
  public boolean findIncomingVertices(LinkedList<String> notesList,LinkedList<HashMap<String,LinkedList<String>>> maps){
    for (String keyOne : maps.get(2).keySet()){
      if (sortIndex == notesList.size()){
        sortIndex = 0;
      }
      if (maps.get(2).get(keyOne).contains(notesList.get(sortIndex))){
        for (String keyTwo : maps.get(3).keySet()){
          if ((keyOne.substring(1)).equals(keyTwo.substring(1)) && maps.get(3).get(keyTwo).size()!=0){
            return true;
          }
        }
      }
    }
    System.out.println("\n" + notesList.get(sortIndex));
    return false;
  }
  /** for each ^refrence key in the ^map, goes through its LL to look for the note in questions
  if it finds any occurrences of the note, they are deleted from the LL as they are outgoing references/vertices
  the note is then removed from the list of all notes and sortIndex is set at 0 since indexes of the list changed
  **/
  public void deleteOutgoingVertices(LinkedList<String> notesList,LinkedList<HashMap<String,LinkedList<String>>> maps){
    for (String key : maps.get(3).keySet()){
      while (maps.get(3).get(key).contains(notesList.get(sortIndex))){
        maps.get(3).get(key).remove(notesList.get(sortIndex));
      }
    }
    notesList.remove(sortIndex);
    sortIndex = 0;
  }
}
