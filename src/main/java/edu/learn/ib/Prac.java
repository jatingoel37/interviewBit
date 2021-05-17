package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Prac {

	public static void main(String[] args) {
		nextGreater(new int[] { 4, 12, 5, 8 });
	}

	public static String toBinary(int n) {

		if (n == 0) {
			return "0";
		}

		int temp = n;
		StringBuilder sb = new StringBuilder();

		while (temp > 0) {
			sb.append(temp % 2);
			temp = temp / 2;
		}

		return sb.reverse().toString();
	}

	public static int sumTill1(int n) {
		if (n / 10 == 0) {
			return n;
		}

		int sum = 0, temp = n;
		while (temp > 0) {
			sum = sum + temp % 10;
			temp = temp / 10;
		}

		return sumTill1(sum);
	}

	private static int[] nextGreater(int[] arr) {
		int size = arr.length;
		int[] res = new int[2 * size];
		Stack<Integer> stack = new Stack<>();

		for (int i = 2 * size - 1; i >= 0; i--) {
			int curr = arr[i % size];
			while (!stack.isEmpty() && stack.peek() <= curr) {
				stack.pop();
			}

			if (stack.isEmpty()) {
				res[i] = -1;
				stack.push(curr);
			} else {
				res[i] = stack.peek();
				stack.push(curr);
			}
		}
		System.out.println(new ArrayList<>(Arrays.asList(res)));
		return res;
	}

}
