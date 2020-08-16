package schedule;

import java.util.ArrayList;

public class Scheduler {
	
	private Machine[] M;
	
	/** Skapar ett schemaläggare för maskinerna 
		i vektorn machines. */
	public Scheduler(Machine[] machines) {
		M = machines;
	}
	
	/** Fördelar jobben i listan jobs på maskinerna. 
	 	Jobben är sortrade är sorterad efter avtagande tidsåtgång. */
	public void makeSchedule(ArrayList<Job> jobs) {
		M[0].assignJob(jobs.get(0));
		int lowest = 0;
		for(int i = 1; i < jobs.size(); i++){
			for(int j = 1; j < M.length; j++){
				if(M[j-1].getTotalTime() > M[j].getTotalTime()){
					lowest = j;
				}
				
			}
			M[lowest].assignJob(jobs.get(i));
		}
		
		
	}
	
	
	
	/** Skriver ut maskinernas scheman. */
	public void printSchedule() {
		for(int i=0;i < M.length; i++){
			System.out.println(M[i].toString());
		}
	}
}
