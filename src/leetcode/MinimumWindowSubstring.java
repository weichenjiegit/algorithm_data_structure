package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the
 * characters in T in complexity O(n). For example, S = "ADOBECODEBANC" T = "ABC" Minimum window is
 * "BANC". Note: If there is no such window in S that covers all characters in T, return the emtpy
 * string"". If there are multiple such windows, you are guaranteed that there will always be only
 * one unique minimum window in S.
 */
public class MinimumWindowSubstring {
	public String minWindow(String s, String t) {
		Map<Character, Integer> countByCharInT = new HashMap<>();
		Map<Character, Integer> currentCountByChar = new HashMap<>();
		for (int i = 0; i < t.length(); i++) {
			char cur = t.charAt(i);
			currentCountByChar.put(cur, 0);
			if (!countByCharInT.containsKey(cur))
				countByCharInT.put(cur, 1);
			else {
				countByCharInT.put(cur, countByCharInT.get(cur) + 1);
			}
		}
		int begin = 0, end = 0, minWin = Integer.MAX_VALUE;
		int minBeg = 0, minEnd = 0;
		int counter = 0;
		while (end < s.length()) {
			char curChar = s.charAt(end);
			if (!countByCharInT.containsKey(curChar)) {
				end++;
				continue;
			}
			if (currentCountByChar.get(curChar) < countByCharInT.get(curChar))
				counter++;
			currentCountByChar.put(curChar, currentCountByChar.get(curChar) + 1);
			if (counter == t.length()) {
				char begChar = s.charAt(begin);
				while (!currentCountByChar.containsKey(begChar)
				        || currentCountByChar.get(begChar) > countByCharInT.get(begChar)) {
					if (currentCountByChar.containsKey(begChar)) {
						currentCountByChar.put(begChar, currentCountByChar.get(begChar) - 1);
					}
					begin++;
					begChar = s.charAt(begin);
				}
				int len = end - begin + 1;
				if (len < minWin) {
					minWin = len;
					minBeg = begin;
					minEnd = end;
				}
			}
			end++;
		}
		return minWin <= s.length() ? s.substring(minBeg, minEnd + 1) : "";
	}

	public static void main(String[] args) {
		MinimumWindowSubstring test = new MinimumWindowSubstring();
		System.out.println(test.minWindow("ab", "b"));
	}
}