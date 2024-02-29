package alg_src;

public class QuicksortInsertion {

	static int[] v;

	/* Sorting by the Insertion method */
	public static void insertion(int[] a, int left, int right) {
		int j;
		int pivot;
		
		for (int i = left + 1; i <= right; i++) {
            pivot = a[i];
            j = i - 1;
            
            while (j >= left && a[j] > pivot) {
                a[j + 1] = a[j];
                j--;
            }
            
            a[j + 1] = pivot;
        }
	}
	
	public static void quicksort(int[] a, int left, int right, int k) {
		int i = left;
		int j = right - 1;
		int pivot;
		
		if (left < right){ //if there is one element it is not necessary
			int center = Quicksort.median_of_three(a, left, right);
			if((right-left) <= k)
				insertion(a, left, right);
			//if there are less than or equal to 3 elements, there are just ordered
			else if ((right - left) >= 3){ 
				pivot = a[center]; //choose the pivot
				Vector.interchange(a, center, right); //hide the pivot

				do {         
			    	while (a[i] <= pivot && i < right) i++; //first element > pivot
			    	while (a[j] >= pivot && j > left) j--; //first element < pivot
			        if (i < j) Vector.interchange(a, i, j);
			    } while (i < j);   //end while
				
				//we set the position of the pivot
				Vector.interchange(a, i, right);
				quicksort(a, left, i-1, k);
				quicksort(a, i+1, right, k);		
			}
		} //if
	}
	
	public static void quicksortInsertion(int[] a, int k) {
		quicksort(a, 0, a.length-1, k);
	}
	
	public static void main(String arg[]) {
		int n = Integer.parseInt(arg[0]); //size of the problem
		v = new int[n];

		Vector.sorted(v);
		System.out.println("VECTOR TO BE SORTED");
		Vector.print(v);
		quicksortInsertion(v, 100);
		System.out.println("SORTED VECTOR");
		Vector.print(v);

		Vector.reverseSorted(v);
		System.out.println("VECTOR TO BE SORTED");
		Vector.print(v);
		quicksortInsertion(v, 100);
		System.out.println("SORTED VECTOR");
		Vector.print(v);

		Vector.randomSorted(v);
		System.out.println("VECTOR TO BE SORTED");
		Vector.print(v);
		quicksortInsertion(v, 100);
		System.out.println("SORTED VECTOR");
		Vector.print(v);
	} 
}
