package leetcode;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * Note:
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RecoverBinarySearchTree {
	// in order traversal to a stack uses O(n)
	public void recoverTree(TreeNode root) {
		if (root == null)
			return;
		List<TreeNode> inorder = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode p = root;
		do {
			if (p != null) {
				stack.push(p);
				p = p.left;
			} else {
				p = stack.pop();
				inorder.add(p);
				p = p.right;
			}
		} while (!stack.empty() || p != null);
		int first = -1, second = -1;
		for (int i = 0; i < inorder.size() - 1; i++) {
			if (inorder.get(i).val > inorder.get(i + 1).val && first < 0)
				first = i;
			if (inorder.get(i).val > inorder.get(i + 1).val && first >= 0)
				second = i + 1;
		}
		int temp = inorder.get(first).val;
		inorder.get(first).val = inorder.get(second).val;
		inorder.get(second).val = temp;
	}

	public void recoverTree2(TreeNode root) {
		TreeNode pre = null;
		TreeNode first = null, second = null;
		// Morris Traversal
		TreeNode temp = null; // predecessor for root/current node in-order
		while (root != null) {
			if (root.left != null) {
				// connect threading for root
				temp = root.left;
				while (temp.right != null && temp.right != root)
					temp = temp.right;
				// the threading already exists
				if (temp.right != null) {
					// Same start
					if (pre != null && pre.val > root.val) {
						if (first == null) {
							first = pre;
							second = root;
						} else {
							second = root;
						}
					}
					pre = root;
					// Same end
					temp.right = null;
					root = root.right;
				} else {
					// construct the threading
					temp.right = root;
					root = root.left;
				}
			} else {
				// Same start
				if (pre != null && pre.val > root.val) {
					if (first == null) {
						first = pre;
						second = root;
					} else {
						second = root;
					}
				}
				// Same end
				pre = root;
				root = root.right;
			}
		}
		// swap two node values;
		if (first != null && second != null) {
			int t = first.val;
			first.val = second.val;
			second.val = t;
		}
	}

	// in order traversal to a stack uses O(n)
	public void recoverTree3(TreeNode root) {
		if (root == null)
			return;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode first = null;
		TreeNode second = null;
		TreeNode pre = null;
		TreeNode p = root;
		do {
			if (p != null) {
				stack.push(p);
				p = p.left;
			} else {
				p = stack.pop();
				if (pre == null)
					pre = p;
				else {
					if (pre.val > p.val) {
						if (first == null) {
							first = pre;
							second = p;
							pre = p;
						} else {
							second = p;
							break;
						}
					} else
						pre = p;
				}
				p = p.right;
			}
		} while (!stack.empty() || p != null);
		int temp = first.val;
		first.val = second.val;
		second.val = temp;
	}

	TreeNode firstElement = null;
	TreeNode secondElement = null;
	// The reason for this initialization is to avoid null pointer exception in the first comparison
	// when prevElement has not been initialized
	TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);
	public void recoverTree4(TreeNode root) {
		// In order traversal to find the two elements
		traverse(root);
		// Swap the values of the two nodes
		int temp = firstElement.val;
		firstElement.val = secondElement.val;
		secondElement.val = temp;
	}

	private void traverse(TreeNode root) {
		if (root == null)
			return;
		traverse(root.left);
		if (firstElement == null && prevElement.val >= root.val) {
			firstElement = prevElement;
		}
		if (firstElement != null && prevElement.val >= root.val) {
			secondElement = root;
		}
		prevElement = root;
		traverse(root.right);
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		// root.left = new TreeNode(3);
		root.right = new TreeNode(2);
		root.right.right = new TreeNode(1);
		RecoverBinarySearchTree test = new RecoverBinarySearchTree();
		test.recoverTree3(root);
	}
}
