package edu.learn.ib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class BTPrac {
	public int maxPathSum(TreeNode A) {
		if (A == null) {
			return 0;
		}

		if (A.left == null && A.right == null) {
			return A.val;
		}

		TempRes res = getTempRes(A);
		return res.my > res.withMe ? res.my : res.withMe;

	}

	public static void main(String[] args) {
		TreeNode tn1 = new TreeNode(1);
		TreeNode tn2 = new TreeNode(2);
		TreeNode tn3 = new TreeNode(3);
		TreeNode tn4 = new TreeNode(4);
		TreeNode tn5 = new TreeNode(5);
//		TreeNode tn6 = new TreeNode(6);
//		TreeNode tn7 = new TreeNode(7);

		tn1.left = tn2;
		tn1.right = tn3;
		tn3.left = tn4;
		tn4.right = tn5;

		System.out.println(verticalOrderTraversal(tn1));

	}

	public ArrayList<ArrayList<Integer>> pathSum(TreeNode A, int B) {
		ArrayList<ArrayList<Integer>> res = pathSumInternal(A, B);
		for (ArrayList<Integer> arr : res) {
			Collections.reverse(arr);
		}
		return res;
	}

	public static ArrayList<ArrayList<Integer>> pathSumInternal(TreeNode A, int B) {

		if (A == null) {
			return new ArrayList<>();
		}

		if (A.left == null && A.right == null) {
			if (A.val == B) {
				ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

				ArrayList<Integer> l1 = new ArrayList<Integer>();
				l1.add(A.val);

				res.add(l1);
				return res;
			} else {
				return new ArrayList<>();
			}
		}

		ArrayList<ArrayList<Integer>> left = pathSumInternal(A.left, B - A.val);
		ArrayList<ArrayList<Integer>> right = pathSumInternal(A.right, B - A.val);

		left.addAll(right);

		for (ArrayList<Integer> arr : left) {
			arr.add(A.val);
		}

		return left;

	}

	public static ArrayList<Integer> diagonalTraversal(TreeNode A) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		diagonalTraversal(A, 0, map);
		ArrayList<Integer> res = new ArrayList<Integer>();
		int d = 0;
		while (true) {
			if (map.containsKey(d)) {
				res.addAll(map.get(d));
				d++;
			} else {
				break;
			}

		}
		return res;

	}

	public static void diagonalTraversal(TreeNode A, int d, Map<Integer, List<Integer>> map) {

		if (A == null) {
			return;
		}

		if (map.containsKey(d)) {
			map.get(d).add(A.val);
		} else {
			List<Integer> l = new ArrayList<>();
			l.add(A.val);
			map.put(d, l);
		}

		diagonalTraversal(A.left, d + 1, map);
		diagonalTraversal(A.right, d, map);

	}

	public static ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
		Map<Integer, ArrayList<Integer>> map = new HashMap<>();
		Pair p = new Pair();
		p.max = Integer.MIN_VALUE;
		p.min = Integer.MAX_VALUE;
		verticalOrderTraversalInternal(A, map, p);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

		for (int i = p.min; i <= p.max; i++) {
			res.add(map.get(i));
		}

		return res;

	}

	public static void verticalOrderTraversalInternal(TreeNode root, Map<Integer, ArrayList<Integer>> map, Pair p) {

		if (root == null) {
			return;
		}

		Queue<NodeWithDistance> q = new LinkedList<>();
		q.add(new NodeWithDistance(root, 0));

		while (!q.isEmpty()) {
			NodeWithDistance curr = q.poll();

			if (curr.hd > p.max) {
				p.max = curr.hd;
			}

			if (curr.hd < p.min) {
				p.min = curr.hd;
			}

			if (map.containsKey(curr.hd)) {
				map.get(curr.hd).add(curr.node.val);
			} else {
				ArrayList<Integer> l = new ArrayList<>();
				l.add(curr.node.val);
				map.put(curr.hd, l);
			}

			if (curr.node.left != null) {
				q.add(new NodeWithDistance(curr.node.left, curr.hd - 1));
			}

			if (curr.node.right != null) {
				q.add(new NodeWithDistance(curr.node.right, curr.hd + 1));
			}

		}
	}

	public static class NodeWithDistance {
		public TreeNode node;
		public int hd;

		public NodeWithDistance(TreeNode node, int hd) {
			this.node = node;
			this.hd = hd;
		}

	}

	public static class Pair {
		public int min, max;

	}

	public ArrayList<Integer> inorderTraversal(TreeNode A) {
		ArrayList<Integer> res = new ArrayList<>();
		Stack<TreeNode> s = new Stack<>();
		TreeNode temp = A;
		while (true) {
			while (temp != null) {
				s.add(temp);
				temp = temp.left;
			}

			if (s.isEmpty()) {
				return res;
			}

			temp = s.pop();
			res.add(temp.val);
			temp = temp.right;
		}
	}

	public static ArrayList<Integer> postorderTraversal(TreeNode A) {
		ArrayList<Integer> res = new ArrayList<>();
		Stack<TreeNode> s = new Stack<>();
		Stack<TreeNode> s2 = new Stack<>();
		TreeNode temp = A;
		while (true) {
			while (temp != null) {
				s.add(temp);
				temp = temp.left;
			}

			if (s.isEmpty()) {

				while (!s2.isEmpty()) {
					res.add(s2.pop().val);
				}
				return res;
			}

			temp = s.pop();
			if (temp.right == null) {
				res.add(temp.val);
				while (!s2.isEmpty() && s2.peek().right == temp) {
					temp = s2.pop();
					res.add(temp.val);
				}
				temp = null;
			} else {
				s2.push(temp);
				temp = temp.right;

			}
		}
	}

	private static TempRes getTempRes(TreeNode node) {
		if (node == null) {
			return new TempRes(Integer.MIN_VALUE, Integer.MIN_VALUE);
		}

		if (node.left == null && node.right == null) {
			return new TempRes(node.val, node.val);
		}

		TempRes left = getTempRes(node.left);
		TempRes right = getTempRes(node.right);

		int goThroughMeTemp = node.val;
		if (left.withMe > 0) {
			goThroughMeTemp = goThroughMeTemp + left.withMe;
		}

		if (right.withMe > 0) {
			goThroughMeTemp = goThroughMeTemp + right.withMe;
		}

		int my = Math.max(Math.max(left.my, right.my), goThroughMeTemp);
		int withMe = node.val + Math.max(left.withMe, right.withMe);
		return new TempRes(withMe, my);

	}

	public int kthsmallest(TreeNode A, int B) {
		Stack<TreeNode> s = new Stack<>();
		TreeNode temp = A;
		while (temp != null) {
			s.push(temp);
			temp = temp.left;
		}
		int poopedC = 0;
		while (true) {
			TreeNode popped = s.pop();
			poopedC++;
			if (poopedC == B) {
				return popped.val;
			}
			temp = popped.right;
			while (temp != null) {
				s.push(temp);
				temp = temp.left;
			}

		}
	}

	public static class TempRes {
		int withMe;
		int my;

		public TempRes(int withMe, int my) {
			this.withMe = withMe;
			this.my = my;
		}

	}

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
}
