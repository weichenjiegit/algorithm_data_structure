package leetcode;

/**
 * Find the length of the longest substring T of a given string (consists of lowercase letters only)
 * such that every character in T appears no less than k times.
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
	public int longestSubstring(String s, int k) {
		char[] str = s.toCharArray();
		return helper(str, 0, s.length(), k);
	}

	private int helper(char[] str, int start, int end, int k) {
		if (end < start)
			return 0;
		if (end - start < k)
			return 0;// substring length shorter than k.
		int[] count = new int[26];
		for (int i = start; i < end; i++) {
			int idx = str[i] - 'a';
			count[idx]++;
		}
		for (int i = 0; i < 26; i++) {
			if (count[i] == 0)
				continue;// i+'a' does not exist in the string, skip it.
			if (count[i] < k) { // character i + 'a' is going to get ignored
				for (int j = start; j < end; j++) {
					if (str[j] == i + 'a') {
						int left = helper(str, start, j, k);
						int right = helper(str, j + 1, end, k);
						return Math.max(left, right);
					}
				}
			}
		}
		return end - start;
	}
}
