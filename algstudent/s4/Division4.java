package algstudent.s3;

public class Division4 {
	public static long rec4 (int n)
	{
     long cont = 0;
	 for(int i =0;i<n;i++)
		 for(int j =0;j<n;j++) cont++;
	 if (n<=0) cont++;
	 else
	  { cont++ ;
		  rec4(n/2);
	  }
	 return cont;   
	}
	
	public static void main (String arg []) 
	{
		 long t1,t2,cont = 0;
		 int nTimes = Integer.parseInt (arg [0]);
		 for (int n=1000;n<=Integer.MAX_VALUE;n*=2)
		 {
			  t1 = System.currentTimeMillis ();
			   
			  for (int reps=1; reps<=nTimes;reps++)
				{ 
					cont = rec4(n);
				} 	
			      
			  t2 = System.currentTimeMillis ();
			
			  System.out.println ("n="+n+ "**TIME="+(t2-t1)+"**cont="+cont);	
		 }  // for
	} // main
}
