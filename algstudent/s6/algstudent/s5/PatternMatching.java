package algstudent.s5;

public class PatternMatching {
	
	private String text;

	public PatternMatching(String text) {
		this.text = text;
	}

	public boolean checkPattern(String pattern) {
		if(pattern.isEmpty()) {
			return false;
		}
		boolean [][] letters = new boolean[text.length()+1][pattern.length()+1];
		letters[0][0]=true;
		boolean [] result = new boolean [text.length()];
		char[] patternChar = pattern.toCharArray();
		char[] textChar = text.toCharArray();
		
		for(int i=1 ; i<letters.length;i++) {
			for(int j = 1;j<letters[i].length;j++) {
				if(patternChar[j-1]==textChar[i-1]) {
					
					if(letters[i-1][j-1]) {
						letters[i][j] = true;
						result[i-1]=true;
					}
				}
				else if(patternChar[j-1]=='?') {
					if(letters[i-1][j-1] || letters[i][j-1]) {
						letters[i][j]=true;
						result[i-1]=true;
					}
				}
				else if(patternChar[j-1]=='*') {
					if(letters[i-1][j-1] || letters[i][j-1] || letters[i-1][j]) {
						letters[i][j]=true;
						result[i-1]=true;
					}
				}
			}
		}
//		for(int i=1 ; i<letters.length;i++) {
//			for(int j = 1;j<letters[i].length;j++) {
//				System.out.print(letters[i][j]+" ");
//			}
//			System.out.println();
//		}
		for(int i=1 ; i<letters[0].length;i++) {
			boolean aux = true;
			for(int j = 1;j<letters.length;j++) {
				if(letters[j][i]) {
					aux = false;
					break;
				}
			}
			if(aux) {
				return false;
			}
		}
		return true;
	}

	public void printsTable() {
		// TODO Auto-generated method stub
		
	}

}
