package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * 
 * Return all possible palindrome partitioning of s. For example, given s = "aab", Return
 * 
 * [ ["aa","b"], ["a","a","b"] ]
 * 
 */
public class PalindromePartition {
	// Solution 1
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();
		List<String> partitions = new ArrayList<>();
		partitioning(res, partitions, s);
		return res;
	}

	public void partitioning(List<List<String>> res, List<String> partitions, String s) {
		int n = s.length();
		if (n == 0) {
			res.add(new ArrayList<>(partitions));
			return;
		}
		for (int i = 0; i < n; i++) {
			String begin = s.substring(0, i + 1);
			if (isPalindrome(begin)) {
				partitions.add(begin);
				partitioning(res, partitions, s.substring(i + 1));
				partitions.remove(partitions.size() - 1);
			}
		}
	}

	public boolean isPalindrome(String s) {
		if (s.length() <= 1)
			return true;
		int begin = 0;
		int end = s.length() - 1;
		while (begin < end) {
			if (s.charAt(begin) == s.charAt(end)) {
				begin++;
				end--;
			} else
				return false;
		}
		return true;
	}

	// Solution 2
	// DP dp[i][j] means s.substring(i, j + 1) is a palindrome string
	public List<List<String>> partition2(String s) {
		List<List<String>> res = new ArrayList<>();
		boolean[][] dp = new boolean[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j <= i; j++) {
				if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
					dp[j][i] = true;
				}
			}
		}
		helper(res, new ArrayList<>(), dp, s, 0);
		return res;
	}

	private void helper(List<List<String>> res, List<String> path, boolean[][] dp, String s, int pos) {
		if (pos == s.length()) {
			res.add(new ArrayList<>(path));
			return;
		}

		for (int i = pos; i < s.length(); i++) {
			if (dp[pos][i]) {
				path.add(s.substring(pos, i + 1));
				helper(res, path, dp, s, i + 1);
				path.remove(path.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		PalindromePartition obj = new PalindromePartition();
		obj.partition("a");
	}
}
