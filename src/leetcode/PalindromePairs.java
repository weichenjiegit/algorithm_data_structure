package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so
 * that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 * 
 * <pre>
 * Example 1:
 * Given words = ["bat", "tab", "cat"]
 * Return [[0, 1], [1, 0]]
 * The palindromes are ["battab", "tabbat"]
 * 
 * Example 2:
 * Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 * Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 * </pre>
 */
public class PalindromePairs {
	/**
	 * The <= in for (int j=0; j<=words[i].length(); j++) is aimed to handle empty string in the
	 * input. Consider the test case of ["a", ""];
	 * 
	 * Since we now use <= in for (int j=0; j<=words[i].length(); j++) instead of <. There may be
	 * duplicates in the output (consider test case ["abcd", "dcba"]). Therefore I put a
	 * str2.length()!=0 to avoid duplicates.
	 */
	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> result = new ArrayList<>();
		if (words == null || words.length < 2)
			return result;
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < words.length; i++)
			map.put(words[i], i);
		for (int i = 0; i < words.length; i++) {
			// notice it should be "j <= words[i].length()"
			for (int j = 0; j <= words[i].length(); j++) {
				String str1 = words[i].substring(0, j);
				String str2 = words[i].substring(j);
				if (isPalindrome(str1)) {
					String str2rvs = new StringBuilder(str2).reverse().toString();
					if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
						List<Integer> list = new ArrayList<Integer>();
						list.add(map.get(str2rvs));
						list.add(i);
						result.add(list);
					}
				}
				if (isPalindrome(str2)) {
					String str1rvs = new StringBuilder(str1).reverse().toString();
					// check "str.length() != 0" to avoid duplicates
					if (map.containsKey(str1rvs) && map.get(str1rvs) != i && str2.length() != 0) {
						List<Integer> list = new ArrayList<Integer>();
						list.add(i);
						list.add(map.get(str1rvs));
						result.add(list);
					}
				}
			}
		}
		return result;
	}

	private boolean isPalindrome(String str) {
		int left = 0;
		int right = str.length() - 1;
		while (left <= right) {
			if (str.charAt(left++) != str.charAt(right--))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		new PalindromePairs().palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"});
	}
}
