package algstudent.s3;

public class Mergesort {
	public static void mergesort(int left, int right, int[] elements) {
		if (right > left){
			//Get the index of the element in the middle
			int center = (right + left) / 2;
			//Sort the left side of the array
			mergesort(left, center, elements);
			//Sort the right side of the array
			mergesort(center+1, right, elements);
			//Combine both parts
			combine(left, center, center+1, right, elements);
		}
	}
	
	private static void combine(int x1, int x2, int y1, int y2, int[] elements) {
		int sizeX = x2-x1+1;
		int sizeY = y2-y1+1;
		int [] x = new int[sizeX];
		int [] y = new int[sizeY];
		//Copy the elements from left to center into a helper
		for (int i = 0; i < sizeX; i++){
			x[i] = elements[x1+i];
		}
		//Copy the elements from center+1 to right into a helper
		for (int i = 0; i < sizeY; i++){
			y[i] = elements[y1+i];
		}
		
		int i = 0, j = 0, k = x1;
		 
		while (i < sizeX && j < sizeY) {
	        if (x[i] <= y[j]) {
	            elements[k] = x[i];
	            i++;
	        } else {
	            elements[k] = y[j];
	            j++;
	        }
	        k++;
	    }
		
	    while (i < sizeX) {
	        elements[k] = x[i];
	        i++;
	        k++;
	    }
	    
	    while (j < sizeY) {
	        elements[k] = y[j];
	        j++;
	        k++;
	    }
	}
}
