package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * For example, Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * 
 * Note:
 * 
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * 
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {
	public List<Integer> topKFrequent(int[] nums, int k) {

		List<List<Integer>> bucket = new ArrayList<>(nums.length + 1);
		for (int i = 0; i <= nums.length; i++) {
			bucket.add(new ArrayList<>());
		}
		Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

		for (int n : nums) {
			frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
		}

		for (int key : frequencyMap.keySet()) {
			int frequency = frequencyMap.get(key);
			bucket.get(frequency).add(key);
		}

		List<Integer> res = new ArrayList<>();

		for (int pos = bucket.size() - 1; pos >= 0 && res.size() < k; pos--) {
			if (!bucket.get(pos).isEmpty()) {
				res.addAll(bucket.get(pos));
			}
		}
		return res;
	}

	public List<Integer> topKFrequent2(int[] nums, int k) {
		Map<Integer, Integer> counterMap = new HashMap<>();
		for (int num : nums) {
			int count = counterMap.getOrDefault(num, 0);
			counterMap.put(num, count + 1);
		}

		PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
		for (Map.Entry<Integer, Integer> entry : counterMap.entrySet()) {
			pq.offer(entry);
			if (pq.size() > k)
				pq.poll();
		}

		List<Integer> res = new LinkedList<>();
		while (!pq.isEmpty()) {
			res.add(0, pq.poll().getKey());
		}
		return res;
	}
}
