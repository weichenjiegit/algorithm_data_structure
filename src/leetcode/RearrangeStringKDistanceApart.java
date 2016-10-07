package leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a non-empty string str and an integer k, rearrange the string such that the same characters
 * are at least distance k from each other.
 * 
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string,
 * return an empty string "".
 */
public class RearrangeStringKDistanceApart {
	// Greedy solution.
	// Count array to store the remaining count of every character. Valid array to keep track
	// of the most left position that one character can appear.
	public String rearrangeString(String str, int k) {
		int length = str.length();
		int[] count = new int[26];
		int[] valid = new int[26];
		for (int i = 0; i < length; i++) {
			count[str.charAt(i) - 'a']++;
		}
		StringBuilder sb = new StringBuilder();
		for (int index = 0; index < length; index++) {
			int candidatePos = findValidMax(count, valid, index);
			if (candidatePos == -1)
				return "";
			count[candidatePos]--;
			valid[candidatePos] = index + k;
			sb.append((char) ('a' + candidatePos));
		}
		return sb.toString();
	}

	private int findValidMax(int[] count, int[] valid, int index) {
		int max = Integer.MIN_VALUE;
		int candidatePos = -1;
		for (int i = 0; i < count.length; i++) {
			if (count[i] > 0 && count[i] > max && index >= valid[i]) {
				max = count[i];
				candidatePos = i;
			}
		}
		return candidatePos;
	}

	public String rearrangeString2(String str, int k) {

		StringBuilder rearranged = new StringBuilder();
		// count frequency of each char
		Map<Character, Integer> map = new HashMap<>();
		for (char c : str.toCharArray()) {
			if (!map.containsKey(c)) {
				map.put(c, 0);
			}
			map.put(c, map.get(c) + 1);
		}

		// construct a max heap using self-defined comparator, which holds all Map entries, Java is
		// quite verbose
		Queue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
		        new Comparator<Map.Entry<Character, Integer>>() {
			        public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
				        return entry2.getValue() - entry1.getValue();
			        }
		        });

		Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();
		maxHeap.addAll(map.entrySet());

		while (!maxHeap.isEmpty()) {

			Map.Entry<Character, Integer> current = maxHeap.poll();
			rearranged.append(current.getKey());
			current.setValue(current.getValue() - 1);
			waitQueue.offer(current);

			if (waitQueue.size() < k) { // intial k-1 chars, waitQueue not full yet
				continue;
			}
			// release from waitQueue if char is already k apart
			Map.Entry<Character, Integer> front = waitQueue.poll();
			// note that char with 0 count still needs to be placed in waitQueue as a place holder
			if (front.getValue() > 0) {
				maxHeap.offer(front);
			}
		}

		return rearranged.length() == str.length() ? rearranged.toString() : "";
	}
}
