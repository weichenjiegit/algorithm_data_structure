package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * 
 * Note:
 * 
 * Each element in the result must be unique.
 * 
 * The result can be in any order.
 */
public class IntersectionOfTwoArrays {
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set = new HashSet<>();
		Set<Integer> intersect = new HashSet<>();
		for (int i = 0; i < nums1.length; i++) {
			set.add(nums1[i]);
		}
		for (int i = 0; i < nums2.length; i++) {
			if (set.contains(nums2[i])) {
				intersect.add(nums2[i]);
			}
		}
		int[] result = new int[intersect.size()];
		int i = 0;
		for (Integer num : intersect) {
			result[i++] = num;
		}
		return result;
	}

	public int[] intersection2(int[] nums1, int[] nums2) {
		Set<Integer> set = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
		return Arrays.stream(nums1).distinct().filter(e-> set.contains(e)).toArray();
	}
}
