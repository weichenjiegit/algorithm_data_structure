package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * 
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a
 * non-empty word in str.
 * 
 * Examples:
 * 
 * <pre>
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false.
 * pattern = "abba", str = "dog dog dog dog" should return false.
 * </pre>
 * 
 * Notes: You may assume pattern contains only lowercase letters, and str contains lowercase letters
 * separated by a single space.
 */
public class WordPattern {
	public boolean wordPattern(String pattern, String str) {
		String[] arr = str.split(" ");
		Map<Character, String> map = new HashMap<>();
		if (arr.length != pattern.length())
			return false;
		for (int i = 0; i < arr.length; i++) {
			char c = pattern.charAt(i);
			if (map.containsKey(c)) {
				if (!map.get(c).equals(arr[i]))
					return false;
			} else {
				if (map.containsValue(arr[i]))
					return false;
				map.put(c, arr[i]);
			}
		}
		return true;
	}
}
