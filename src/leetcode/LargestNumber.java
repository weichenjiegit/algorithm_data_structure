package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * 
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * 
 * Note: The result may be very large, so you need to return a string instead of an integer.
 *
 */
public class LargestNumber {

	public String largestNumber(int[] nums) {
		if (nums == null || nums.length == 0)
			return "";
		String[] stringNums = new String[nums.length];
		for (int i = 0; i < nums.length; i++)
			stringNums[i] = String.valueOf(nums[i]);
		Comparator<String> comp = new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				String s1 = str1 + str2;
				String s2 = str2 + str1;
				return s2.compareTo(s1); // reverse order here, so we can do append() later
			}
		};
		Arrays.sort(stringNums, comp);
		// An extreme edge case by lc, say you have only a bunch of 0 in your int array
		if (stringNums[0].charAt(0) == '0')
			return "0";
		StringBuilder sb = new StringBuilder();
		for (String s : stringNums)
			sb.append(s);
		return sb.toString();
	}
}
