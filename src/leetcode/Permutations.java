package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of numbers, return all possible permutations.
 * 
 * For example, [1,2,3] have the following permutations: [1,2,3], [1,3,2], [2,1,3], [2,3,1],
 * [3,1,2], and [3,2,1].
 */
public class Permutations {
	public List<List<Integer>> permute(int[] num) {
		List<List<Integer>> res = new ArrayList<>();
		perm(res, num, 0);
		return res;
	}

	public void perm(List<List<Integer>> res, int[] num, int cur) {
		if (cur == num.length) {
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < num.length; i++)
				list.add(num[i]);
			res.add(list);
		}
		for (int i = cur; i < num.length; i++) {
			swap(num, i, cur);
			perm(res, num, cur + 1);
			swap(num, i, cur);
		}
	}

	private void swap(int[] num, int i, int j) {
		int temp = num[i];
		num[i] = num[j];
		num[j] = temp;
	}

	public static void main(String[] args) {
		Permutations test = new Permutations();
		List<List<Integer>> res = test.permute(new int[] { 1, 2, 3 });
		for (int i = 0; i < res.size(); i++) {
			List<Integer> in = res.get(i);
			for (int j = 0; j < in.size(); j++) {
				System.out.print(in.get(j));
			}
			System.out.println();
		}
	}
}
