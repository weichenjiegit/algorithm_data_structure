package leetcode;

/**
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring.
 */
public class LongestPalindromeSubstring {
	// Solution 1
	public String longestPalindrome(String s) {
		int len = s.length();
		if (len == 0)
			return "";
		String longest = s.substring(0, 1);
		for (int i = 0; i < len - 1; i++) {
			String temp = expandAroundCenter(s, i, i);
			if (temp.length() > longest.length())
				longest = temp;
			temp = expandAroundCenter(s, i, i + 1);
			if (temp.length() > longest.length())
				longest = temp;
		}
		return longest;
	}

	public String expandAroundCenter(String s, int left, int right) {
		int len = s.length();
		while (left >= 0 && right <= len - 1
				&& s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		return s.substring(left + 1, right);
	}

	// Solution 2
	// O(n) Manacher Algorithm
	public String longestPalindrome2(String s) {
		int len = s.length();
		StringBuilder sb = new StringBuilder("#");
		for (int i = 0; i < len; i++) {
			sb.append(s.charAt(i));
			sb.append("#");
		}
		String new_s = sb.toString();
		len = new_s.length();
		int[] P = new int[len];
		int max_radius = 1;
		int res_center = 1;
		int C = 1, R = 1;
		for (int i = 1; i < len - 1; i++) {
			int i_mirror = 2 * C - i;// i_mirror = C - (i - C)
			P[i] = (R > i) ? Math.min(R - i, P[i_mirror]) : 0;

			// expand paindrome centered at i if valid
			if ((i - 1 - P[i]) >= 0 && (i + 1 + P[i]) < len) {
				while (new_s.charAt(i + 1 + P[i]) == new_s.charAt(i - 1 - P[i])) {
					P[i]++;
					if ((i - 1 - P[i]) < 0 || (i + 1 + P[i]) >= len)
						break;
				}
			}
			// if palindrome centered at i expand past R
			// adjust center based on expanded palindrome
			if (P[i] > max_radius) {
				max_radius = P[i];
				res_center = i;
			}
			if (i + P[i] > R) {
				C = i;
				R = i + P[i];
			}
		}
		return s.substring((res_center - max_radius) / 2,
				(res_center + max_radius) / 2);
	}
}
