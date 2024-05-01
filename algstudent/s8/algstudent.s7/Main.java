package src;

public class Main {
	private static String filename = "C:/Users/gonza/Desktop/UNI/Algoritmia/LAB08/LAB08/Files/test07.txt";


	public static void main(String args[]) {
		long t1, t2;
		Parser p = new Parser(filename);
		NumericSquareBaB n = new NumericSquareBaB(p.getBoard(), p.getOperators(), p.getUntouchables());
		
		t1 = System.currentTimeMillis();
		n.backtracking();
		t2 = System.currentTimeMillis();
		
		System.out.println("For one solution: \n");
		System.out.println("Is solved? "+n.isSolved());
		n.printMatrix(n.getSolution());
		System.out.println("Time: " + (t2 - t1)+"ms");
		System.out.println("Number of developed nodes: "+n.getNumberOfDevelopedNodes());
	}
}
