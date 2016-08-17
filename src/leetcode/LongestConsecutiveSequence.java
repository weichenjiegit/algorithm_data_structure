package leetcode;

import java.util.Arrays;
import java.util.Hashtable;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements
 * sequence.
 * 
 * For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive elements sequence is [1, 2, 3,
 * 4]. Return its length: 4.
 * 
 * Your algorithm should run in O(n) complexity.
 *
 */
public class LongestConsecutiveSequence {
	// O(n)
	public int longestConsecutive(int[] num) {
		if (num.length < 1)
			return 0;
		Hashtable<Integer, Integer> hashtable = new Hashtable<>();
		for (int i = 0; i < num.length; i++) {
			if (hashtable.containsKey(num[i]))
				hashtable.put(num[i], hashtable.get(num[i]) + 1);
			else
				hashtable.put(num[i], 1);
		}
		int max = 1;
		for (int i = 0; i < num.length; i++) {
			if (hashtable.containsKey(num[i])) {
				int temp = 1, cur = num[i];
				hashtable.remove(cur);
				temp += getDis(hashtable, cur + 1, true);
				temp += getDis(hashtable, cur - 1, false);
				max = temp > max ? temp : max;
			}
		}
		return max;
	}

	public int getDis(Hashtable<Integer, Integer> hashtable, int cur, boolean inc) {
		int dis = 0;
		while (hashtable.containsKey(cur)) {
			hashtable.remove(cur);
			dis++;
			if (inc)
				cur++;
			else
				cur--;
		}
		return dis;
	}

	// O(nlgn)
	public int longestConsecutive2(int[] num) {
		Arrays.sort(num);
		int longest = 1;
		int cur = 1;
		for (int i = 1; i < num.length; i++) {
			if (num[i] == num[i - 1] + 1) {
				cur++;
				longest = longest > cur ? longest : cur;
			} else if (num[i] == num[i - 1])
				continue;
			else
				cur = 1;
		}
		return longest;
	}
}
