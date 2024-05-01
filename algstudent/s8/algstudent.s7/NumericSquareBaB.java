package src;

import java.util.PriorityQueue;

public class NumericSquareBaB {
	private static String filename = "C:/Users/gonza/Desktop/UNI/Algoritmia/LAB08/LAB08/Files/test00.txt";

	private int [][] board;
	public static char [][] operators;
	private boolean [][] untouchables;
	private boolean isSolved;
	int [][] solution = board;
	private int numberOfDevelopedNodes = 0;
	private PriorityQueue<HeuristicState> nextStates = new PriorityQueue<HeuristicState>();
	
	public static char MULT='*';
	public static char DIV='/';
	public static char ADD='+';
	public static char MINUS='-';
	public static char EQ='=';
	
	public NumericSquareBaB(int [][] board, char[][] operators, boolean [][] untouchables) {
		this.board = board;
		NumericSquareBaB.operators=operators;
		this.untouchables=untouchables;
	}
	
	public NumericSquareBaB() {
		Parser p = new Parser(filename);
		this.board = p.getBoard();
		NumericSquareBaB.operators=p.getOperators();
		this.untouchables=p.getUntouchables();
	}
	
	public NumericSquareBaB(String filename) {
		Parser p = new Parser(filename);
		this.board = p.getBoard();
		NumericSquareBaB.operators=p.getOperators();
		this.untouchables=p.getUntouchables();
	}

	public boolean isSolved() {
		return isSolved;
	}
	
	public int [][] getResult(){
		return board;
	}
	
	public int getNumberOfDevelopedNodes() {
		return numberOfDevelopedNodes;
	}

	public void branchAndBound() {
		for(int i = 0;i<10;i++) {
			nextStates.add(new HeuristicState(board, 0, 0));
		}
		while(!nextStates.isEmpty()&&!isSolved) {
			HeuristicState currentState = nextStates.poll();
			int[][] newBoard = currentState.getBoard();
			int row = currentState.getRow();
			int col = currentState.getCol();
			numberOfDevelopedNodes++;
			branchAndBound(newBoard, row, col);
		}
	}

	private void branchAndBound(int [][] board, int row, int col) {
		int [][] copy = copyBoard(board);
		if(!untouchables[row][col]) {
			for(int i = 0; i < 10; i++) {
				copy[row][col] = i;
				if(row==board.length-2 && col==board.length - 2) {
					if(isValidRow(copy, row) && isValidCol(copy, col)) {
						solution = copyBoard(copy);
						isSolved=true;
					}
				}else if(row==board.length-2 && col != board.length-2){
					nextStates.add(new HeuristicState(copy, row, col+1));
				}else if(col==board.length - 2) {
					if(isValidRow(copy, row)){
						nextStates.add(new HeuristicState(copy, row+1, 0));
					}	
				}else {
					nextStates.add(new HeuristicState(copy, row, col+1));
				}
			}
		}else{
			if(row!=board.length-2&&col!=board.length-2) {
				nextStates.add(new HeuristicState(copy, row, col+1));
			}if(row==board.length-2 && col==board.length - 2) {
				if(isValidRow(copy, row) && isValidCol(copy, col)) {
					isSolved=true;
					solution = copyBoard(copy);
				}
			}else if(row==board.length-2 && col != board.length-2){
				nextStates.add(new HeuristicState(copy, row, col+1));
			}else if(col==board.length-2 && isValidRow(copy, row)) {
				nextStates.add(new HeuristicState(copy, row+1, 0));
			}
		}
	}

	public int[][] getSolution() {
		return solution;
	}

	private int[][] copyBoard(int[][] b) {
		int [][] copy = new int [b.length][b.length];
		for(int i =0;i<board.length;i++) {
			for(int j = 0;j<board.length;j++) {
				copy[i][j] = b[i][j];
			}
		}
		return copy;
	}

	public boolean isValidRow(int[][] board, int row) {
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
	
	public boolean isValidCol(int[][] board, int col) {
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

	public void printMatrix(int[][] board) {
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

//	private boolean heuristic(int row, int col) {
//		if(board[row][board.length-1] <= 0) {
//			for(int i = 0;i<col;i++) {
//				if(board[row][i] > 3)
//					return false;
//			}
//		}
//		if(board[board.length-1][col] <= 0) {
//			for(int i = 0;i<row;i++) {
//				if(board[i][col] > 3)
//					return false;
//			}
//		}
//		return true;
//	}

}
