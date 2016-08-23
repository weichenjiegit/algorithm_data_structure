package leetcode;

/**
 * Given n, how many structurally unique BST's (binary search trees) 
 * that store values 1...n?
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 * <pre>
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * </pre>
 */
public class UniqueBinarySearchTrees {
	public int numTrees(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		int total = 0;
		for (int i = 0; i < n; i++) {
			int left = numTrees(i);
			int right = numTrees(n - i - 1);
			total += left * right;
		}
		return total;
	}
}
