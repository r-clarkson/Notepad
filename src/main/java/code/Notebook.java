package notepad.src.main.java.code;

import java.util.*;

/**
 * Reads in notes and adds them to HashMaps by type Generating a map for each type seems too
 * specific to be AGILE
 */
public class Notebook {
  char listType = '\0';
  LinkedList<String> notes;
  List<String> uniqueMentions;
  HashMap<String, LinkedList<String>> topicMentionMap;
  HashMap<String, LinkedList<String>> individualMentionMap;
  HashMap<String, LinkedList<String>> referenceMentionMap;
  HashMap<String, LinkedList<String>> uniqueMentionMap;
  HashMap<String, LinkedList<String>> urlMap;

  /**
   * initializes the hashmaps is there a more refactor-ish way to do this while still being specific
   * enough to the assignment? A list of these would not work because we define them later...
   */
  public Notebook() {
    notes = new LinkedList<String>();
    uniqueMentions = new LinkedList<String>();
    topicMentionMap = new HashMap<String, LinkedList<String>>();
    individualMentionMap = new HashMap<String, LinkedList<String>>();
    referenceMentionMap = new HashMap<String, LinkedList<String>>();
    uniqueMentionMap = new HashMap<String, LinkedList<String>>();
    urlMap = new HashMap<String, LinkedList<String>>();
  }

  /**
   * custom pass to map function 1. gets the type of list by checking the symbol in front of the
   * first item 2. creates two maps; one is the current typeMap such as topicMentionMap, the other
   * is a general map to work with (temporary map) 3. for each item in the list, sets the temporary
   * map equal to the current map 4. creates a LL to contain all previous values that are currently
   * in the current map (so they are not lost when new values are added) 5. if the list is empty,
   * initializes a new list and puts previous values in it 6. adds the new value to the LL and then
   * sets the current map equal to the temporary map 7. at the end, this function also adds the list
   * of all notes parsed from the note to the bigger list of all notes parsed in all notes so far...
   * https://stackoverflow.com/questions/26478646/adding-to-a-linkedlist-in-a-hashmapstring-linkedlist
   */
  public boolean passToMap(Note n, LinkedList<String> list) {
    for (int i = 0; i < list.size(); i++) {
      listType = list.get(i).charAt(0);
    }

    Map<String, LinkedList<String>> m = getListType(listType);
    Map<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();

    for (int j = 0; j < list.size(); j++) {

      map = m;

      LinkedList<String> previousNotes = map.get(list.get(j));

      if (previousNotes == null) {
        if ((listType == '!' && !uniqueMentions.contains(list.get(j))) || listType != '!') {
          previousNotes = new LinkedList<String>();
          map.put(list.get(j), previousNotes);
        }
      }

      previousNotes.add(n.getName());

      m = map;

    }
    if (!notes.contains(n.getName())) {
      notes.add(n.getName());
    }

    if (m == map){
      return true;
    }
    else{
      return false;
    }
  }

  /** determines list type based on the first character of an item * */
  public HashMap<String, LinkedList<String>> getListType(char type) {
    switch (type) {
      case '#':
        return topicMentionMap;
      case '^':
        return referenceMentionMap;
      case '@':
        return individualMentionMap;
      case '!':
        return uniqueMentionMap;
      default:
        return urlMap;
    }
  }
  /** getter for the list of all notes parsed so far; needed for topological sort* */
  public LinkedList<String> getNotesList() {
    return notes;
  }
}
