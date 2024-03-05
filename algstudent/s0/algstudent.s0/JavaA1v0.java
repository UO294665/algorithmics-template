package pythonToJava;

public class JavaA1v0 {

	public boolean primoA1(int m) {
	    boolean p = true;
	    for (int i=2;i<m;i++) {
	        if (m%i == 0)
	            p = false;
	    }
	    return p;
	}
}
