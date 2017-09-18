import java.util.*;
/**
Reads in notes and adds them to HashMaps by type
Rough draft...
**/
public class Notebook{
	char type = '\0';
	//initialize map and LLs
	HashMap<String, LinkedList<String>> topicMentionMap ;
	HashMap<String, LinkedList<String>> individualMentionMap;
	HashMap<String, LinkedList<String>> referenceMentionMap;
	HashMap<String, LinkedList<String>> uniqueMentionMap;
	HashMap<String, LinkedList<String>> urlMap;

	public Notebook(){
		topicMentionMap = new HashMap<String, LinkedList<String>>();
		individualMentionMap = new HashMap<String, LinkedList<String>>();
		referenceMentionMap = new HashMap<String, LinkedList<String>>();
		uniqueMentionMap = new HashMap<String, LinkedList<String>>();
		urlMap = new HashMap<String, LinkedList<String>>();
	}
	/** custom add to map function
	for the size of the given note's identifier list that needs to be added,
	takes the current LL in that identifier's place in the current map (or creates a new one if empty),
	then adds the new noteName to that LL and puts it into the correct map based on the list type
	https://stackoverflow.com/questions/26478646/adding-to-a-linkedlist-in-a-hashmapstring-linkedlist
	**/
	public void passToMap(Note n, LinkedList<String> list){
		for (int i = 0; i < list.size(); i++){
			type = list.get(i).charAt(0);
		}

		Map<String, LinkedList<String>> m = getListType(type);
		//make a map.
		Map<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();
		//for each item in each list,
		for (int j = 0; j < list.size(); j++){

			//check the first character of a list item to get the list type
			//set map equal to the correct one based on list type
			map = m;
			//make a LL of previous LLs so that old items are not lost
			LinkedList<String> previousNotes = map.get(list.get(j));
			//if the list is empty, make a new one
			if (previousNotes==null){
				previousNotes = new LinkedList<String>();
				map.put(list.get(j),previousNotes);
			}
			//add the note name to the LL of note names for that item
			previousNotes.add(n.getName());
			//based on list type, set the specific map equal to the general one
			m = map;
		}
	}
	/** determines list type based on the first character of an item **/
	public HashMap<String, LinkedList<String>> getListType(char type){
		switch (type){
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
}
