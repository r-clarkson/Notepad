package classes;

import java.io.*;
import java.util.*;

public class TSort {
  int sortIndex;
  Notebook notebook;
  LinkedList<String> notesList;
  HashMap<String, LinkedList<String>> referenceMap;
  HashMap<String, LinkedList<String>> titleMap;

  public TSort(Notebook notebook) {
    sortIndex = 0;
    this.notebook = notebook;
    notesList = notebook.getNotesList();
    referenceMap = notebook.getListType("^");
    titleMap = notebook.getListType("!");
  }

  /**
   * topological sort of note files finds notes without any incoming vertices, prints them, and then
   * deletes those notes' outgoing vertices the note that was printed is then deleted from the list
   * of all notes then repeats until the notes list has reached 0 notes sortIndex is global because
   * it is used in different ways in each of the sort functions
   */
  public boolean tSort() {
    boolean incomingVertices = false;
    while (notesList.size() != 0) {
      incomingVertices = findIncomingVertices();
      if (incomingVertices) {
        sortIndex++;
        tSort();
      } else {
        deleteOutgoingVertices();
        tSort();
      }
    }
    return notesList.size() == 0 ? true : false;
  }
  /**
   * searches through the ! map if the sortIndex is listSize, start at 0 to check for notes with new
   * incoming vertices missing from prior deletions finds each notes' !value in the !hashmap looks
   * for a ^reference key in the ^map that contains the !value of the note (compares strings without
   * their symbols), and returns true if it does otherwise, prints the note name because no incoming
   * references were found and then returns false
   */
  public boolean findIncomingVertices() {
    for (String keyOne : titleMap.keySet()) {
      if (sortIndex == notesList.size()) {
        sortIndex = 0;
      }
      if (titleMap.get(keyOne).contains(notesList.get(sortIndex))) {
        for (String keyTwo : referenceMap.keySet()) {
          if ((keyOne.substring(1)).equals(keyTwo.substring(1))
              && referenceMap.get(keyTwo).size() != 0) {
            return true;
          }
        }
      }
    }
    System.out.println("\n*" + notesList.get(sortIndex) + "\n");
    return false;
  }
  /**
   * for each ^refrence key in the ^map, goes through its LL to look for the note in questions if it
   * finds any occurrences of the note, they are deleted from the LL as they are outgoing
   * references/vertices the note is then removed from the list of all notes and sortIndex is set at
   * 0 since indexes of the list changed
   */
  public boolean deleteOutgoingVertices() {
    boolean removed = false;
    for (String key : referenceMap.keySet()) {
      while (referenceMap.get(key).contains(notesList.get(sortIndex))) {
        referenceMap.get(key).remove(notesList.get(sortIndex));
        removed = true;
      }
    }
    notesList.remove(sortIndex);
    sortIndex = 0;
    return removed;
  }
}
