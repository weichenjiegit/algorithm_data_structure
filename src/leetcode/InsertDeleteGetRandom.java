package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 * 
 * insert(val): Inserts an item val to the set if not already present.
 * 
 * remove(val): Removes an item val from the set if present.
 * 
 * getRandom: Returns a random element from current set of elements. Each element must have the same
 * probability of being returned.
 */
public class InsertDeleteGetRandom {
	public class RandomizedSet {
		List<Integer> nums;
		Map<Integer, Integer> indexByNumber;
		java.util.Random rand = new java.util.Random();

		/** Initialize your data structure here. */
		public RandomizedSet() {
			nums = new ArrayList<>();
			indexByNumber = new HashMap<>();
		}

		/**
		 * Inserts a value to the set. Returns true if the set did not already contain the specified
		 * element.
		 */
		public boolean insert(int val) {
			if (indexByNumber.containsKey(val))
				return false;
			indexByNumber.put(val, nums.size());
			nums.add(val);
			return true;
		}

		/**
		 * Removes a value from the set. Returns true if the set contained the specified element.
		 */
		public boolean remove(int val) {
			if (!indexByNumber.containsKey(val))
				return false;
			int index = indexByNumber.get(val);
			if (index < nums.size() - 1) {
				// not the last one
				int lastone = nums.get(nums.size() - 1);
				nums.set(index, lastone);
				indexByNumber.put(lastone, index);
			}
			indexByNumber.remove(val);
			nums.remove(nums.size() - 1);
			return true;
		}

		/** Get a random element from the set. */
		public int getRandom() {
			return nums.get(rand.nextInt(nums.size()));
		}
	}
}
