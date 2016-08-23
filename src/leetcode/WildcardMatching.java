package leetcode;

/**
 * '?' Matches any single character. '*' Matches any sequence of characters (including the empty
 * sequence).
 * 
 * The matching should cover the entire input string (not partial). The function prototype should
 * be: bool isMatch(const char *s, const char *p)
 * 
 * Some examples: isMatch("aa","a") ? false isMatch("aa","aa") ? true isMatch("aaa","aa") ? false
 * isMatch("aa", "*") ? true isMatch("aa", "a*") ? true isMatch("ab", "?*") ? true isMatch("aab",
 * "c*a*b") ? false
 */
public class WildcardMatching {

	public boolean isMatch(String s, String p) {
		boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
		match[s.length()][p.length()] = true;
		for (int i = p.length() - 1; i >= 0; i--) {
			if (p.charAt(i) != '*')
				break;
			else
				match[s.length()][i] = true;
		}
		for (int i = s.length() - 1; i >= 0; i--) {
			for (int j = p.length() - 1; j >= 0; j--) {
				if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
					match[i][j] = match[i + 1][j + 1];
				else if (p.charAt(j) == '*')
					match[i][j] = match[i + 1][j] || match[i][j + 1];
				else
					match[i][j] = false;
			}
		}
		return match[0][0];
	}

	boolean isMatch2(String s, String p) {
		int sPos = 0, pPos = 0, match = 0, starIdx = -1;
		while (sPos < s.length()) {
			// advancing both pointers
			if (pPos < p.length() && (p.charAt(pPos) == '?' || s.charAt(sPos) == p.charAt(pPos))) {
				sPos++;
				pPos++;
			}
			// * found, only advancing pattern pointer
			else if (pPos < p.length() && p.charAt(pPos) == '*') {
				starIdx = pPos;
				match = sPos;
				pPos++;
			}
			// last pattern pointer was *, advancing string pointer
			else if (starIdx != -1) {
				pPos = starIdx + 1;
				match++;
				sPos = match;
			}
			// current pattern pointer is not star, last patter pointer was not *
			// characters do not match
			else
				return false;
		}

		// check for remaining characters in pattern
		while (pPos < p.length() && p.charAt(pPos) == '*')
			pPos++;

		return pPos == p.length();
	}

	// Non-recursive solution. 764 ms
	public boolean isMatch3(String s, String p) {
		if (p.length() == 0)
			return s.length() == 0;
		int i = 0;
		int j = 0;
		int star = -1;
		int back = -1;
		while (i < s.length()) {
			if (j < p.length() && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))) {
				i++;
				j++;
			} else if (j < p.length() && p.charAt(j) == '*') {
				back = i;
				star = j;
				j++;
			} else {
				if (star != -1) {
					i = back + 1;
					j = star + 1;
					back = i;
				} else {
					return false;
				}
			}
		}
		while (j < p.length() && p.charAt(j) == '*')
			j++;
		return j == p.length();
	}
}
