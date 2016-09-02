package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Deque;
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
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of
 * courses you should take to finish all courses.
 * 
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to
 * finish all courses, return an empty array.
 * 
 * For example:
 * 
 * 2, [[1,0]]
 * 
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So
 * the correct course order is [0,1]
 * 
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * 
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1
 * and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course
 * order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
 */
public class CourseScheduleII {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		int[] degree = new int[numCourses];
		Queue<Integer> queue = new LinkedList<>();
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
			}
		}

		int index = numCourses;
		int[] order = new int[numCourses];
		while (queue.size() != 0) {
			int course = queue.poll();
			index--;
			order[index] = course;
			for (int i = 0; i < graph.get(course).size(); i++) {
				int preCourse = graph.get(course).get(i);
				degree[preCourse]--;
				if (degree[preCourse] == 0) {
					queue.add(preCourse);
				}
			}
		}
		if (index == 0)
			return order;
		else
			return new int[0];
	}

	// Example to solve by DFS after construct the graph
	public int[] solveByDFS(List<List<Integer>> adjs) {
		BitSet visited = new BitSet(adjs.size());
		BitSet onStack = new BitSet(adjs.size());
		Deque<Integer> order = new ArrayDeque<>();
		for (int i = adjs.size() - 1; i >= 0; i--) {
			if (visited.get(i) == false && hasOrder(i, adjs, visited, onStack, order) == false)
				return new int[0];
		}
		int[] orderArray = new int[adjs.size()];
		for (int i = 0; !order.isEmpty(); i++)
			orderArray[i] = order.pop();
		return orderArray;
	}

	private boolean hasOrder(int from, List<List<Integer>> adjs, BitSet visited, BitSet onStack, Deque<Integer> order) {
		visited.set(from);
		onStack.set(from);
		for (int to : adjs.get(from)) {
			if (visited.get(to) == false) {
				if (hasOrder(to, adjs, visited, onStack, order) == false)
					return false;
			} else if (onStack.get(to) == true) {
				return false;
			}
		}
		onStack.clear(from);
		order.push(from);
		return true;
	}
}
