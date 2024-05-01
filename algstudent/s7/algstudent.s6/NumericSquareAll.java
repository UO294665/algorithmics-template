package src;

import java.util.LinkedList;

public class NumericSquareAll {
	private static String filename = "C:/Users/gonza/Desktop/UNI/Algoritmia/LAB08/LAB08/Files/test00.txt";

	private int [][] board;
	private char [][] operators;
	private boolean [][] untouchables;
	private boolean isSolved;
	private int solutions=0;
	private LinkedList<int [][]> solutionsReached = new LinkedList<int [][]>();
	
	public static char MULT='*';
	public static char DIV='/';
	public static char ADD='+';
	public static char MINUS='-';
	public static char EQ='=';
	
	public NumericSquareAll(int [][] board, char[][] operators, boolean [][] untouchables) {
		this.board = board;
		this.operators=operators;
		this.untouchables=untouchables;
		
	}
	
	public NumericSquareAll() {
		Parser p = new Parser(filename);
		this.board = p.getBoard();
		this.operators=p.getOperators();
		this.untouchables=p.getUntouchables();
	}
	
	public NumericSquareAll(String filename) {
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
	
	public int getNumberOfSolutions() {
		return solutions;
	}
	
	public void backtracking() {
		backtracking(0,0);
	}

	private void backtracking(int row, int col) {
		
		if(!untouchables[row][col]) {
			for(int val = 0; val < 10; val ++) {
				board[row][col] = val;
				if(hasSolution()) {
					isSolved = true;
					saveState();
					solutions++;
				}
				if(row == col && row == board.length-2)
					continue;
				else if(col == board.length - 2) {
					if(isValidRow(row)) {
						backtracking(row + 1, 0);
					}
				}
				else if(row == board.length - 2) {
					if(isValidCol(col)) {
						backtracking(row, col + 1);
					}
				}
				else {
					backtracking(row, col+1);
				}
			}
		}else {
			if(row == col && row == board.length-2)
				return;
			else if(col == board.length - 2) {
				if(isValidRow(row)) {
					backtracking(row + 1, 0);
				}
			}
			else if(row == board.length - 2) {
				if(isValidCol(col)) {
					backtracking(row, col + 1);
				}
			}
			else {
				backtracking(row, col+1);
			}
		}
	}
	
	private boolean hasSolution() {
		for(int i = 0; i<board.length-1;i++) {
			if(!(isValidRow(i) && isValidCol(i))) {
				return false;
			}
		}
		return true;
	}

	private void saveState() {
		int [][] copy = new int [board.length][board.length];
		for (int i = 0; i < board.length - 1; i++) {
			for(int j = 0; j<board.length-1; j++) {
				copy[i][j] = board[i][j];
			}
        }
		solutionsReached.add(copy);
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
				if(board[row][i]==0) {
					return false;
				}
				if(first%board[row][i] !=0) {
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
				if(board[i][col]==0) {
					return false;
				}
				if(column%board[i][col] !=0) {
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
		int [][] auxBoard = this.board;
		if(isSolved)
			auxBoard = solutionsReached.get(0);
		for(int i =0;i<auxBoard.length-1;i++) {
			for(int j = 0;j<auxBoard.length-1;j++) {
				result[i][j] = Integer.toString(auxBoard[i][j]);
			}
		}
		return result;
	}
	
	private boolean canBeSolution(int row, int col) {
		if(col == board.length - 2)
			return isValidRow(row);
		if(row == board.length - 2)
			return isValidCol(col);
		
		return true;
	}

}
