package edu.learn.ib;

import edu.learn.ib.LL.ListNode;

public class SortList {

	static class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int x) {
			val = x;
			next = null;
		}

		ListNode(int x, ListNode nxt) {
			val = x;
			next = nxt;
		}
	}

	public static ListNode sort(ListNode A) {
		if (A == null || A.next == null) {
			return A;
		}

		ListNode slow = A, fast = A;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode first = A;
		ListNode second = slow.next;
		slow.next = null;

		first = sort(first);
		second = sort(second);

		ListNode res = null, tail = null;

		while (first != null && second != null) {
			ListNode next = null;
			if (first.val < second.val) {
				next = first;
				first = first.next;
				next.next = null;
			} else {
				next = second;
				second = second.next;
				next.next = null;
			}

			if (res == null) {
				res = next;
				tail = next;
			} else {
				tail.next = next;
				tail = next;
			}
		}

		if (first != null) {
			tail.next = first;
		} else {
			tail.next = second;
		}
		return res;
	}

	// --- insertion sort ---

	public static ListNode insertionSortList(ListNode A) {
		if (A == null || A.next == null) {
			return A;
		}

		ListNode res = A, curr = A.next;
		res.next = null;

		while (curr != null) {
			ListNode temp = curr;
			curr = curr.next;
			temp.next = null;
			res = insert(res, temp);
		}
		return res;

	}

	public static ListNode insert(ListNode A, ListNode node) {
		if (A.val >= node.val) {
			node.next = A;
			return node;
		}
		ListNode curr = A;
		while (curr.next != null && curr.next.val < node.val) {
			curr = curr.next;
		}

		node.next = curr.next;
		curr.next = node;
		return A;

	}

	// -- partition --
	public ListNode partition(ListNode A, int x) {
		if (A == null || A.next == null) {
			return A;
		}

		ListNode tempHead = new ListNode(2, A);
		ListNode curr = tempHead;
		ListNode boeh = null, boet = null;
		while (curr != null && curr.next != null) {
			int nextVal = curr.next.val;
			if (nextVal < x) {
				curr = curr.next;
			} else {
				if (boeh == null) {
					boeh = curr.next;
					boet = boeh;
					curr.next = curr.next.next;
					boet.next = null;
				} else {
					boet.next = curr.next;
					boet = boet.next;
					curr.next = boet.next;
					boet.next = null;
				}
			}
		}

		curr.next = boeh;
		return tempHead.next;

	}

	public static void main(String[] args) {
		// ListNode node5 = new ListNode(5);
		ListNode node4 = new ListNode(4);
		ListNode node3 = new ListNode(3, node4);
		ListNode node2 = new ListNode(2, node3);
		ListNode node1 = new ListNode(1, node2);
		System.out.println(reorderList(node1));

	}

	public static ListNode reorderList(ListNode A) {
		if (A == null || A.next == null) {
			return A;
		}

		ListNode slow = A, fast = A;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode first = A;
		ListNode second = slow.next;
		slow.next = null;
		second = reverse(second);
		ListNode res = null, tail = null;

		while (first != null && second != null) {
			if (res == null) {
				res = first;
				tail = first;
				first = first.next;
				tail.next = null;

				tail.next = second;
				tail = tail.next;
				second = second.next;
				tail.next = null;

			} else {
				tail.next = first;
				tail = tail.next;
				first = first.next;
				tail.next = null;

				tail.next = second;
				tail = tail.next;
				second = second.next;
				tail.next = null;
			}

		}

		if (first != null) {
			tail.next = first;
		}

		return res;
	}

	public static ListNode reverse(ListNode node) {
		if (node == null || node.next == null) {
			return node;
		}
		ListNode res = node, curr = node.next, temp = null;
		node.next = null;
		while (curr != null) {
			temp = curr.next;
			curr.next = res;
			res = curr;
			curr = temp;
		}
		return res;
	}

}
