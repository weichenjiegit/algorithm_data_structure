package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

	public int lengthOfLongestSubstring(String s) {
		if (s == null) {
			return 0;
		}
		int result = 0;
		Map<Character, Integer> indexByCharacter = new HashMap<>();
		for (int j = 0, i = 0; j < s.length(); j++) {
			if (indexByCharacter.containsKey(s.charAt(j))) {
				i = Math.max(indexByCharacter.get(s.charAt(j)) + 1, i);
			}
			result = Math.max(result, j - i + 1);
			indexByCharacter.put(s.charAt(j), j);
		}
		return result;
	}

	public int lengthOfLongestSubstring2(String s) {
		int n = s.length(), ans = 0;
		int[] index = new int[256]; // current index of character + 1
		// try to extend the range [i, j]
		for (int j = 0, i = 0; j < n; j++) {
			i = Math.max(index[s.charAt(j)], i);
			ans = Math.max(ans, j - i + 1);
			index[s.charAt(j)] = j + 1;
		}
		return ans;
	}
}
