package test;

public class HelloWorld {

	public static void main(String args[]) {
		int temp = 1;
		System.out.println("Hello world!");
		modify(temp);
		System.out.println(temp);
	}

	private static void modify(int temp) {
		temp++;
		System.out.println("Inside method: " + temp);
	}
}
