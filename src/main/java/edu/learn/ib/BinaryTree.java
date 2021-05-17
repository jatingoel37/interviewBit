package edu.learn.ib;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class BinaryTree {

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

	public static void main(String[] args) {
		TreeNode node = buildTree(asList(2, 1, 3), asList(2, 3, 1));
		System.out.println(rightView(node));
	}

	public static ArrayList<Integer> rightView(TreeNode A) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Deque<TreeNode> deque = new LinkedList<>();
		deque.add(A);
		deque.add(null);

		while (!deque.isEmpty()) {
			TreeNode popped = deque.removeFirst();
			if (popped != null) {
				if (deque.isEmpty() || deque.peekFirst() == null) {
					res.add(popped.val);
				}
				if (popped.left != null) {
					deque.add(popped.left);
				}

				if (popped.right != null) {
					deque.add(popped.right);
				}

			} else if (!deque.isEmpty()) {
				deque.add(null);
			}

		}

		return res;
	}

	public int minDepth(TreeNode A) {
		return minDepthInternal(A);
	}

	public int minDepthInternal(TreeNode A) {
		if (A == null) {
			return -1;
		}

		if (A.left == null && A.right == null) {
			return 1;
		}

		int left = minDepthInternal(A.left);
		int right = minDepthInternal(A.right);

		if (left > 0 && right > 0) {
			return Math.min(left, right) + 1;
		}

		return Math.max(left, right) + 1;

	}

	public static TreeNode buildTree(List<Integer> inorder, List<Integer> postOrder) {
		Map<Integer, Integer> mapping = new HashMap<>();
		int n = inorder.size();
		for (int i = 0; i < n; i++) {
			mapping.put(inorder.get(i), i);
		}

		return buildTreePostorder(postOrder, 0, n - 1, inorder, 0, n - 1, mapping);

	}

	public static TreeNode buildTreePostorder(List<Integer> postOrder, int ps, int pe, List<Integer> inorder, int is,
			int ie, Map<Integer, Integer> mapping) {
		if (ps == pe) {
			return new TreeNode(postOrder.get(ps));
		}

		if (ps > pe) {
			return null;
		}

		TreeNode root = new TreeNode(postOrder.get(pe));

		int rootInInorder = mapping.get(postOrder.get(pe));
		int leftCount = rootInInorder - is;
		int rightCount = ie - rootInInorder;

		if (leftCount > 0) {
			root.left = buildTreePostorder(postOrder, ps, ps + leftCount - 1, inorder, is, rootInInorder - 1, mapping);
		}

		if (rightCount > 0) {
			root.right = buildTreePostorder(postOrder, ps + leftCount, pe - 1, inorder, rootInInorder + 1, ie, mapping);
		}

		return root;
	}

	public static TreeNode buildTreePreorder(List<Integer> A, List<Integer> B) {
		Map<Integer, Integer> mapping = new HashMap<>();
		int n = B.size();
		for (int i = 0; i < n; i++) {
			mapping.put(B.get(i), i);
		}

		return buildTreePreorder(A, 0, n - 1, B, 0, n - 1, mapping);

	}

	public static TreeNode buildTreePreorder(List<Integer> preorder, int ps, int pe, List<Integer> inorder, int is,
			int ie, Map<Integer, Integer> mapping) {
		if (ps == pe) {
			return new TreeNode(preorder.get(ps));
		}

		if (ps > pe) {
			return null;
		}

		TreeNode root = new TreeNode(preorder.get(ps));

		int rootInInorder = mapping.get(preorder.get(ps));
		int leftCount = rootInInorder - is;
		int rightCount = ie - rootInInorder;

		if (leftCount > 0) {
			root.left = buildTreePreorder(preorder, ps + 1, ps + leftCount, inorder, is, rootInInorder - 1, mapping);
		}

		if (rightCount > 0) {
			root.right = buildTreePreorder(preorder, ps + leftCount + 1, pe, inorder, rootInInorder + 1, ie, mapping);
		}

		return root;
	}

	public int isSameTree(TreeNode A, TreeNode B) {
		return isSameTreeInternal(A, B) ? 1 : 0;
	}

	public int maxDepthInternal(TreeNode A) {
		if (A == null) {
			return 0;
		}

		if (A.left == null && A.right == null) {
			return 1;
		}

		return Math.max(maxDepthInternal(A.left), maxDepthInternal(A.right)) + 1;
	}

	public boolean isSameTreeInternal(TreeNode A, TreeNode B) {
		if (A == null && B == null) {
			return true;
		}

		if ((A == null && B != null) || (B == null && A != null)) {
			return false;
		}

		if (A.val != B.val) {
			return false;
		}

		return isSameTreeInternal(A.left, B.left) && isSameTreeInternal(A.right, B.right);
	}

	public ArrayList<Integer> cousin(TreeNode A, int B) {
		ArrayList<Integer> res = new ArrayList<>();
		if (A == null || A.val == B) {
			return res;
		}

		Deque<TreeNode> q = new LinkedList<>();
		q.add(A);
		q.add(null);

		boolean found = false, skipRight = false;

		while (!q.isEmpty()) {
			TreeNode removed = q.removeFirst();
			if (found) {
				while (removed != null) {
					if (removed.left != null) {
						q.add(removed.left);
					}

					if (removed.right != null) {
						q.add(removed.right);
					}

					if (!q.isEmpty()) {
						removed = q.removeFirst();
					}
				}

				while (!q.isEmpty()) {
					res.add(q.removeFirst().val);
				}
				return res;

			}

			// not found
			if (removed == null) {
				q.add(null);
				continue;
			}

			if (removed.left != null) {
				if (removed.left.val != B) {
					q.add(removed.left);
				} else {
					found = true;
					skipRight = true;
				}
			}

			if (removed.right != null) {
				if (skipRight) {
					// do nothing
				} else {
					if (removed.right.val != B) {
						q.add(removed.right);
					} else {
						found = true;
						if (removed.left != null) {
							q.removeLast();
						}
					}
				}
			}

		}

		return res;
	}

	public ArrayList<Integer> solve(TreeNode A, int B) {
		ArrayList<Integer> res = findPathToB(A, B);
		Collections.reverse(res);
		return res;
	}

	public static ArrayList<Integer> findPathToB(TreeNode A, int B) {
		if (A == null) {
			return null;
		}

		ArrayList<Integer> res;
		if (A.val == B) {
			res = new ArrayList<>();
			res.add(B);
			return res;
		}

		ArrayList<Integer> left = findPathToB(A.left, B);
		if (left != null) {
			left.add(A.val);
			return left;
		}

		ArrayList<Integer> right = findPathToB(A.right, B);
		if (right != null) {
			right.add(A.val);
			return right;
		}

		return null;

	}

	public TreeNode removeHalf(TreeNode A) {
		if (A == null) {
			return null;
		}

		if (A.left == null && A.right == null) {
			return A;
		}

		TreeNode leftRemoved = removeHalf(A.left);
		TreeNode rightRemoved = removeHalf(A.right);

		if (leftRemoved != null && rightRemoved != null) {
			A.left = leftRemoved;
			A.right = rightRemoved;
			return A;
		}

		if (leftRemoved == null) {
			return rightRemoved;
		}

		return leftRemoved;

	}

	public int isBalancedInternal(TreeNode A) {
		if (A == null) {
			return 0;
		}
		if (A.left == null && A.right == null) {
			return 1;
		}

		int leftResult = isBalancedInternal(A.left);
		if (leftResult < 0) {
			return -1;
		}

		int rightResult = isBalancedInternal(A.right);
		if (rightResult < 0) {
			return -1;
		}

		int diff = Math.abs(rightResult - leftResult);
		if (diff <= 1) {
			return Math.max(rightResult, leftResult) + 1;
		}

		return -1;

	}

	public ArrayList<Integer> preorderTraversal(TreeNode A) {
		List<Integer> res = preorderTraversalInternal(A);
		return new ArrayList<Integer>(res);
	}

	public List<Integer> preorderTraversalInternal(TreeNode A) {

		if (A == null) {
			return Collections.emptyList();
		}
		List<Integer> res = new ArrayList<Integer>();
		res.add(A.val);
		res.addAll(preorderTraversalInternal(A.left));
		res.addAll(preorderTraversalInternal(A.right));
		return res;

	}

	public static int t2Sum(TreeNode A, int B) {
		Stack<TreeNode> pre = new Stack<>();
		Stack<TreeNode> post = new Stack<>();
		TreeNode pren = A, postn = A;
		while (pren != null) {
			pre.push(pren);
			pren = pren.left;
		}

		while (postn != null) {
			post.push(postn);
			postn = postn.right;
		}

		while (pre.peek().val < post.peek().val) {
			int sum = pre.peek().val + post.peek().val;
			if (sum == B) {
				return 1;
			} else if (sum < B) {
				TreeNode popped = pre.pop();
				pren = popped.right;
				while (pren != null) {
					pre.push(pren);
					pren = pren.left;
				}
			} else {

				TreeNode popped = post.pop();
				postn = popped.left;
				while (postn != null) {
					post.push(postn);
					postn = postn.right;
				}

			}
		}

		return 0;

	}

	public int hasPathSum(TreeNode A, int B) {
		return hasPathSumInternal(A, B);
	}

	public int hasPathSumInternal(TreeNode A, int B) {
		if (A == null) {
			return 0;
		}

		if (A.left == null && A.right == null) {
			return A.val == B ? 1 : 0;
		}

		int leftPossible = hasPathSumInternal(A.left, B - A.val);
		if (leftPossible == 1) {
			return 1;
		}

		int rightPossible = hasPathSumInternal(A.right, B - A.val);
		return rightPossible;
	}

	public int sumNumbers(TreeNode A) {

		return (int) (sumNumbersInternal(A, 0) % 1003);
	}

	public static long sumNumbersInternal(TreeNode A, long B) {
		if (A == null) {
			return 0;
		}

		long num = 10 * B + A.val;
		if (A.left == null && A.right == null) {
			return num;
		}

		long left = sumNumbersInternal(A.left, num);
		long right = sumNumbersInternal(A.right, num);

		return left + right;
	}

	public int isSymmetric(TreeNode A) {
		return isSymmetricInternal(A.left, A.right) ? 1 : 0;
	}

	public boolean isSymmetricInternal(TreeNode A, TreeNode B) {
		if (A == null & B == null) {
			return true;
		}

		if ((A == null && B != null) || (A != null && B == null)) {
			return false;
		}

		if (A.val != B.val) {
			return false;
		}

		return isSymmetricInternal(A.left, B.right) && isSymmetricInternal(A.right, B.left);

	}

	public TreeNode invertTreeInternal(TreeNode A) {
		if (A == null) {
			return null;
		}
		if (A.left == null && A.right == null) {
			return A;
		}
		TreeNode left = A.left;
		TreeNode right = A.right;
		A.left = invertTreeInternal(right);
		A.right = invertTreeInternal(left);
		return A;
	}

}
