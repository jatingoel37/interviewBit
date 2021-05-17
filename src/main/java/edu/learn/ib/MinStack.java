package edu.learn.ib;

import java.util.Stack;

public class MinStack {

	private Stack<Integer> data;
	private Stack<Integer> min;

	public MinStack() {
		this.data = new Stack<Integer>();
		this.min = new Stack<Integer>();
	}

	public void push(int x) {
		data.push(x);
		if (min.isEmpty() || min.peek() >= x) {
			min.push(x);
		}

	}

	public void pop() {
		if (data.isEmpty()) {
			return;
		}

		int popped = data.pop();
		if (min.peek() == popped) {
			min.pop();
		}

	}

	public int top() {
		if (data.isEmpty()) {
			return -1;
		}
		return data.peek();
	}

	public int getMin() {
		if (min.isEmpty()) {
			return -1;
		}

		return min.peek();
	}

}
