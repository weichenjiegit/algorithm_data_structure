package leetcode;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {
	public int maxPoints(Point[] points) {
		if (points.length <= 2)
			return points.length;
		int result = 0;
		for (int i = 0; i < points.length; i++) {
			Map<Double, Integer> hm = new HashMap<Double, Integer>();
			int samex = 1;
			int samep = 0;
			for (int j = 0; j < points.length; j++) {
				if (j != i) {
					if ((points[j].x == points[i].x) && (points[j].y == points[i].y)) {
						samep++;
					}
					if (points[j].x == points[i].x) {
						samex++;
						continue;
					}
					double k = (double) (points[j].y - points[i].y) / (double) (points[j].x - points[i].x);
					if (hm.containsKey(k)) {
						hm.put(k, hm.get(k) + 1);
					} else {
						hm.put(k, 2);
					}
					result = Math.max(result, hm.get(k) + samep);
				}
			}
			result = Math.max(result, samex);
		}
		return result;
	}

	public static class Point {
		int x;
		int y;

		Point() {
			x = 0;
			y = 0;
		}

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}
}
