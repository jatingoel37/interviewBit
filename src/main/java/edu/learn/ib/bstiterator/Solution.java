package edu.learn.ib.bstiterator;

import java.util.Stack;

public class Solution {

	private final Stack<TreeNode> s;

	public Solution(TreeNode root) {
		this.s = new Stack<>();
		TreeNode temp = root;
		while (temp != null) {
			s.push(temp);
			temp = temp.left;
		}

	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !s.isEmpty();
	}

	/** @return the next smallest number */
	public int next() {
		TreeNode res = s.pop();

		TreeNode temp = res.right;
		while (temp != null) {
			s.push(temp);
			temp = temp.left;
		}

		return res.val;

	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

}