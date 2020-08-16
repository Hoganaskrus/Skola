package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		List<TextProcessor> Tp = new ArrayList<TextProcessor>();
		TextProcessor p = new SingleWordCounter("nils");
		TextProcessor n = new SingleWordCounter("norge");
		Tp.add(p);
		Tp.add(n);
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		
		TextProcessor mwc = new MultiWordCounter(REGIONS);

		Scanner s2 = new Scanner(new File("undantagsord.txt"));
		Set <String> stopwords = new HashSet<String>();
		while(s2.hasNext()){
			stopwords.add(s2.next().toLowerCase());
		}
		TextProcessor wwc = new GeneralWordCounter(stopwords);
		
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for(TextProcessor swc : Tp){
				swc.process(word);
			}
			mwc.process(word);
			wwc.process(word);
		}
		

		
		

		s.close();
		s2.close();
		for(TextProcessor Tps: Tp){
			Tps.report();
		}
		mwc.report();
		wwc.report();
	}
}