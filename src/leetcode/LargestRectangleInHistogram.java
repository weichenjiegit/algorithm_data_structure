package leetcode;

/**
 * Given n non-negative integers representing the histogram's bar height
 * where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 * 
 * Above is a histogram where width of each bar is 1, given height =[2,1,5,6,2,3].
 * 
 * The largest rectangle is shown in the shaded area, which has area =10 unit.
 * 
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 * 
 * http://blog.csdn.net/violet_program/article/details/9125221
 */
public class LargestRectangleInHistogram {
	/*
	 * 此解法的核心思想为：一次性计算连续递增的区间的最大面积，并且考虑完成这个区间之后，考虑其前、后区间的时候，
	 * 不会受到任何影响。也就是这个连续递增区间的最小高度大于等于其前、后区间。
	 * 
	 * 这个方法非常巧妙，最好通过一个图来理解： 假设输入直方图为：int[] height = {2,7,5,6,4}.
	 * 这个方法运行的时候，当遇到height[2] == 5的时候，发现其比之前一个高度小，
	 * 则从当前值（5）开始，向左搜索比当前值小的值。当搜索到最左边（2）时，比5小， 此时计算在height[0]和height[2]之间的最大面积，
	 * 注意不包括height[0]和和height[2]。 height[1]以红色标出的这个区域就被计算完成。同样的方法，计算出绿色和粉色的面积。
	 * 
	 * 因此这个方法需要使用两个栈。第一个栈为高度栈heightStack，用于记录还没有被计算过的连续递增的序列的值。
	 * 第二个栈为下标栈indexStack，用于记录高度栈中对应的每一个高度的下标，以计算宽度。
	 * 
	 * 算法具体执行的步骤为：
	 * 
	 * 若heightStack为空或者当前高度大于heightStack栈顶，
	 * 则当前高度和当前下标分别入站。所以heightStack记录了一个连续递增的序列。
	 * 若当前高度小于heightStack栈顶，heightStack和indexStack出栈， 直到当前高度大于等于heightStack栈顶。
	 * 出栈时，同时计算区间所形成的最大面积。注意计算完之后，当前值入栈的时候， 其对应的下标应该为最后一个从indexStack出栈的下标。
	 * 比如height[2]入栈时，其对应下标入栈应该为1，而不是其本身的下标2。
	 * 如果将其本身下标2入栈，则计算绿色区域的最大面积时，会忽略掉红色区域。
	 */
	// O(n) using two stacks
	public int largestRectangleArea(int[] height) {
		int area = 0;
		java.util.Stack<Integer> heightStack = new java.util.Stack<>();
		java.util.Stack<Integer> indexStack = new java.util.Stack<>();
		for (int i = 0; i < height.length; i++) {
			if (heightStack.empty() || heightStack.peek() <= height[i]) {
				heightStack.push(height[i]);
				indexStack.push(i);
			} else if (heightStack.peek() > height[i]) {
				int j = 0;
				while (!heightStack.empty() && heightStack.peek() > height[i]) {
					j = indexStack.pop();
					int currArea = (i - j) * heightStack.pop();
					if (currArea > area) {
						area = currArea;
					}
				}
				heightStack.push(height[i]);
				indexStack.push(j);
			}
		}
		while (!heightStack.empty()) {
			int currArea = (height.length - indexStack.pop())
					* heightStack.pop();
			if (currArea > area) {
				area = currArea;
			}
		}
		return area;
	}

	/*
	 * 在网上发现另外一个使用一个栈的O(n)解法，代码非常简洁，栈内存储的是高度递增的下标. 对于每一个直方图高度，分两种情况。
	 * 1：当栈空或者当前高度大于栈顶下标所指示的高度时，当前下标入栈。否则， 2：当前栈顶出栈，并且用这个下标所指示的高度计算面积。
	 * 而这个方法为什么只需要一个栈呢？因为当第二种情况时，for循环的循环下标回退，
	 * 也就让下一次for循环比较当前高度与新的栈顶下标所指示的高度，注意此时的栈顶已经改变由于之前的出栈。
	 */
	// O(n) using one stack
	public int largestRectangleArea2(int[] height) {
		int area = 0;
		java.util.Stack<Integer> stack = new java.util.Stack<>();
		for (int i = 0; i < height.length; i++) {
			if (stack.empty() || height[stack.peek()] < height[i]) {
				stack.push(i);
			} else {
				int start = stack.pop();
				int width = stack.empty() ? i : i - stack.peek() - 1;
				area = Math.max(area, height[start] * width);
				i--;
			}
		}
		while (!stack.empty()) {
			int start = stack.pop();
			int width = stack.empty() ? height.length : height.length
					- stack.peek() - 1;
			area = Math.max(area, height[start] * width);
		}
		return area;
	}
	
	public static void main(String[] args){
		LargestRectangleInHistogram test = new LargestRectangleInHistogram();
		test.largestRectangleArea(new int[]{2, 7, 5, 6, 4});
	}
}
