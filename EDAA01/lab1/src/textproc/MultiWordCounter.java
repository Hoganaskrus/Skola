package textproc;

import java.util.HashMap;
import java.util.Map;

public class MultiWordCounter implements TextProcessor{

	private Map<String,Integer> map = new HashMap<>();
	
	public MultiWordCounter(String[] s){
		for(String word: s){
			map.put(word, 0);
		}
	}
	
	
	public void process(String w) {
		if(map.containsKey(w)){
			map.put(w, map.get(w) + 1);
		}
		
	}

	
	public void report() {
		for(String key : map.keySet()){
			System.out.println(key + ": " + map.get(key));
		}
		
	}

}
