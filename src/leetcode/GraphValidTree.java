package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of
 * nodes), write a function to check whether these edges make up a valid tree.
 * 
 * For example:
 * 
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * 
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 * 
 * Hint:
 * 
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid
 * tree?
 * 
 * According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two
 * vertices are connected by exactly one path. In other words, any connected graph without simple
 * cycles is a tree.”
 * 
 */
public class GraphValidTree {
	// Union find.
	public boolean validTree(int n, int[][] edges) {
		// initialize n isolated islands
		int[] nums = new int[n];
		Arrays.fill(nums, -1);

		// perform union find
		for (int i = 0; i < edges.length; i++) {
			int x = find(nums, edges[i][0]);
			int y = find(nums, edges[i][1]);

			// if two vertices happen to be in the same set
			// then there's a cycle
			if (x == y)
				return false;

			// union
			nums[x] = y;
		}

		return edges.length == n - 1;
	}

	private int find(int nums[], int i) {
		if (nums[i] == -1)
			return i;
		return find(nums, nums[i]);
	}

	// DFS solution.
	public boolean validTree2(int n, int[][] edges) {
		List<List<Integer>> adjList = new ArrayList<>(n);
		for (int i = 0; i < n; i++)
			adjList.add(i, new ArrayList<>());
		for (int i = 0; i < edges.length; i++) {
			int u = edges[i][0], v = edges[i][1];
			adjList.get(u).add(v);
			adjList.get(v).add(u);
		}

		boolean[] visited = new boolean[n];
		if (hasCycle(adjList, visited, 0, -1))
			return false;

		// make sure all vertices are connected
		for (int i = 0; i < n; i++) {
			if (!visited[i])
				return false;
		}
		return true;
	}

	private boolean hasCycle(List<List<Integer>> adj, boolean[] visit, int v, int par) {
		if (visit[v])
			return true;
		visit[v] = true;
		for (int nb : adj.get(v))
			if (nb != par && hasCycle(adj, visit, nb, v))
				return true;
		return false;
	}
}
