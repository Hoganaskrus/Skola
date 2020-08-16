public class SudokuSolver {

	public Sudoku sudoku;
	private boolean[][] userEntry;

	public SudokuSolver(Sudoku s) {
		sudoku = s;
		userEntry = setBooleanMatrix(s);
	}

	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		
		s.sudoku[0][2] = 8;
		s.sudoku[0][5] = 9;
		s.sudoku[0][7] = 6;
		s.sudoku[0][8] = 2;
		s.sudoku[1][8] = 5;
		s.sudoku[2][0] = 1;
		s.sudoku[2][2] = 2;
		s.sudoku[2][3] = 5;
		s.sudoku[3][3] = 2;
		s.sudoku[3][4] = 1;
		s.sudoku[3][7] = 9;
		s.sudoku[4][1] = 5;
		s.sudoku[4][6] = 6;
		s.sudoku[5][0] = 6;
		s.sudoku[5][7] = 2;
		s.sudoku[5][8] = 8;
		s.sudoku[6][0] = 4;
		s.sudoku[6][1] = 1;
		s.sudoku[6][3] = 6;
		s.sudoku[6][5] = 8;
		s.sudoku[7][0] = 8;
		s.sudoku[7][1] = 6;
		s.sudoku[7][4] = 3;
		s.sudoku[7][6] = 1;
		s.sudoku[8][6] = 4;
		
		
		
	
		
		SudokuSolver ss = new SudokuSolver(s);
		ss.solve();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(ss.sudoku.sudoku[i][j]);
			}
			System.out.println("");
		}
		if (ss.sudoku.sudokuSolved) {
			System.out.println("slut");
		}

	}

	private boolean[][] setBooleanMatrix(Sudoku s) {
		boolean[][] temp = new boolean[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (s.sudoku[i][j] != 0) {
					temp[i][j] = true;
				}
			}
		}
		return temp;
	}

	public boolean checkinput() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (userEntry[i][j]) {
					if(!checkMove(sudoku.sudoku[i][j],i,j)){
						return false;
					}
					
				}
			}
		}
		return true;
	}

	private boolean checkMove(int m, int row, int col) {
		for (int i = 0; i < 9; i++) {
			if (sudoku.sudoku[row][i] == m && col != i) {
				return false;
			}
		
			if (sudoku.sudoku[i][col] == m && row != i) {
				return false;
			}
		}
		int boxRow = row / 3;
		int boxColumn = col / 3;
		for(int i=3*boxRow; i <= 2 + 3*boxRow; i++){
			for(int j=3*boxColumn; j <= 2 + 3*boxColumn; j++){
				if(sudoku.sudoku[i][j] == m && (i != row && j != col)){
					return false;
				}
			}
		}
		
		
		
		return true;
	}

	public boolean solve() {
		if (!checkinput()) {
			return false;
		}
		solveHelp(0, 0);
		if (!sudoku.sudokuSolved) {
			return false;
		}
		return true;
	}
	
	
	//Denna krashar ifall man sätter int olagliga saker från början dvs den kan inte ändra nono som är fel.
	public boolean solveHelp(int row, int column) {
		// Grundfallet/ slutet av mazen.
		if (sudoku.sudoku[8][8] != 0) {
			sudoku.sudokuSolved = true;
			return true;
		}
		if (userEntry[row][column]) {
			while(userEntry[row][column]){
				row = goForward(row, column)[0];
				column = goForward(row, column)[1];
			}
		}
		if (!userEntry[row][column]) {
			for (int i = 1; i <= 9; i++) {
				if (legalMove(i, row, column)) {
					sudoku.sudoku[row][column] = i;
					// om sudokut blir klart efter man satt in ovanför så kommer
					// solveHelp returna true;
					if (solveHelp(goForward(row, column)[0], goForward(row, column)[1])) {
						return true;
					}
				}
			}

			// Denna ligger utanför loopen, dvs endast då den har gått igenom
			// 1-9 ska den sätta noll och returna false.
			sudoku.sudoku[row][column] = 0;
			
		}
		return false;
		
	}

	private boolean legalMove(int m, int row, int column) {
		if (m > 0 && m < 10) {

			for (int i = 0; i < 9; i++) {
				// Kan vara fel
				if (sudoku.sudoku[row][i] == m) {
					return false;
				}
				if (sudoku.sudoku[i][column] == m) {
					return false;
				}
			}
			if (checkSquare(m, row, column)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkSquare(int m, int row, int column) {
		int boxRow = row / 3;
		int boxColumn = column / 3;

		for (int i = (0 + 3 * boxRow); i <= 2 + (3 * boxRow); i++) {
			for (int j = (0 + 3 * boxColumn); j <= 2 + (3 * boxColumn); j++) {
				if (sudoku.sudoku[i][j] == m) {
					return false;
				}
			}
		}
		return true;
	}

	private int[] goForward(int row, int column) {
		int[] index = new int[2];
		index[0] = goForwardRow(row, column);
		index[1] = goForwardColumn(row, column);
		return index;
	}

	private int goForwardRow(int row, int column) {
		if (column == 8 && row != 8) {
			row++;
		}
		return row;
	}

	private int goForwardColumn(int row, int column) {
		if (column == 8) {
			column = 0;
			return column;
		}
		return column + 1;
	}
}