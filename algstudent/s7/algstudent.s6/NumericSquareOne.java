package src;

public class NumericSquareOne {
	private static String filename = "C:/Users/gonza/Desktop/UNI/Algoritmia/LAB08/LAB08/Files/test00.txt";

	private int [][] board;
	private char [][] operators;
	private boolean [][] untouchables;
	private boolean isSolved;
	
	public static char MULT='*';
	public static char DIV='/';
	public static char ADD='+';
	public static char MINUS='-';
	public static char EQ='=';
	
	public NumericSquareOne(int [][] board, char[][] operators, boolean [][] untouchables) {
		this.board = board;
		this.operators=operators;
		this.untouchables=untouchables;
	}
	
	public NumericSquareOne() {
		Parser p = new Parser(filename);
		this.board = p.getBoard();
		this.operators=p.getOperators();
		this.untouchables=p.getUntouchables();
	}
	
	public NumericSquareOne(String filename) {
		Parser p = new Parser(filename);
		this.board = p.getBoard();
		this.operators=p.getOperators();
		this.untouchables=p.getUntouchables();
	}

	public boolean isSolved() {
		return isSolved;
	}
	
	public int [][] getResult(){
		return board;
	}

	public void backtracking() {
		backtracking(0,0);
	}
	private void backtracking(int row, int col) {
		if(!untouchables[row][col]) {
			for(int k = 0;k<10;k++) {
				if(isSolved)
					return;
				board[row][col]=k;

				if(row == board.length-2 && col == board.length-2) {
					if(isValidRow(row) && isValidCol(col)) {
						isSolved = true;
					}else if(k==9)
						return;
				}
				else if(row == board.length-2) {
					if(isValidCol(col)) {
						backtracking(row, col+1);
					}
				}
				else if(isValidRow(row)) {
					backtracking(row+1,0);
				}
				else if(col == board.length-2 && k == 9) {
					return;
				}else if(col != board.length-2) {
					backtracking(row, col+1);
				}
			}
		}
		else if(row == board.length-2 && col == board.length-2) {
			if(isValidRow(row) && isValidCol(col)) {
				isSolved = true;
			}else
				return;
		}
		else if(row == board.length-2) {
			if(isValidCol(col)) {
				backtracking(row, col+1);
			}else {
				return;
			}
		}
		else if(col == board.length-2) {
			if(isValidRow(row)) {
				backtracking(row+1, 0);
			}else
				return;
		}else
			backtracking(row,col+1);
	}
	
	public boolean isValidRow(int row) {
		int rowOp = row * 2;
		int first = board[row][0];
		for(int i = 1; i<board.length;i++) {
			if(operators[rowOp][i-1] == ADD) {
				first += board[row][i];
			}
			if(operators[rowOp][i-1] == MULT) {
				first *= board[row][i];
			}
			if(operators[rowOp][i-1] == MINUS) {
				first -= board[row][i];
			}
			if(operators[rowOp][i-1] == DIV) {
				if(board[row][i]==0 || first%board[row][i] !=0) {
					return false;
				}
				first /= board[row][i];
			}
			if(operators[rowOp][i-1] == EQ) {
				if(first!=board[row][i])
					return false;
			}
		}
		return true;
	}
	
	public boolean isValidCol(int col) {
		int column = board[0][col];
		int aux=1;
		
		for(int i = 1; i<board.length;i++) {
			if(operators[aux][col] == ADD) {
				column += board[i][col];
			}
			if(operators[aux][col] == MULT) {
				column *= board[i][col];
			}
			if(operators[aux][col] == DIV) {
				if(board[i][col]==0 || column%board[i][col] !=0) {
					return false;
				}
				column /= board[i][col];
			}
			if(operators[aux][col] == MINUS) {
				column -= board[i][col];
			}
			if(operators[aux][col] == EQ) {
				if(column!=board[i][col])
					return false;
			}
			aux+=2;
		}
		return true;
	}

	public void printMatrix() {
		int aux = 0;
		for(int i = 0;i<operators.length;i++) {
			for(int j = 0;j<board.length-1;j++) {
				if(i==0 || i % 2 == 0) {
					System.out.print(board[aux][j] + " " + operators[i][j] + " ");
				}else {
					System.out.print(operators[i][j] + "   ");
				}
			}
			if(i==0 || i % 2 == 0) {
				System.out.print(board[aux][board.length-1]);
				aux++;
			}
			System.out.println();
		}
		for(int i = 0;i<board.length-1;i++) {
			System.out.print(board[board.length-1][i]+ "  ");
		}
		System.out.println();
	}

	public String[][] getSol() {
		String [][] result = new String [board.length-1][board.length-1];
		
		for(int i =0;i<board.length-1;i++) {
			for(int j = 0;j<board.length-1;j++) {
				result[i][j] = Integer.toString(board[i][j]);
			}
		}
		return result;
	}

}
