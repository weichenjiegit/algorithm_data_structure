package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings,
 * and k is a real number (floating point number). Given some queries, return the answers. If the
 * answer does not exist, return -1.0.
 * 
 * Example:
 * 
 * Given a / b = 2.0, b / c = 3.0.
 * 
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * 
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * 
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string,
 * string>> queries , where equations.size() == values.size(), and the values are positive. This
 * represents the equations. Return vector<double>.
 * 
 * According to the example above:
 * 
 * equations = [ ["a", "b"], ["b", "c"] ],
 * 
 * values = [2.0, 3.0],
 * 
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * 
 * The input is always valid. You may assume that evaluating the queries will result in no division
 * by zero and there is no contradiction.
 */
public class EvaluateDivision {
	/**
	 * For each value pair, for example a/b = 3.0, toss <b, 3.0> into a's map and also <a, 1.0/3.0>
	 * into b's map. Then use standard dfs to search for the query. For backtracking (i.e., avoiding
	 * the condition of searching back to itself), before every dfs delete the entry and put it back
	 * after the search on current entry is done. The value of query is just product along the path,
	 * which can be done through dfs process.
	 */
	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
		double[] result = new double[queries.length];
		if (equations == null || equations.length == 0 || equations[0].length != 2 || equations.length != values.length)
			return result;
		Map<String, Map<String, Double>> map = new HashMap<>();
		int valueIndex = 0;
		for (String[] equation : equations) {
			String exp1 = equation[0];
			String exp2 = equation[1];

			map.putIfAbsent(exp1, new HashMap<>());
			map.putIfAbsent(exp2, new HashMap<>());

			map.get(exp1).put(exp2, values[valueIndex]);

			map.get(exp2).put(exp1, 1.0 / values[valueIndex]);

			valueIndex++;
		}

		for (int i = 0; i < queries.length; i++) {
			String q1 = queries[i][0];
			String q2 = queries[i][1];
			result[i] = dfs(map, q1, q2);
		}
		return result;
	}

	private double dfs(Map<String, Map<String, Double>> map, String start, String end) {
		if (!map.containsKey(start))
			return -1.0;
		if (map.get(start).containsKey(end))
			return map.get(start).get(end);

		Map<String, Double> subMap = map.remove(start);
		double currentVal = -1.0;
		for (String str : subMap.keySet()) {
			double result = dfs(map, str, end);
			if (result != -1.0) {
				currentVal = subMap.get(str) * result;
				break;
			}
		}
		map.put(start, subMap);
		return currentVal;
	}
}
