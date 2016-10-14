package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" ->
 * "bcd". We can keep "shifting" which forms the sequence:
 * 
 * "abc" -> "bcd" -> ... -> "xyz"
 * 
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to
 * the same shifting sequence.
 * 
 * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], A solution is:
 * 
 * [ ["abc","bcd","xyz"], ["az","ba"], ["acef"], ["a","z"] ]
 */
public class GroupShiftedStrings {
	public List<List<String>> groupStrings(String[] strings) {
		List<List<String>> ret = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for (String s : strings) {
			String key = "";
			for (int i = 1; i < s.length(); i++) {
				int offset = s.charAt(i) - s.charAt(i - 1);
				key += offset < 0 ? offset + 26 : offset;
			}
			if (!map.containsKey(key))
				map.put(key, new ArrayList<String>());
			map.get(key).add(s);
		}
		for (List<String> ss : map.values()) {
			Collections.sort(ss);
			ret.add(ss);
		}
		return ret;
	}
}
