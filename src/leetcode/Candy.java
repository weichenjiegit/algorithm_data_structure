package leetcode;

/**
 *  There are N children standing in a line. Each child is assigned a rating value.
 *  You are giving candies to these children subjected to the following requirements:
 *  Each child must have at least one candy.
 *  Children with a higher rating get more candies than their neighbors.
 *  
 *  What is the minimum candies you must give?
 *  
 */
public class Candy {
	public int candy(int[] ratings) {
		int length = ratings.length;
		if(length <= 1)
			return length;
		int minSum = 0;
		int[] candy = new int[ratings.length];
		for (int i = 0; i < length; i++) {
			candy[i] = 1;
		}
		for(int i = 1; i < length; i++){
			if(ratings[i - 1] < ratings[i])
				candy[i] = candy[i - 1] + 1;
		}
		for (int i = length - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1])
				candy[i] = Math.max(candy[i], candy[i + 1] + 1);
		}
		for (int i = 0; i < length; i++) {
			minSum += candy[i];
		}
		return minSum;
	}
	
	public static void main(String[] args){
		Candy test = new Candy();
		test.candy(new int[]{1, 2});
	}
}
