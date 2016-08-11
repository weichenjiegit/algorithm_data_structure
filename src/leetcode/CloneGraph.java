package leetcode;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class CloneGraph {
	class UndirectedGraphNode {
		int label;
		ArrayList<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	}

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null)
			return null;
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		queue.add(node);
		Hashtable<UndirectedGraphNode, UndirectedGraphNode> hashtable =
				new Hashtable<UndirectedGraphNode, UndirectedGraphNode>();
		UndirectedGraphNode nodeCopy = new UndirectedGraphNode(node.label);
		hashtable.put(node, nodeCopy);
		while (!queue.isEmpty()) {
			UndirectedGraphNode cur = queue.poll();
			for (UndirectedGraphNode neighbor : cur.neighbors) {
				if (!hashtable.containsKey(neighbor)) {
					UndirectedGraphNode neighborCopy = new UndirectedGraphNode(neighbor.label);
					hashtable.get(cur).neighbors.add(neighborCopy);
					hashtable.put(neighbor, neighborCopy);
					queue.add(neighbor);
				} else {
					hashtable.get(cur).neighbors.add(hashtable.get(neighbor));
				}
			}
		}
		return nodeCopy;
	}
}
