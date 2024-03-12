package algstudent.s3;

import java.util.Random;

public class MergesortTimes {

    public static void main(String[] args) {
    	for (int n = 250000; n <= 1000000000; n *= 2) {
    		
			int size = n;
			int [] arr = new int [size];
			int option = Integer.parseInt(args[0]);
			long t1,t2;
			
			if (option==1) {
				fillOrdered(arr);
			}    
			else if (option==2) {
				fillReverse(arr);
			}
			else if (option==3) {
				fillRandom(arr);
			}
			else System.out.println("INCORRECT OPTION");
			
		    t1= System.currentTimeMillis();
			  
	        Mergesort.mergesort(0, arr.length-1, arr);
		
		    t2= System.currentTimeMillis();
			System.out.println ("ORDER = "+size+"**"+"TIME = "+(t2-t1)+"**"); 
    	}
    }
    
    
    public static void fillOrdered(int[]a) {
   	 int n=a.length;
   	 for(int i=0;i<n;i++)
   	  a[i]= i;
   	}   
    
    public static void fillReverse(int[]a) {
      	 int n=a.length-1;
      	 for(int i=n;i>=0;i--)
      	  a[n-i]= i;
    }
    
    public static void fillRandom(int[]a) {
   	 Random r= new Random();
   	 int n=a.length;
   	 for(int i=0;i<n;i++)
   	  a[i]= r.nextInt(19)-9;
   	} 
}
