package src;

public class Vector5 {
	static int[]v;
	
	public static void main(String arg []) {
		int repetitions = Integer.parseInt(arg[0]);
		long t1,t2;
		
		for (int n=10000; n<=Integer.MAX_VALUE; n*=2){
			  v = new int[n];
			  Vector1.fillIn(v);
			  
			  t1 = System.currentTimeMillis();
			  
			  for (int repetition=1; repetition<=repetitions; repetition++){    	
			     Vector1.maximum(v, v);
			  }
			  t2 = System.currentTimeMillis();
			  System.out.printf("SIZE=%d TIME=%d milliseconds NTIMES=%d\n", n, t2-t1, repetitions);	
		}//for 
		
	}//main
}
