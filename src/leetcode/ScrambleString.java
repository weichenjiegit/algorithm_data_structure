package leetcode;

import java.util.Arrays;

/**
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty
 * substrings recursively. Below is one possible representation of s1 = "great":
 * 
 * <pre>
 *  great
 * /    \
 * gr    eat
 * / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 * </pre>
 * 
 * To scramble the string, we may choose any non-leaf node and swap its two children. For example,
 * if we choose the node "gr" and swap its two children, it produces a scrambled string"rgeat".
 * 
 * <pre>
 * rgeat
 * /    \
 * rg    eat
 * / \    /  \
 * r   g  e   at
 *           / \
 *           a   t
 * </pre>
 * 
 * We say that "rgeat" is a scrambled string of "great". Similarly, if we continue to swap the
 * children of nodes "eat" and"at", it produces a scrambled string"rgtae".
 * 
 * <pre>
 * rgtae
 * /    \
 * rg    tae
 * / \    /  \
 * r   g  ta  e
 *        / \
 *        t   a
 * </pre>
 * 
 * We say that "rgtae" is a scrambled string of "great". Given two strings s1 and s2 of the same
 * length, determine if s2 is a scrambled string ofs1.
 */
public class ScrambleString {
	// Brute force. Use sorting to prune.
	public boolean isScramble(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;
		if (s1.equals(s2))
			return true;
		char[] s1_char = s1.toCharArray();
		char[] s2_char = s2.toCharArray();
		Arrays.sort(s1_char);
		Arrays.sort(s2_char);
		if (!new String(s1_char).equals(new String(s2_char)))
			return false;
		for (int i = 1; i < s1.length(); i++) {
			String sub1a = s1.substring(0, i);
			String sub1b = s1.substring(i);
			String sub2a = s2.substring(0, i);
			String sub2b = s2.substring(i);
			if (isScramble(sub1a, sub2a) && isScramble(sub1b, sub2b))
				return true;
			sub2a = s2.substring(s2.length() - i);
			sub2b = s2.substring(0, s2.length() - i);
			if (isScramble(sub1a, sub2a) && isScramble(sub1b, sub2b))
				return true;
		}
		return false;
	}

	/**
	 * 3D-DP.
	 * 
	 * dp[i][j][k] means s1 starts at i, s2 starts at j, with length k, both substrings are scramble
	 * string
	 * 
	 * <pre>
	 * Three situations to consider
	 * 1. If two substrings are equal, then true
	 * 2.
	 * If there is one point in the substring
	 * that left part is a scramble string and the right part is a scramble string
	 * then true.
	 * 3.
	 * If there is one point in the substring,
	 * left part of s1 and right part of s2 are scramble string,
	 * and right part of s1 and left part of s2 are scramble string
	 * then true.
	 * </pre>
	 */
	public boolean isScramble2(String s1, String s2) {
		int n = s1.length();
		boolean[][][] dp = new boolean[n][n][n + 1];
		for (int i = n - 1; i >= 0; i--)
			for (int j = n - 1; j >= 0; j--)
				for (int k = 1; k <= n - Math.max(i, j); k++) {
					if (s1.substring(i, i + k).equals(s2.substring(j, j + k)))
						dp[i][j][k] = true;
					else {
						for (int l = 1; l < k; l++) {
							if (dp[i][j][l] && dp[i + l][j + l][k - l] || dp[i][j + k - l][l] && dp[i + l][j][k - l]) {
								dp[i][j][k] = true;
								break;
							}
						}
					}
				}
		return dp[0][0][n];
	}
}
