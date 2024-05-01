package src;

public class HeuristicState implements Comparable<HeuristicState>{
	private int[][] board;
	private int row;
	private int col;
	private int heuristic = 0;
	
	public static char MULT='*';
	public static char DIV='/';
	public static char ADD='+';
	public static char MINUS='-';
	public static char EQ='=';
	
	public HeuristicState(int[][] board, int row, int col) {
		this.board = copy(board);
		this.row = row;
		this.col = col;
		setHeuristic();
	}
	public void setHeuristic() {
		for(int i = 0; i < board.length-1; i++) {
			for(int j = 0;  j < board.length-1; j++) {
				if(board[i][j] == -1) {
					heuristic++;
				}
			}
		}
	}
	
	public int computeRow(int row) {
		char[][] operators = NumericSquareBaB.operators;
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
					return Integer.MAX_VALUE;
				}
				first /= board[row][i];
			}
			if(operators[rowOp][i-1] == EQ) {
				return Math.abs(first-board[row][i]);
			}
		}
		return Integer.MAX_VALUE;
	}
	
	public int computeColumn(int col) {
		char[][] operators = NumericSquareBaB.operators;
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
					return Integer.MAX_VALUE;
				}
				column /= board[i][col];
			}
			if(operators[aux][col] == MINUS) {
				column -= board[i][col];
			}
			if(operators[aux][col] == EQ) {
				return Math.abs(column-board[i][col]);
			}
			aux+=2;
		}
		return Integer.MAX_VALUE;
	}
	private int[][] copy(int[][] prevBoard){
		int[][] copy = new int[prevBoard.length][prevBoard.length];
		
		for(int i = 0; i < prevBoard.length; i++) {
			for(int j = 0; j < prevBoard.length; j++) {
				copy[i][j] = prevBoard[i][j];
			}
		}
		return copy;
	}
	
	public int getHeuristic() {
		return heuristic;
	}

	public int[][] getBoard() {
		return board;
	}
	@Override
	public int compareTo(HeuristicState o) {
		if(o.getHeuristic()>this.getHeuristic()) {
			return -1;
		}
		return 1;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}

}
