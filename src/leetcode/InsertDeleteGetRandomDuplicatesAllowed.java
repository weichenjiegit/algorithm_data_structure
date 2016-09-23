package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Design a data structure that supports all following operations in average O(1) time. Note:
 * Duplicate elements are allowed.
 * 
 * insert(val): Inserts an item val to the collection.
 * 
 * remove(val): Removes an item val from the collection if present.
 * 
 * getRandom: Returns a random element from current collection of elements. The probability of each
 * element being returned is linearly related to the number of same value the collection contains.
 */
public class InsertDeleteGetRandomDuplicatesAllowed {
	public class RandomizedCollection {

		List<Integer> nums;
		Map<Integer, Set<Integer>> map;

		public RandomizedCollection() {
			nums = new ArrayList<>();
			map = new HashMap<Integer, Set<Integer>>();
		}

		/**
		 * Inserts a value to the collection. Returns true if the collection did not already contain
		 * the specified element.
		 */
		public boolean insert(int val) {
			boolean alreadyExists = map.containsKey(val);
			if (!alreadyExists) {
				map.put(val, new LinkedHashSet<Integer>());
			}
			map.get(val).add(nums.size());
			nums.add(val);
			return !alreadyExists;
		}

		/**
		 * Removes a value from the collection. Returns true if the collection contained the
		 * specified element.
		 */
		public boolean remove(int val) {
			if (!map.containsKey(val)) {
				return false;
			}
			// Get arbitary index of the ArrayList that contains val
			Set<Integer> valSet = map.get(val);
			int indexToReplace = valSet.iterator().next();

			// Obtain the set of the number in the last place of the ArrayList
			int numAtLastPlace = nums.get(nums.size() - 1);
			Set<Integer> replaceWith = map.get(numAtLastPlace);

			// Replace val at arbitary index with very last number
			nums.set(indexToReplace, numAtLastPlace);

			// Remove appropriate index
			valSet.remove(indexToReplace);

			// Don't change set if we were replacing the removed item with the same number
			if (indexToReplace != nums.size() - 1) {
				replaceWith.remove(nums.size() - 1);
				replaceWith.add(indexToReplace);
			}
			nums.remove(nums.size() - 1);

			// Remove map entry if set is now empty, then return
			if (valSet.isEmpty()) {
				map.remove(val);
			}
			return true;
		}

		/** Get a random element from the collection. */
		public int getRandom() {
			return nums.get((int) (Math.random() * nums.size()));
		}
	}
}
