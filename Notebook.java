import java.util.HashMap<K,V>;
import java.util.LinkedList
import java.util.Map<E>;

public class Notebook{
	Map<String, LinkedList<String>> topicMention = HashMap<String, LinkedList<String>>;
	LinkedList<String> topicMentionLL = new LinkedList<String>();
	LinkedList<String> individualMentionLL = new LinkedList<String>();
	LinkedList<String> uniqueMentionLL = new LinkedList<String>();
	LinkedList<String> refeferenceMentionLL = new LinkedList<String>();
	LinkedList<String> urlLL = new LinkedList<String>();
	
	public void passNote(Note note){
		
	}
	
	public void intializeMap(List<String> list){
		for (int i = 0; i < list.size(); i++){
			topicMention.put(n.getTopicMention().get(i), topicMentionLL);
		}
	}
}