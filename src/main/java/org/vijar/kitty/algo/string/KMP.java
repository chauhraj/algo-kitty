package org.vijar.kitty.algo.string;

public class KMP {
	
	private int[] longestPrefixTbl;
	
	private char[] patternArray;

	private int[] match ;
	public KMP(String pattern) {
		patternArray = pattern.toCharArray();
		longestPrefixTbl = new int[patternArray.length];
		match = new int[longestPrefixTbl.length];
		
		longestPrefixTbl[0] = -1;
		match[0] = 1;
		int k = -1;
		for(int q = 1; q < patternArray.length; q++) {
			while(k > -1 && patternArray[k + 1] != patternArray[q]) {
				k = longestPrefixTbl[k];
			}
			if(patternArray[k+1] == patternArray[q]) {
				k = k + 1;
			}
			longestPrefixTbl[q] = k;
			match[q] = 1;
		}
	}
	
	public void printLongestPrefixTbl() {
		for(char c : patternArray) {
			System.out.printf(" %c |", c);
		}
		System.out.println();
		for(int i = 1; i <= 3 * patternArray.length; i++) {
			System.out.printf("-");
		}
		System.out.println();
		for(int c : longestPrefixTbl) {
			System.out.printf(" %2d|", c);
		}
		System.out.println();
	}
	
	public int countMatchesInText(String text, int patLength) {
		int match = 0;
		int q = -1;
		char[] textArray = text.toCharArray();
		for(int i = 0; i < textArray.length; i++) {
			while(q > -1 && patternArray[q + 1] != textArray[i]) {
				q = longestPrefixTbl[q];
			}
			if(patternArray[q + 1] == textArray[i]) {
				q = q + 1;
			}
			if(q == patLength - 1) {
				// found the pattern match
				match++;
				q = longestPrefixTbl[q];
			}
		}
		return match;
	}
}
