package src;

public class Main {
	private static String filename = "C:/Users/gonza/Desktop/UNI/Algoritmia/LAB08/LAB08/Files/test07.txt";


	public static void main(String args[]) {
		long t1, t2;
		Parser p = new Parser(filename);
		NumericSquareOne n = new NumericSquareOne(p.getBoard(), p.getOperators(), p.getUntouchables());
		
		t1 = System.currentTimeMillis();
		n.backtracking();
		t2 = System.currentTimeMillis();
		
		System.out.println("For one solution: \n");
		System.out.println("Is solved? "+n.isSolved());
		n.printMatrix();
		System.out.println("Time: " + (t2 - t1)+"ms");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		NumericSquareAll n2 = new NumericSquareAll(p.getBoard(), p.getOperators(), p.getUntouchables());
		
		t1 = System.currentTimeMillis();
		n2.backtracking();
		t2 = System.currentTimeMillis();
		
		System.out.println("For all the solutions: \n");
		System.out.println("Is solved? "+n2.isSolved());
		System.out.println("Number of solutions: "+n2.getSolutions());
		System.out.println("Time: " + (t2 - t1)+"ms");
	}
}
