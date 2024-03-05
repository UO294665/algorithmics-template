package pythonToJava;

public class JavaA1 {
	
	private static JavaA1v1 j=new JavaA1v1();
	
	public static void main(String[] args) {
		
	    int n = 10000;
	    for (int casos=0; casos<7;casos++) {
	    	long t1 = System.currentTimeMillis();
	        j.listadoPrimos(n);
	        System.out.println("n ="+ n+ "***"+ "time ="+ Long.toString(System.currentTimeMillis()-t1)+ "milliseconds)");
	        n = n*2;
	    }
	}


}
