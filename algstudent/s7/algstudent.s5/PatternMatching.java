package algstudent.s5;

public class PatternMatching {
	
	private String text;
	private boolean letters[][];
	private String pattern;


	public PatternMatching(String text) {
		this.text = text;
	}

	public boolean checkPattern(String pattern) {
		this.pattern=pattern;
		if(pattern.isEmpty()) {
			return false;
		}
		letters = new boolean[text.length()+1][pattern.length()+1];
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
		printsTable();
		return letters[text.length()][pattern.length()];
	}

	public void printsTable() {
		if (letters == null) 
			throw new IllegalStateException("Algorithm has not been run yet");
		System.out.println();
		System.out.print("      ");
		for (char c : pattern.toCharArray()) {
			System.out.print(c+" ");
		}
		System.out.println();
		System.out.println();
		for (int i = 0; i < letters.length; i++) {
			for (int j = 0; j < letters[i].length + 1; j++) {
				if (i == 0 && j == 0)
					System.out.print("    ");
				else if (j == 0)
					System.out.print(text.toCharArray()[i-1] + "   ");
				else
					System.out.print(letters[i][j-1] ? "T " : "F ");
			}
			System.out.println();
		}
	}

}
