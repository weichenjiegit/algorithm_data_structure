package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest
 * transformation sequence(s) from beginWord to endWord, such that:
 * 
 * Only one letter can be changed at a time Each intermediate word must exist in the word list
 * 
 * <pre>
 * For example,
 * 
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * Return
 * 
 *   [
 *     ["hit","hot","dot","dog","cog"],
 *     ["hit","hot","lot","log","cog"]
 *   ]
 * 
 * </pre>
 * 
 * Note: All words have the same length. All words contain only lowercase alphabetic characters.
 *
 * 
 */
public class WordLadderII {
	public List<List<String>> findLadders(String start, String end, Set<String> dict) {
		// hash set for both ends
		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();

		// initial words in both ends
		set1.add(start);
		set2.add(end);

		// we use a map to help construct the final result
		Map<String, List<String>> map = new HashMap<String, List<String>>();

		// build the map
		helper(dict, set1, set2, map, false);

		List<List<String>> res = new ArrayList<List<String>>();
		List<String> sol = new ArrayList<String>(Arrays.asList(start));

		// recursively build the final result
		generateList(start, end, map, sol, res);

		return res;
	}

	boolean helper(Set<String> dict, Set<String> set1, Set<String> set2, Map<String, List<String>> map, boolean flip) {
		if (set1.isEmpty()) {
			return false;
		}

		if (set1.size() > set2.size()) {
			return helper(dict, set2, set1, map, !flip);
		}

		// remove words on current both ends from the dict
		dict.removeAll(set1);
		dict.removeAll(set2);

		// as we only need the shortest paths
		// we use a boolean value help early termination
		boolean done = false;

		// set for the next level
		Set<String> set = new HashSet<String>();

		// for each string in end 1
		for (String str : set1) {
			for (int i = 0; i < str.length(); i++) {
				char[] chars = str.toCharArray();

				// change one character for every position
				for (char ch = 'a'; ch <= 'z'; ch++) {
					chars[i] = ch;

					String word = new String(chars);

					// make sure we construct the tree in the correct direction
					String key = flip ? word : str;
					String val = flip ? str : word;

					List<String> list = map.containsKey(key) ? map.get(key) : new ArrayList<String>();

					if (set2.contains(word)) {
						done = true;

						list.add(val);
						map.put(key, list);
					}

					if (!done && dict.contains(word)) {
						set.add(word);

						list.add(val);
						map.put(key, list);
					}
				}
			}
		}

		// early terminate if done is true
		return done || helper(dict, set2, set, map, !flip);
	}

	void generateList(String start, String end, Map<String, List<String>> map, List<String> sol,
	        List<List<String>> res) {
		if (start.equals(end)) {
			res.add(new ArrayList<String>(sol));
			return;
		}

		// need this check in case the diff between start and end happens to be one
		// e.g "a", "c", {"a", "b", "c"}
		if (!map.containsKey(start)) {
			return;
		}

		for (String word : map.get(start)) {
			sol.add(word);
			generateList(word, end, map, sol, res);
			sol.remove(sol.size() - 1);
		}
	}
}
