package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring T that contains at most 2 distinct
 * characters.
 * 
 * For example, Given s = “eceba”,
 * 
 * T is "ece" which its length is 3
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {
	// As in map, there will always be only 2 entries. So using local variables is also fine.
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		if (s.length() < 1)
			return 0;
		Map<Character, Integer> index = new HashMap<>();
		int left = 0;
		int right = 0;
		int maxLength = 0;
		while (right < s.length()) {
			if (index.size() <= 2) {
				char c = s.charAt(right);
				index.put(c, right);
				right++;
			}
			if (index.size() > 2) {
				int leftMost = s.length();
				for (int i : index.values()) {
					leftMost = Math.min(leftMost, i);
				}
				char c = s.charAt(leftMost);
				index.remove(c);
				left = leftMost + 1;
			}
			maxLength = Math.max(maxLength, right - left);
		}
		return maxLength;
	}
}
