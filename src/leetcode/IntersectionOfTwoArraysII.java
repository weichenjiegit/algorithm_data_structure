package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * 
 * Note:
 * 
 * Each element in the result should appear as many times as it shows in both arrays.
 * 
 * The result can be in any order.
 * 
 * Follow up:
 * 
 * What if the given array is already sorted? How would you optimize your algorithm?
 * 
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * 
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load
 * all elements into the memory at once?
 */
public class IntersectionOfTwoArraysII {
	public int[] intersect(int[] nums1, int[] nums2) {
		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < nums1.length; i++) {
			if (map.containsKey(nums1[i]))
				map.put(nums1[i], map.get(nums1[i]) + 1);
			else
				map.put(nums1[i], 1);
		}

		for (int i = 0; i < nums2.length; i++) {
			if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
				result.add(nums2[i]);
				map.put(nums2[i], map.get(nums2[i]) - 1);
			}
		}

		int[] r = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			r[i] = result.get(i);
		}

		return r;
	}

	public int[] intersect2(int[] nums1, int[] nums2) {
		Map<Integer, Long> map = Arrays.stream(nums2).boxed()
		        .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		return Arrays.stream(nums1).filter(e -> {
			if (!map.containsKey(e))
				return false;
			map.put(e, map.get(e) - 1);
			if (map.get(e) == 0)
				map.remove(e);
			return true;
		}).toArray();
	}

	/**
	 * If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of
	 * array that fit into the memory, and record the intersections.
	 */
}
