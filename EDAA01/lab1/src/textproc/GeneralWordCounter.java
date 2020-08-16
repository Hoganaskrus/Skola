package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GeneralWordCounter implements TextProcessor{

	private Map<String,Integer> map = new HashMap<>();
	private Set<String> s;
	public GeneralWordCounter(Set<String> s){
		this.s = s;
	}
	
	
	public void process(String w) {
		if(!s.contains(w)){
			if(map.containsKey(w)){
				map.put(w, map.get(w) + 1);
				
			}else map.put(w, 0);
			
			
			
		}
	}

	
//	public void report() {
//		for(String key : map.keySet()){
//			if(map.get(key) >= 200){
//				System.out.println(key + ": " + map.get(key));
//			}
//		}
//}
	public void report(){
		Set<Map.Entry<String, Integer>> wordSet = map.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		for(int i = 0; i < 20; i++){
			System.out.println(wordList.get(i));
		}
	}
	
	public Set<Map.Entry<String, Integer>> getWords(){
		return map.entrySet();
	}
	

}
