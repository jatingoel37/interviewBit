package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class StackPractice {

	public static void main(String[] args) {
		System.out.println(maxSpecialProduct(Arrays.asList(1, 4, 3, 4)));
	}

	public int trap(final int[] A) {
		int n = A.length;
		if (n <= 2) {
			return 0;
		}

		int res = 0;
		int leftMax = A[0];
		int rightMax = A[n - 1];
		int lo = 1, hi = n - 2;
		while (lo <= hi) {
			int currLo = A[lo];
			int currHi = A[hi];

			if (leftMax < rightMax) {
				if (currLo >= leftMax) {
					leftMax = currLo;
				} else {
					res = res + (leftMax - currLo);
				}

				lo++;
			} else {
				if (currHi >= rightMax) {
					rightMax = currHi;
				} else {
					res = res + (rightMax - currHi);
				}
				hi--;
			}
		}
		
		return res;
	}

	public static int maxSpecialProduct(List<Integer> A) {
		Stack<Integer> s = new Stack<>();
		long res = 0, temp = 0;

		for (int i = 0; i < A.size(); i++) {
			int curr = A.get(i);
			if (s.isEmpty() || A.get(s.peek()) > curr) {
				s.push(i);
			} else if (!s.isEmpty() && A.get(s.peek()) < curr) {
				while (!s.isEmpty() && A.get(s.peek()) < curr) {
					s.pop();
					if (!s.isEmpty()) {
						temp = (long) s.peek() * (long) i;
						if (temp > res) {
							res = temp;
						}
					}
				}

				if (!s.isEmpty() && A.get(s.peek()) == curr) {
					// do nothing
				} else {
					s.push(i);
				}

			}
		}

		return (int) (res % 1000_000_007);
	}

	public static int longestValidParentheses(String A) {
		int n = A.length(), res = 0;
		Stack<Integer> s = new Stack<>();
		s.add(-1);
		for (int i = 0; i < n; i++) {
			char curr = A.charAt(i);
			if (curr == '(') {
				s.push(i);
			} else {
				if (s.peek() >= 0 && '(' == A.charAt(s.peek())) {
					s.pop();
					int tr = i - s.peek();
					if (tr > res) {
						res = tr;
					}
				} else {
					s.push(i);
				}
			}
		}
		return res;
	}

	// -- nearest smaller left --

	public static ArrayList<Integer> prevSmaller(List<Integer> A) {
		int size = A.size();
		ArrayList<Integer> res = new ArrayList<Integer>(size);
		if (size == 0) {
			return res;
		}
		if (size == 1) {
			res.add(-1);
			return res;
		}

		Stack<Integer> stack = new Stack<Integer>();

		for (int i = 0; i < size; i++) {
			int curr = A.get(i);
			while (!stack.isEmpty() && stack.peek() >= curr) {
				stack.pop();
			}

			if (stack.isEmpty()) {
				res.add(-1);
			} else {
				res.add(stack.peek());
			}

			stack.push(curr);
		}

		return res;
	}

	// --polish --

	public static int evalRPN(List<String> list) {
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < list.size(); i++) {
			String curr = list.get(i);
			if (curr.equals("+")) {
				stack.push(stack.pop() + stack.pop());
			} else if (curr.equals("-")) {
				int a = stack.pop();
				int b = stack.pop();
				stack.push(b - a);
			} else if (curr.equals("*")) {
				stack.push(stack.pop() * stack.pop());
			} else if (curr.equals("/")) {
				int a = stack.pop();
				int b = stack.pop();
				stack.push(b / a);
			} else {
				stack.push(Integer.valueOf(curr));
			}
		}

		return stack.pop();
	}

	// -- simplify path --

	public static String simplifyPath(String A) {
		String[] splitted = A.split("/");
		Stack<String> stack = new Stack<String>();

		for (int i = 1; i < splitted.length; i++) {
			String curr = splitted[i];
			if (curr.isEmpty()) {
				continue;
			}
			if (curr.equals(".")) {
				// noop
			} else if (curr.equals("..")) {
				if (!stack.isEmpty()) {
					stack.pop();
				}

			} else {
				stack.push(curr);
			}
		}

		StringBuilder sb = new StringBuilder("/");
		Stack<String> tempstack = new Stack<String>();
		while (!stack.isEmpty()) {
			tempstack.push(stack.pop());

		}

		while (!tempstack.isEmpty()) {
			sb.append(tempstack.pop());
			if (!tempstack.isEmpty()) {
				sb.append("/");
			}

		}

		return sb.toString();
	}

	// -- balance patenthesis --
	public static int solve(String A) {

		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < A.length(); i++) {
			char curr = A.charAt(i);
			if (curr == '(') {
				stack.push(curr);
			} else {
				if (stack.isEmpty()) {
					return 0;
				} else {
					stack.pop();
				}
			}
		}

		return stack.isEmpty() ? 1 : 0;
	}

	// -- redundant braces --

	public static int braces(String A) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < A.length(); i++) {
			char curr = A.charAt(i);
			if (curr != ')') {
				stack.push(curr);
			} else {
				boolean found = false;
				while (!stack.isEmpty() && stack.peek() != '(') {
					char popped = stack.pop();

					if (popped == '+' || popped == '-' || popped == '*' || popped == '/') {
						found = true;
					}
				}

				if (stack.isEmpty() || !found) {
					return 1;
				} else {
					stack.pop();
				}
			}
		}

		return 0;
	}

	// -- histogram --

	public static int largestRectangleArea2(List<Integer> A) {
		Stack<Integer> stack = new Stack<>();
		int res = 0;
		int i = 0, n = A.size();
		while (i < n) {
			int curr = A.get(i);
			if (stack.isEmpty()) {
				stack.push(i);
				i++;
			} else {
				int topI = stack.peek();
				int topN = A.get(topI);
				if (curr >= topN) {
					stack.push(i);
					i++;
				} else {
					stack.pop();
					int lhs = stack.isEmpty() ? topI : stack.peek() + 1;
					int rhs = i - 1;
					int area = topN * (rhs - lhs + 1);
					if (area > res) {
						res = area;
					}
				}
			}
		}

		while (!stack.isEmpty()) {
			int poppedIndex = stack.pop();
			int lhs = stack.isEmpty() ? poppedIndex : stack.peek() + 1;
			int rhs = A.size() - 1;
			int area = A.get(poppedIndex) * (rhs - lhs + 1);
			if (area > res) {
				res = area;
			}
		}

		return res;
	}

	public static int largestRectangleArea(List<Integer> A) {
		Stack<Integer> stack = new Stack<>();
		int res = 0;
		for (int i = 0; i < A.size(); i++) {
			int curr = A.get(i);
			if (stack.isEmpty()) {
				stack.push(i);
			} else {
				int top = A.get(stack.peek());
				if (top <= curr) {
					stack.push(i);
				} else {
					// curr is smaller, it can be rhs and lhs will be peek
					while (!stack.isEmpty() && A.get(stack.peek()) > curr) {
						int poppedIndex = stack.pop();
						int lhs = stack.isEmpty() ? 0 : stack.peek() + 1;
						int rhs = i - 1;
						int area = A.get(poppedIndex) * (rhs - lhs + 1);
						if (area > res) {
							res = area;
						}
					}
					stack.push(i);
				}
			}
		}

		while (!stack.isEmpty()) {
			int poppedIndex = stack.pop();
			int lhs = stack.isEmpty() ? 0 : stack.peek() + 1;
			int rhs = A.size() - 1;
			int area = A.get(poppedIndex) * (rhs - lhs + 1);
			if (area > res) {
				res = area;
			}
		}

		return res;
	}

}
