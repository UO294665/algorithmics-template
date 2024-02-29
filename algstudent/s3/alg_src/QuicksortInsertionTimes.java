package alg_src;

public class QuicksortInsertionTimes {
	static int[] v;
	static int[] n = {0, 5, 10, 20, 30, 50, 100, 200, 500, 1000};
	public static void main(String arg[]) {
		for(int i = 0; i<n.length;i++) {
			long t1, t2;
			String opcion = arg[0];
			int num=16000000;
			v = new int[num];
	
			if (opcion.compareTo("ordered") == 0)
				Vector.sorted(v);
			else if (opcion.compareTo("reverse") == 0)
				Vector.reverseSorted(v);
			else
				Vector.randomSorted(v);
	
			t1 = System.currentTimeMillis();
	
			QuicksortInsertion.quicksortInsertion(v, n[i]);
	
			t2 = System.currentTimeMillis();
	
			System.out.println(num + "\t" + (t2 - t1) + "\t" + n[i]);
		}
	}
}
