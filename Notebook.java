
import java.util.*;

/**
Reads in notes and adds them to HashMaps by type
Rough draft...
**/
public class Notebook{
	//initialize map and LLs
	Map<String, LinkedList<String>> topicMentionMap = new HashMap<String, LinkedList<String>>();
	Map<String, LinkedList<String>> individualMentionMap = new HashMap<String, LinkedList<String>>();
	Map<String, LinkedList<String>> referenceMentionMap = new HashMap<String, LinkedList<String>>();
	Map<String, LinkedList<String>> uniqueMentionMap = new HashMap<String, LinkedList<String>>();
	Map<String, LinkedList<String>> urlMap = new HashMap<String, LinkedList<String>>();

	/** custom add to map function
	for the size of the given note's identifier list that needs to be added,
	takes the current LL in that identifier's place in the current map (or creates a new one if empty),
	then adds the new noteName to that LL and puts it into the correct map based on the list type
	https://stackoverflow.com/questions/26478646/adding-to-a-linkedlist-in-a-hashmapstring-linkedlist
	**/
	public void addToMap(Note n){
		char listType = '\0';

		//for each list,
		for (int i = 0; i < n.getIdentifierLists().size(); i++){
			//make a map.
			Map<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();
			//for each item in each list,
			for (int j = 0; j < n.getIdentifierLists().get(i).size(); j++){
				//check the first character of a list item to get the list type
				listType = n.getIdentifierLists().get(i).get(j).charAt(0);
				//set map equal to the correct one based on list type
				switch (listType){
					case '#':
						map = topicMentionMap;
						break;
					case '^':
						map = referenceMentionMap;
						break;
					case '@':
						map = individualMentionMap;
						break;
					case '!':
						map = uniqueMentionMap;
						break;
					default:
						map = urlMap;
				}
				//make a LL of previous LLs so that old items are not lost
				LinkedList<String> previousNotes = map.get(n.getIdentifierLists().get(i).get(j));
				//if the list is empty, make a new one
				if (previousNotes==null){
					previousNotes = new LinkedList<String>();
					map.put(n.getIdentifierLists().get(i).get(j),previousNotes);
				}
				//add the note name to the LL of note names for that item
				previousNotes.add(n.getName());
				//based on list type, set the specific map equal to the general one
				switch (listType){
					case '#':
						topicMentionMap = map;
						break;
					case '^':
						referenceMentionMap = map;
						break;
					case '@':
						individualMentionMap = map;
						break;
					case '!':
						uniqueMentionMap = map;
						break;
					default:
						urlMap = map;
				}
			}
		}
	}

	/**
	an example function for how we might print a report
	**/
	public void printMap(Map<String, LinkedList<String>> mapType){

		for (String key : mapType.keySet()) {
    	System.out.println("\nKEY: " + key + " ");
			for (int i = 0; i < mapType.get(key).size(); i++){
				System.out.print( mapType.get(key).get(i) + " ");
			}
		}
	}

	public void printMaps(){
		printMap(topicMentionMap);
		printMap(uniqueMentionMap);
		printMap(individualMentionMap);
		printMap(referenceMentionMap);
		printMap(urlMap);

	}
}
