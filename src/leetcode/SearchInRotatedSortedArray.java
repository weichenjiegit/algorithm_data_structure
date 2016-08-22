package leetcode;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search.
 * If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 */
public class SearchInRotatedSortedArray {
	public int search(int[] A, int target) {
		if(A.length == 0)
			return -1;
		int begin = 0;
		int end = A.length - 1;
		while(begin <= end){
			int mid = begin + (end - begin) / 2;
			if(A[mid] == target)
				return mid;
			else if(A[begin] <= A[mid]){
				if(target >= A[begin] && target <= A[mid])
					end = mid - 1;
				else
					begin = mid + 1;
			}
			else{
				if(target >= A[mid] && target <= A[end])
					begin = mid + 1;
				else
					end = mid - 1;
			}
		}
		return -1;
	}
}
