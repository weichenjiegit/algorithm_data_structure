package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course
 * 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to
 * finish all courses?
 * 
 * For example:
 * 
 * 2, [[1,0]]
 * 
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it
 * is possible.
 * 
 * 2, [[1,0],[0,1]]
 * 
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, and
 * to take course 0 you should also have finished course 1. So it is impossible.
 * 
 * Note: The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
 * Read more about how a graph is represented.
 * 
 * Hints:
 * 
 * This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists,
 * no topological ordering exists and therefore it will be impossible to take all courses.
 */
public class CourseSchedule {

	/** Topological ordering in BFS. */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		int[] degree = new int[numCourses];
		Queue<Integer> queue = new LinkedList<>();
		int count = 0;
		for (int i = 0; i < numCourses; i++) {
			graph.put(i, new ArrayList<Integer>());
		}

		for (int i = 0; i < prerequisites.length; i++) {
			degree[prerequisites[i][1]]++;
			graph.get(prerequisites[i][0]).add(prerequisites[i][1]);
		}
		for (int i = 0; i < degree.length; i++) {
			if (degree[i] == 0) {
				queue.add(i);
				count++;
			}
		}

		while (queue.size() != 0) {
			int course = queue.poll();
			for (int i = 0; i < graph.get(course).size(); i++) {
				int preCourse = graph.get(course).get(i);
				degree[preCourse]--;
				if (degree[preCourse] == 0) {
					queue.add(preCourse);
					count++;
				}
			}
		}
		if (count == numCourses)
			return true;
		else
			return false;
	}

	// Exceeds time limit.
	public boolean canFinish2(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		for (int i = 0; i < numCourses; i++) {
			graph.put(i, new ArrayList<Integer>());
		}

		boolean[] visited = new boolean[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			graph.get(prerequisites[i][0]).add(prerequisites[i][1]);
		}

		for (int i = 0; i < numCourses; i++) {
			if (!dfs(graph, visited, i))
				return false;
		}
		return true;
	}

	private boolean dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int course) {
		if (visited[course]) {
			return false;
		} else {
			visited[course] = true;
		}

		for (int i = 0; i < graph.get(course).size(); i++) {
			if (!dfs(graph, visited, graph.get(course).get(i)))
				return false;
		}
		visited[course] = false;
		return true;
	}
}
