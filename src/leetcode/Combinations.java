package leetcode;

import java.util.ArrayList;

/**
 * Given two integers n and k,
 * return all possible combinations of k numbers out of 1 ...n.
 * For example,
 * If n = 4 and k = 2, a solution is:
 * [
 *  [2,4],
 *  [3,4],
 *  [2,3],
 *  [1,2],
 *  [1,3],
 *  [1,4],
 * ]
 */
public class Combinations {

	// Solution 1
	ArrayList<ArrayList<Integer>> res;

	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
		res = new ArrayList<ArrayList<Integer>>();
		if (k > n || k == 0)
			return res;
		ArrayList<Integer> com = new ArrayList<Integer>();
		DFS(com, 0, k, n);
		return res;
	}

	public void DFS(ArrayList<Integer> com, int level, int k, int n) {
		if (com.size() == k) {
			res.add(new ArrayList<Integer>(com));
			return;
		}
		if (level == n)
			return;
		else {
			level++;
			DFS(com, level, k, n);// com does not contain current level
			com.add(level);
			DFS(com, level, k, n);// com contains current level
			com.remove(com.size() - 1);
		}
	}

	// Solution 2
	ArrayList<ArrayList<Integer>> res2;

	public ArrayList<ArrayList<Integer>> combine2(int n, int k) {
		res2 = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		subsets(temp, n, k, 1);
		return res2;
	}

	private void subsets(ArrayList<Integer> temp, int n, int k, int level) {
		if (temp.size() == k) {
			res2.add(new ArrayList<Integer>(temp));
			return;
		}
		for (int i = level; i <= n; i++) {
			temp.add(i);// from level to n pick one.
			subsets(temp, n, k, i + 1);
			temp.remove(temp.size() - 1);
		}
	}

	// Solution 3
	// This solution is based on C(n,k) = C(n-1,k-1） + C(n-1,k)。
	ArrayList<ArrayList<Integer>> res3;

	public ArrayList<ArrayList<Integer>> combine3(int n, int k) {
		res3 = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		com_rec(temp, n, k, 1);
		return res3;
	}

	private void com_rec(ArrayList<Integer> temp, int n, int k, int index) {
		if (k == 0) {
			res3.add(new ArrayList<Integer>(temp));
		} else if (k > 0 && index <= n) {
			temp.add(index);
			com_rec(temp, n, k - 1, index + 1);

			temp.remove(temp.size() - 1);
			com_rec(temp, n, k, index + 1);
		}
	}
}
