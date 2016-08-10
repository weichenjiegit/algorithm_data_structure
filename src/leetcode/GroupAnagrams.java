package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings, return all groups of strings that are anagrams.
 * Note: All inputs will be in lower-case.
 */
public class GroupAnagrams {
	// New version
	public List<List<String>> groupAnagrams(String[] strs) {
		if (strs == null || strs.length == 0) {
			return new ArrayList<List<String>>();
		}
		Map<String, ArrayList<String>> anagramsByChars = new HashMap<String, ArrayList<String>>();
		for (String str : strs) {
			char[] chars = str.toCharArray();
			Arrays.sort(chars);
			String keyChars = String.valueOf(chars);
			if (!anagramsByChars.containsKey(keyChars)) {
				anagramsByChars.put(keyChars, new ArrayList<String>());
			}
			anagramsByChars.get(keyChars).add(str);
		}

		for (String key : anagramsByChars.keySet()) {
			Collections.sort(anagramsByChars.get(key));
		}
		return new ArrayList<List<String>>(anagramsByChars.values());
	}

	// Old version
	public ArrayList<String> anagrams2(String[] strs) {
		ArrayList<String> res = new ArrayList<>();
		if (strs.length < 1)
			return res;
		Arrays.sort(strs, comp);
		boolean next = false;// indicate the last anagram of a certain group
		for (int i = 0; i + 1 < strs.length; i++) {
			if (equal(strs[i], strs[i + 1])) {
				res.add(strs[i]);
				next = true;
			} else if (next) {
				res.add(strs[i]);
				next = false;
			}
		}
		if (next)
			res.add(strs[strs.length - 1]);
		return res;
	}

	private boolean equal(String s1, String s2) {
		char[] s1_char = s1.toCharArray();
		Arrays.sort(s1_char);
		char[] s2_char = s2.toCharArray();
		Arrays.sort(s2_char);
		if (s1_char.length != s2_char.length)
			return false;
		for (int i = 0; i < s1_char.length; i++) {
			if (s1_char[i] != s2_char[i])
				return false;
		}
		return true;
	}

	private static Comparator<String> comp = new Comparator<String>() {
		public int compare(String s1, String s2) {
			char[] s1_char = s1.toCharArray();
			Arrays.sort(s1_char);
			char[] s2_char = s2.toCharArray();
			Arrays.sort(s2_char);
			if (s1_char.length < s2_char.length)
				return -1;
			if (s1_char.length > s2_char.length)
				return 1;
			for (int i = 0; i < s1_char.length; i++) {
				if (s1_char[i] < s2_char[i])
					return -1;
				if (s1_char[i] > s2_char[i])
					return 1;
			}
			return 0;
		}
	};
}
