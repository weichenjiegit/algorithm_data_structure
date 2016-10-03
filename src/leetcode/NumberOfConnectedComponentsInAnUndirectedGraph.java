package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of
 * nodes), write a function to find the number of connected components in an undirected graph.
 * 
 * Note: You can assume that no duplicate edges will appear in edges. Since all edges are
 * undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {
	// Union find solution.
	public int countComponents(int n, int[][] edges) {
		int[] roots = new int[n];
		for (int i = 0; i < n; i++)
			roots[i] = i;

		for (int[] e : edges) {
			int root1 = find(roots, e[0]);
			int root2 = find(roots, e[1]);
			if (root1 != root2) {
				roots[root1] = root2; // union
				n--;
			}
		}
		return n;
	}

	private int find(int[] roots, int id) {
		while (roots[id] != id) {
			roots[id] = roots[roots[id]]; // optional: path compression
			id = roots[id];
		}
		return id;
	}

	// DFS.
	public int countComponents2(int n, int[][] edges) {
		if (n <= 1) {
			return n;
		}
		List<List<Integer>> adjList = new ArrayList<List<Integer>>();
		for (int i = 0; i < n; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for (int[] edge : edges) {
			adjList.get(edge[0]).add(edge[1]);
			adjList.get(edge[1]).add(edge[0]);
		}
		boolean[] visited = new boolean[n];
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				count++;
				dfs(visited, i, adjList);
			}
		}

		return count;
	}

	private void dfs(boolean[] visited, int index, List<List<Integer>> adjList) {
		visited[index] = true;
		for (int i : adjList.get(index)) {
			if (!visited[i]) {
				dfs(visited, i, adjList);
			}
		}
	}

	// BFS.
	public int countComponents3(int n, int[][] edges) {
		if (n <= 1) {
			return n;
		}
		List<List<Integer>> adjList = new ArrayList<List<Integer>>();
		for (int i = 0; i < n; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for (int[] edge : edges) {
			adjList.get(edge[0]).add(edge[1]);
			adjList.get(edge[1]).add(edge[0]);
		}
		boolean[] visited = new boolean[n];
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				count++;
				Queue<Integer> queue = new LinkedList<Integer>();
				queue.offer(i);
				while (!queue.isEmpty()) {
					int index = queue.poll();
					visited[index] = true;
					for (int next : adjList.get(index)) {
						if (!visited[next]) {
							queue.offer(next);
						}
					}
				}
			}
		}
		return count;
	}

}
