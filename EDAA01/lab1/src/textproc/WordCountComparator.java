package textproc;

import java.util.Comparator;

import java.util.Map;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String,Integer>>{

	public static void main(String[] args) {

		
	}
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		if((int) o1.getValue() == (int) o2.getValue()){
			return o1.getKey().compareTo(o2.getKey());
		}
		return Boolean.compare(o1.getValue() <= o2.getValue() , o1.getValue() >= o2.getValue());
	}

	
	}

	

