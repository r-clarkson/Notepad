
import java.util.*;

/**
IGNORE everything, currently testing with only topic mentions
**/
public class Notebook{
	//initialize map and LLs
	Map<String, LinkedList<String>> topicMentionMap = new HashMap<String, LinkedList<String>>();
	//should we make a list of lists? more organized...
	LinkedList<String> topicMentionLL = new LinkedList<String>();

	/** custom add to map function
	for the size of the given note's identifier list that needs to be added,
	takes the current LL in that identifier's place in the current map (or creates a new one if empty),
	then adds the new noteName to that LL
	https://stackoverflow.com/questions/26478646/adding-to-a-linkedlist-in-a-hashmapstring-linkedlist
	**/
	public void addToMap(Note n){
		for (int i = 0; i < n.getTopicMention().size(); i++){
			LinkedList<String> previousNotes = topicMentionMap.get(n.getTopicMention().get(i));
			if (previousNotes==null){
				previousNotes = new LinkedList<String>();
				topicMentionMap.put(n.getTopicMention().get(i),previousNotes);
			}
			previousNotes.add(n.getName());
		}
	}
	/**
	an example function for how we might print a report
	**/
	public void printTopicMentionNotes(String topicMention){

		for (int i = 0; i < topicMentionMap.get(topicMention).size(); i++){
			System.out.println(topicMentionMap.get(topicMention).get(i));
		}

	}
}
