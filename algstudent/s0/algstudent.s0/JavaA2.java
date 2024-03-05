package pythonToJava;

import java.util.ArrayList;

public class JavaA2 {
	public static void main(String[] args) {
		
	    int n = 10000;
	    for (int casos=0; casos<7;casos++) {
	    	long t1 = System.currentTimeMillis();
	        listadoPrimos(n);
	        System.out.println("n ="+ n+ "***"+ "time ="+ Long.toString(System.currentTimeMillis()-t1)+ "milliseconds)");
	        n = n*2;
	    }
	}
	
	public static boolean primoA2(int m) {
		for (int i=2; i<m; i++) {
	        if (m%i==0)
	            return false;
		}
        return true;
	}
	
	public static ArrayList<Integer> listadoPrimos(int n) {
	    ArrayList<Integer>primes = new ArrayList<Integer>();
	    for (int i=2 ; i<n+1; i++) {
	        if (primoA2(i))
	            primes.add(i);
	    }
	    return primes;
	}

}
