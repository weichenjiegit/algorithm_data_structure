package leetcode;

/**
 * Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
 */
import java.util.ArrayList;
import java.util.List;

public class PascalTriangleII {
	public List<Integer> getRow(int rowIndex) {
		int[] res = new int[rowIndex + 1];
		res[0] = 1;
		for (int i = 0; i < rowIndex; i++) {
			for (int j = i; j > 0; j--) {
				if (j == i)
					res[j] = 1;
				else
					res[j] = res[j - 1] + res[j];
			}
		}
		List<Integer> res_list = new ArrayList<>();
		for (int i = 0; i < res.length; i++)
			res_list.add(res[i]);
		return res_list;
	}
}
