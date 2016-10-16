package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * An abbreviation of a word follows the form <first letter><number><last letter>. Below are some
 * examples of word abbreviations:
 * 
 * <pre>
 * a) it                      --> it    (no abbreviation)
 * 
 *      1
 * b) d|o|g                   --> d1g
 * 
 *               1    1  1
 *      1---5----0----5--8
 * c) i|nternationalizatio|n  --> i18n
 * 
 *               1
 *      1---5----0
 * d) l|ocalizatio|n          --> l10n
 * 
 * Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary.
 * A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
 * 
 * Example:
 * 
 * Given dictionary = [ "deer", "door", "cake", "card" ]
 * 
 * isUnique("dear") -> false
 * isUnique("cart") -> true
 * isUnique("cane") -> false
 * isUnique("make") -> true
 * </pre>
 *
 */
public class UniqueWordAbbreviation {
	public class ValidWordAbbr {
		Map<String, String> map;

		public ValidWordAbbr(String[] dictionary) {
			map = new HashMap<>();
			for (String str : dictionary) {
				String key = getKey(str);
				// If there is more than one string belong to the same key
				// then the key will be invalid, we set the value to ""
				if (map.containsKey(key)) {
					if (!map.get(key).equals(str)) {
						map.put(key, "");
					}
				} else {
					map.put(key, str);
				}
			}
		}

		public boolean isUnique(String word) {
			String key = getKey(word);
			return !map.containsKey(key) || map.get(key).equals(word);
		}

		private String getKey(String str) {
			if (str.length() <= 2)
				return str;
			return str.charAt(0) + Integer.toString(str.length() - 2) + str.charAt(str.length() - 1);
		}
	}

	public class ValidWordAbbr2 {
		private final Map<String, Set<String>> abbrDict = new HashMap<>();

		public ValidWordAbbr2(String[] dictionary) {
			for (String s : dictionary) {
				String abbr = toAbbr(s);
				Set<String> words = abbrDict.containsKey(abbr) ? abbrDict.get(abbr) : new HashSet<>();
				words.add(s);
				abbrDict.put(abbr, words);
			}
		}

		public boolean isUnique(String word) {
			String abbr = toAbbr(word);
			Set<String> words = abbrDict.get(abbr);
			return words == null || (words.size() == 1 && words.contains(word));
		}

		private String toAbbr(String s) {
			int n = s.length();
			if (n <= 2) {
				return s;
			}
			return s.charAt(0) + Integer.toString(n - 2) + s.charAt(n - 1);
		}
	}

	public class ValidWordAbbr3 {
		private final Map<String, Boolean> abbrDict = new HashMap<>();
		private final Set<String> dict;

		public ValidWordAbbr3(String[] dictionary) {
			dict = new HashSet<>(Arrays.asList(dictionary));
			for (String s : dict) {
				String abbr = toAbbr(s);
				abbrDict.put(abbr, !abbrDict.containsKey(abbr));
			}
		}

		public boolean isUnique(String word) {
			String abbr = toAbbr(word);
			Boolean hasAbbr = abbrDict.get(abbr);
			return hasAbbr == null || (hasAbbr && dict.contains(word));
		}

		private String toAbbr(String s) {
			int n = s.length();
			if (n <= 2) {
				return s;
			}
			return s.charAt(0) + Integer.toString(n - 2) + s.charAt(n - 1);
		}
	}
}
