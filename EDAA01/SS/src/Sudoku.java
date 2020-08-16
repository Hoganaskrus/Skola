
public class Sudoku {

	public Integer[][] sudoku;
	public boolean sudokuSolved;
	
	
	public Sudoku(){
		sudoku = new Integer[9][9];
		
		for(int i= 0; i<9;i++){
			for(int j = 0; j<9;j++){
				sudoku[i][j]=0;
			}
		}
		sudokuSolved = false;
	}
	
	
	
}
