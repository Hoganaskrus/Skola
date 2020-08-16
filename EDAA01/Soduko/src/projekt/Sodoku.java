package projekt;

public class Sodoku {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	// Lösningsalgoritm:
	// Om boxEmpty, testa att sätta in 1. Om det ger (legalMove==true), gå vidare till nästa ruta och sätt in 1.
	// Om 1 ger (legalMove==false), testa 2 istället. Om 1 - 9 ej funkar, gå tillbaks till förra rutan och sätt in två. 
	// Om 2 ger (legalMove==true), gå vidare till nästa ruta. 
	
	
	// Metoder:
	
	// firstBox: Kolla om iteratorn står på första rutan. Använd listIterator.hasPrevious.
	
	// legalMove(int m) : Kolla om nuvarande rad eller kolumn innehåller värdet som testNextNumber försöker sätta in. 
	//			 		  Om rad och kolumn inte innehåller talet, return true. Annars return false. 
	
	// boxEmpty: Kolla om det finns något input från användaren i rutan som iteratorn står på.
	// 			 Om (listIterator.next==null), retrun true, annars return false. 
	
	// testNextNumber: Om (boxEmpty==true), for(int i=1; i<=9; i++){ if("i = legalMove") sätt arrayList[currentRow][currentColumn]=1
	// 				   if("legalMove(i)"){ sätt arrayList[currentRow][currentColumn]=i och return true}
	
	//				   OBS! 9x9 booleanmaris - Kolla vilka som användaren fyllt i.
	
	//				   Return false efter loopen (alltså om ingenting har satts in)
	//				   Om (boxEmpty==false), return true. (Låtsas som att den satt in ett "legal" tal)
	
	// goBack: Om(firstBox==false), sätt arrayList[currentRow][currentColumn]=null och sen listIterator.previous(). Ändra också currentRow och currentColumn
	//		   Ändra också currentRow och currentColumn.
	
	// nextBox: Gå från nuvarande ruta till rutan till höger. Om du står på sista rutan på en rad och vill gå till nästa, 
	// 			gå till första rutan på raden under. Använd if(listIterator.hasNext) --> listIterator.next och 
	//			ändra currentRow och currentColumn.
	//			Gör currentColumn++. Om (currentColumn==9){ Gör currentRow++ och currentColumn=0;  Om(currentRow == 9)
	//			sätt sudokuSolved=true och currentRow=0 och currentColumn=0;
	
	// solve: 1. while(sudokuSolved==false)
	//		  2. if(testNextNumber==true){ "gör nextBox"} else{"gör goBack" }
	//		  3. Är det verkligen allt?
	
	
	
	// Extra:	Minst 17 clues, annars "No unique solution"
	
	// Extra: 	"legalSquares: Innan rekursionsalgoritmen börjar, gör "enkla" test vilka 3x3-rutor, rader
	// 	och kolumner som de olika siffrorna får vara i. Lägg in resultatet för varje siffra i 9x9-booleanmatriser.  
	//	När en ny siffra testas i rekursionsalgoritmen kollar den först i motsvarnde siffras boolean-matris för den plats
	//  den tänker sätta in siffran på. Om booleanmatrisen visar True för det indexet, så fortsätter algoritmen.
	//	Annars testas nästa siffra.
	//	(ExtraExtra: Uppdatera legalSquares efter varje insatt tal, snabbare än att testa alla?)
	
	
	//  Sudokurutan:
	// 	Innehåller: Ett listIterator-objekt, en 9x9-arrayList  (8x8 i Java?), en currentRow-integer, en currentColumn-integer
	//	och en sudokuSolved-boolean.
	//	När rutan skapas fästs listIterator-objektet vid den första rutan i första raden.
	//	

}
