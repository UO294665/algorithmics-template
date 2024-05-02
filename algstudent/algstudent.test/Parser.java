package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
	
	private static String filename ;
	private static int [][] board;
	private static char [][] operators;
	private static boolean [][] untouchables;
	
	public Parser(String filename) {
		Parser.filename=filename;
		Initialize();
	}
	
	public void Initialize() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			int size = Integer.parseInt(reader.readLine());
			board = new int [size+1][size+1];
			operators = new char[size*2][size];
			untouchables = new boolean [size+1][size+1];
			Init(reader);
			
		}catch(IOException e) {
			return;
		}
		
	}
	
	public int [][] getBoard(){
		return board;
	}
	
	public char[][] getOperators(){
		return operators;
	}
	
	public boolean[][] getUntouchables(){
		return untouchables;
	}

	private void Init(BufferedReader reader) throws IOException {
		int i = 0;
		int j = 0;
		int rowOp = 0;
		int pos = 1;
		int colNum = 0;
		String line = reader.readLine();
		while(line != null) {
			String [] r = line.split(" ");
			if(pos % 2 != 0) {
				for(String k : r) {
					if(!k.equals("?")) {
						try {
							int p = Integer.parseInt(k);
							board[i][colNum] = p;
							untouchables[i][colNum] = true;
							colNum++;
						}catch(Exception e) {
							operators[rowOp][j] = k.charAt(0);
							j++;
						}
					}else {
						board[i][colNum] = 1;
						colNum++;
					}
					if(k.equals("=")) {
						break;
					}
					
				}
				if(!(board.length-1 == i && board.length-1 == j))
					board[i][board.length-1] = Integer.parseInt(r[r.length-1]);
				i++;
			}else {
				for(String k : r) {
					operators[rowOp][j] = k.charAt(0);
					j++;
				}
			}
			rowOp++;
			pos++;
			j = 0;
			colNum = 0;
			line = reader.readLine();
		}
	}
	
}
