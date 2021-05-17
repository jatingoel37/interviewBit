package edu.learn.ib;

public class LCA {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
			left = null;
			right = null;
		}
	}

	public static class LCAW {
		boolean firstFound, secondFound;
		TreeNode lca;

		public LCAW() {
		}

		public LCAW(boolean firstFound, boolean secondFound, TreeNode lca) {
			this.firstFound = firstFound;
			this.secondFound = secondFound;
			this.lca = lca;
		}

	}

	public int lca(TreeNode A, int B, int C) {
		LCAW curr = lcaInternal(A, B, C);
		if (curr.lca != null) {
			return curr.lca.val;
		}
		return -1;
	}

	public LCAW lcaInternal(TreeNode A, int B, int C) {
		if (A == null) {
			return new LCAW();
		}

		LCAW left = lcaInternal(A.left, B, C);
		if (left.lca != null) {
			return left;
		}

		LCAW right = lcaInternal(A.right, B, C);
		if (right.lca != null) {
			return right;
		}

		boolean firstFound = left.firstFound || right.firstFound || A.val == B;
		boolean secondFound = left.secondFound || right.secondFound || A.val == C;

		return new LCAW(firstFound, secondFound, firstFound && secondFound ? A : null);

	}
}
