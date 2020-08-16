package schedule;

import java.util.ArrayList;

public class Machine {
	private int nbr;
	private ArrayList<Job> s = new ArrayList<Job>();
	/** Skapar maskin nr nbr. */
	public Machine(int nbr) {
		this.nbr = nbr;
	}

	/** Tar reda på maskinens nr. */
	public int getNbr() {
		return nbr;
	}	


	/** Tilldelar maskinen jobbet j. */
	public void assignJob(Job j) {
		s.add(j);
	}
	
	/** Tar bort alla jobb från maskinen. */
	public void clearJobs() {
		s.clear();
	}
	
	/** Tar bort och returnerar nästa jobb som maskinen ska utföra. 
	 	Returnerar null om maskinen inte har några jobb. */
	public Job getNextJob() {
		s.remove(0);
		return s.get(0);
		
	}
	
	/** Tar reda på den totala tiden för maskinens jobb. */
	public int getTotalTime() {
		int k = 0;
		for(int i = 0; i < s.size(); i++){
			k += s.get(i).getTime();
		}
		return k;
	}
	
	/** Returnerar en sträng som innehåller maskinens nr samt maskinens
    schemalagda jobb inom [] med kommatecken mellan. */
	public String toString() {
		return  this.nbr + s.toString() ;
	}
	
}
