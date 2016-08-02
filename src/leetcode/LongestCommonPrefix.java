package leetcode;

/*
 * Write a function to find the longest common prefix string
 * amongst an array of strings.
 */
public class LongestCommonPrefix {
	// Solution 1, vertical character scan
	// O(s), s is the sum of all characters in all strings.
	public String longestCommonPrefix1(String[] strs) {
		if (strs.length == 0)
			return "";
		for (int i = 0; i < strs[0].length(); i++) {
			for (int j = 0; j < strs.length; j++) {
				if (i >= strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)) {
					return strs[0].substring(0, i);
				}
			}
		}
		return strs[0];
	}

	// Solution 2, divide and conquer
	// Time O(m * log(n)), where we have n equal strings with length m in worst
	// case.
	// Space O(m * log(n)), since using recursive call with depth of log(n) and
	// each level worst case m length.
	public String longestCommonPrefix2(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		return longestCommonPrefix(strs, 0, strs.length - 1);
	}

	private String longestCommonPrefix(String[] strs, int l, int r) {
		if (l == r) {
			return strs[l];
		} else {
			int mid = (l + r) / 2;
			String lcpLeft = longestCommonPrefix(strs, l, mid);
			String lcpRight = longestCommonPrefix(strs, mid + 1, r);
			return commonPrefix(lcpLeft, lcpRight);
		}
	}

	private String commonPrefix(String left, String right) {
		int min = Math.min(left.length(), right.length());
		for (int i = 0; i < min; i++) {
			if (left.charAt(i) != right.charAt(i))
				return left.substring(0, i);
		}
		return left.substring(0, min);
	}

	// Solution 3, binary search
	// Time O(S * log(n)), where s is the sum of all the characters in all
	// strings and we have n equal strings with
	// length m in worst case, s = m * n.
	public String longestCommonPrefix3(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		int minLen = Integer.MAX_VALUE;
		for (String str : strs)
			minLen = Math.min(minLen, str.length());
		int low = 1;
		int high = minLen;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (isCommonPrefix(strs, middle))
				low = middle + 1;
			else
				high = middle - 1;
		}
		return strs[0].substring(0, (low + high) / 2);
	}

	private boolean isCommonPrefix(String[] strs, int len) {
		String str1 = strs[0].substring(0, len);
		for (int i = 1; i < strs.length; i++)
			if (!strs[i].startsWith(str1))
				return false;
		return true;
	}
}
