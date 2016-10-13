package leetcode;

/**
 * Given an input string, reverse the string word by word. A word is defined as a sequence of
 * non-space characters.
 * 
 * The input string does not contain leading or trailing spaces and the words are always separated
 * by a single space.
 * 
 * For example,
 * 
 * Given s = "the sky is blue",
 * 
 * return "blue is sky the".
 * 
 * Could you do it in-place without allocating extra space?
 */
public class ReverseWordsInAStringII {
	public void reverseWords(char[] s) {
		reverseWords(s, 0, s.length - 1);
		for (int i = 0, j = 0; i <= s.length; i++) {
			if (i == s.length || s[i] == ' ') {
				reverseWords(s, j, i - 1);
				j = i + 1;
			}
		}
	}

	private void reverseWords(char[] s, int begin, int end) {
		while (begin < end) {
			char c = s[begin];
			s[begin] = s[end];
			s[end] = c;
			begin++;
			end--;
		}
	}
}
