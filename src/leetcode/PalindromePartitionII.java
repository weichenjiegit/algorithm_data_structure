package leetcode;

/**
 * Given a string s,
 * partition s such that every substring of the partition is a palindrome.
 * 
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1
 * since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class PalindromePartitionII {
	public static int minCut(String s) {
		int len = s.length();
		int[] res = new int[len + 1];
		// res[i] means the min cut in [i，n]
		// res[i] = min(1 + res[j + 1], res[i]), i <= j < len
		boolean[][] isPal = new boolean[len][len];
		// isPal[i][j] = true, if [i,j] is a palindrome
		// isPal[i][j] = (s[i] == s[j] && isPal[i+1][j-1])
		for (int i = 0; i <= len; i++)
			res[i] = len - i;
		for (int i = len - 1; i >= 0; i--) {
			for (int j = i; j < len; j++) {
				if (s.charAt(i) == s.charAt(j)
						&& (j - i < 2 || isPal[i + 1][j - 1])) {
					isPal[i][j] = true;
					res[i] = Math.min(res[i], res[j + 1] + 1);
				}
			}
		}
		return res[0] - 1;
	}
	public static void main(String[] args){
		minCut("aab");
	}
}
