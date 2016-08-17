package leetcode;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * 
 * For example, Given: s1 = "aabcc", s2 = "dbbca",
 * 
 * When s3 = "aadbbcbcac", return true. When s3 = "aadbbbaccc", return false.
 */
public class InterleavingString {
	public boolean isInterleave(String s1, String s2, String s3) {
		int n1 = s1.length();
		int n2 = s2.length();
		int n3 = s3.length();
		if (n1 + n2 != n3)
			return false;
		boolean[][] ans = new boolean[n1 + 1][n2 + 1];
		// ans[i][j] == true means s3.substring(0, i + j) is an interleaving
		// string
		ans[0][0] = true;
		for (int i = 1; i <= n1; i++) {
			if (s1.charAt(i - 1) == s3.charAt(i - 1))
				ans[i][0] = true;
			else
				break;
		}
		for (int i = 1; i <= n2; i++) {
			if (s2.charAt(i - 1) == s3.charAt(i - 1))
				ans[0][i] = true;
			else
				break;
		}
		for (int i = 1; i <= n1; i++) {
			for (int j = 1; j <= n2; j++) {
				if (s1.charAt(i - 1) == s3.charAt(i + j - 1))
					ans[i][j] = ans[i][j] || ans[i - 1][j];
				if (s2.charAt(j - 1) == s3.charAt(i + j - 1))
					ans[i][j] = ans[i][j] || ans[i][j - 1];
			}
		}
		return ans[n1][n2];
	}
}
