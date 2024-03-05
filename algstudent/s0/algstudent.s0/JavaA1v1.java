package pythonToJava;

import java.util.ArrayList;

public class JavaA1v1 {
	JavaA1v0 j=new JavaA1v0();
	
	public ArrayList<Integer> listadoPrimos(int n) {
	    ArrayList<Integer>primes = new ArrayList<Integer>();
	    for (int i=2 ; i<n+1; i++) {
	        if (j.primoA1(i))
	            primes.add(i);
	    }
	    return primes;
	}
}
