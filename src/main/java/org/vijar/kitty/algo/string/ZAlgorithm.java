package org.vijar.kitty.algo.string;

public class ZAlgorithm {

	private int[] zValue;
	private char[] text;
	
	private int right;
	private int left;

	public ZAlgorithm(String text) {
		right = left = 0;
		computeZValues(text.toCharArray());
	}
	
	private void computeZValues(char[] text) {
		this.text = text;
		zValue = new int[text.length];
		zValue[0] = text.length;
		
		if(zValue.length == 1)
			return ;
		
		int prefixStartIndex = 0;
		zValue[1] = explicitMatchFromGivenIndex(prefixStartIndex, 1);
		if(zValue[1] > 0) {
			right = zValue[1];
			left  = 1;
		}
		for(int k = 2; k < text.length; k++) {
			if (k > right) {
				zValue[k] = explicitMatchFromGivenIndex(0, k);
				if(zValue[k] > 0) {
					right = (k + zValue[k] - 1);
					left = k ;
				}
			} else if (k <= right) {
				int beta = right - k + 1;
				int kPrime = (k - left);
				if (zValue[kPrime] < beta) {
					zValue[k] = zValue[kPrime];
				} else if (zValue[kPrime] > beta) {
					zValue[k] = beta;
				} else {
					int nMatches ;
					zValue[k] = beta + (nMatches = explicitMatchFromGivenIndex(beta + 1, right + 1));
					if(zValue[k] > 0) {
						right = (beta + nMatches);
					}
				}			
			} 
		}
	}

	private int explicitMatchFromGivenIndex(int prefixStart, int index) {
		int matches = 0;
		for(int i = index; i < text.length; i++) {
			if(text[prefixStart++] == text[index++]) {
				matches++;
			} else {
				break;
			}
		}
		return matches;
	}

	public int[] getZValue() {
		return zValue;
	}
}
