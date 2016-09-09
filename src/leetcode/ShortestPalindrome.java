package leetcode;

/**
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of
 * it. Find and return the shortest palindrome you can find by performing this transformation.
 * 
 * For example:
 * 
 * Given "aacecaaa", return "aaacecaaa".
 * 
 * Given "abcd", return "dcbabcd".
 */

public class ShortestPalindrome {
	public String shortestPalindrome(String s) {
		int i = 0, end = s.length() - 1, j = end;
		char chs[] = s.toCharArray(); // for better performance
		while (i < j) {
			if (chs[i] == chs[j]) {
				i++;
				j--;
			} else {
				i = 0;
				end--;
				j = end;
			}
		}
		return new StringBuilder(s.substring(end + 1)).reverse().toString() + s;
	}

	/**
	 * A KMP based solution.
	 */
	public String shortestPalindrome2(String s) {
		if (s.length() <= 1)
			return s;
		String new_s = s + "#" + new StringBuilder(s).reverse().toString();
		int[] position = new int[new_s.length()];

		for (int i = 1; i < position.length; i++) {
			int pre_pos = position[i - 1];
			while (pre_pos > 0 && new_s.charAt(pre_pos) != new_s.charAt(i))
				pre_pos = position[pre_pos - 1];
			position[i] = pre_pos + ((new_s.charAt(pre_pos) == new_s.charAt(i)) ? 1 : 0);
		}

		return new StringBuilder(s.substring(position[position.length - 1])).reverse().toString() + s;
	}
}
