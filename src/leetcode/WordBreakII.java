package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where
 * each word is a valid dictionary word.
 * 
 * Return all such possible sentences.
 * 
 * For example, given s = "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"].
 * 
 * A solution is ["cats and dog", "cat sand dog"].
 */
public class WordBreakII {

	// DP + back track
	public List<String> wordBreak1(String s, Set<String> dict) {
		int n = s.length();
		List<List<Integer>> pres = new ArrayList<>(n);
		// initialize
		for (int i = 0; i < n; ++i)
			pres.add(new ArrayList<Integer>());
		// DP. pres[i] stores position j where should insert space
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j <= i; ++j) {
				String suffix = s.substring(j, i + 1);
				if ((j == 0 || pres.get(j - 1).size() > 0) && dict.contains(suffix))
					pres.get(i).add(j);
			}
		}
		return getPath(s, n, pres);
	}

	public List<String> getPath(String s, int n, List<List<Integer>> pres) {
		List<String> res = new ArrayList<>();
		for (int pre : pres.get(n - 1)) {
			if (pre == 0) {
				res.add(s.substring(0, n));
			} else {
				List<String> preres = getPath(s, pre, pres);
				String sub = s.substring(pre, n);
				for (String ss : preres)
					res.add(ss + " " + sub);
			}
		}
		return res;
	}

	public List<String> wordBreak2(String s, Set<String> wordDict) {
		return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
	}

	// DFS function returns an array including all substrings derived from s.
	private List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>> map) {
		if (map.containsKey(s))
			return map.get(s);

		LinkedList<String> res = new LinkedList<>();
		if (s.length() == 0) {
			res.add("");
			return res;
		}
		for (String word : wordDict) {
			if (s.startsWith(word)) {
				List<String> sublist = DFS(s.substring(word.length()), wordDict, map);
				for (String sub : sublist)
					res.add(word + (sub.isEmpty() ? "" : " ") + sub);
			}
		}
		map.put(s, res);
		return res;
	}

	// Pure DFS solution. Time O(2^n). Time limit Exceed.
	public ArrayList<String> res;

	public ArrayList<String> wordBreak3(String s, Set<String> dict) {
		res = new ArrayList<String>();
		ArrayList<String> temp = new ArrayList<String>();
		if (s.length() < 1 || dict.size() == 0)
			return res;
		for (int i = 0; i <= s.length(); i++) {
			String suffix = s.substring(0, i);
			if (dict.contains(suffix)) {
				temp.add(suffix);
				DFS(s.substring(i), dict, temp);
				temp.remove(temp.size() - 1);
			}
		}
		return res;
	}

	public void DFS(String s, Set<String> dict, ArrayList<String> temp) {
		if (s.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < temp.size(); i++) {
				sb.append(temp.get(i));
				sb.append(" ");
			}
			sb.deleteCharAt(sb.length() - 1);
			res.add(sb.toString());
		}
		for (int i = 0; i <= s.length(); i++) {
			String suffix = s.substring(0, i);
			if (dict.contains(suffix)) {
				temp.add(suffix);
				DFS(s.substring(i), dict, temp);
				temp.remove(temp.size() - 1);
			}
		}
	}
}
