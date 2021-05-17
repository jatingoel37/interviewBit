package edu.learn.ib;

public class FlattenBT {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		TreeNode(int x) {
			val = x;
			left = null;
			right = null;
		}
	}

	public static class PointerPair {
		public TreeNode first;
		public TreeNode last;

		public PointerPair() {
		}

		public PointerPair(TreeNode first, TreeNode last) {
			this.first = first;
			this.last = last;
		}

	}
	
	public TreeNode flatten(TreeNode a) {
		return flattenInternal(a).first;
    }

	public PointerPair flattenInternal(TreeNode a) {
		if (a == null) {
			return new PointerPair();
		}

		if (a.left == null && a.right == null) {
			return new PointerPair(a, a);
		}

		PointerPair left = flattenInternal(a.left);
		PointerPair right = flattenInternal(a.right);

		PointerPair res = new PointerPair();
		res.first = a;
		a.left = null;
		a.right = left.first != null ? left.first : right.first;
		if (left.last != null) {
			left.last.right = right.first;
		}

		res.last = right.last != null ? right.last : left.last;
		return res;
	}
}
