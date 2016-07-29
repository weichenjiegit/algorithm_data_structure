package leetcode;

public class LongestPalindromicSubstring {

	// Solution 1
	// Expand around center
	// Time O(n^2)
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
		while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
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
		return s.substring((res_center - max_radius) / 2, (res_center + max_radius) / 2);
	}

	// Solution 3
	// Dynamic programming
	// P(i, j) = true if substring(i, j + 1) is a palindrome
	public String longestPalindrome3(String s) {
		int start = 0, end = 0, len = s.length();
		boolean buffer[][] = new boolean[len][len];
		for (int i = 0; i < len; i++) { // initialize
			buffer[i][i] = true;
			if (i < len - 1 && s.charAt(i) == s.charAt(i + 1)) {
				buffer[i][i + 1] = true;
				start = i;
				end = i + 1;
			}
		}
		for (int i = len - 1; i >= 0; i--) {
			for (int j = i + 2; j < len; j++) {
				if (buffer[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
					buffer[i][j] = true;
					if (j - i > end - start) {
						start = i;
						end = j;
					}
				}
			}
		}
		return s.substring(start, end + 1);
	}

	// Solution 4 for subsequence (not contiguous substring)
	// Dynamic programming
	// P(i, j) is the length of contained longest palindromic subsequence of
	// substring(i, j + 1)
	// if (s[i] != s[j]) P(i, j) = max{P(i + 1, j),P(i, j - 1)}
	// else if (j == i + 1) P(i, j) = 2 // if only 2 characters and both are
	// same
	// else P(i, j) = P(i + 1, j - 1) + 2 // if more than two characters, and
	// first and last characters are same
	public int longestPalindromeSubsequence(String s) {
		int n = s.length();
		int i, j, cl;
		int L[][] = new int[n][n];

		for (i = 0; i < n; i++)
			L[i][i] = 1;

		// Updating the table from short length of substring to longer one
		for (cl = 2; cl <= n; cl++) {
			for (i = 0; i < n - cl + 1; i++) {
				j = i + cl - 1;
				if (s.charAt(i) == s.charAt(j) && cl == 2)
					L[i][j] = 2;
				else if (s.charAt(i) == s.charAt(j))
					L[i][j] = L[i + 1][j - 1] + 2;
				else
					L[i][j] = Math.max(L[i][j - 1], L[i + 1][j]);
			}
		}

		return L[0][n - 1];
	}
}
