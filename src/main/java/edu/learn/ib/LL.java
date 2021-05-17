package edu.learn.ib;

import java.util.HashMap;
import java.util.Map;

public class LL {

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

	public static void main(String[] args) {
		// ListNode node5 = new ListNode(6);
		// ListNode node4 = new ListNode(3, node5);
		ListNode node3 = new ListNode(3);
		ListNode node2 = new ListNode(2, node3);
		ListNode node1 = new ListNode(1, node2);
		System.out.println(reverseBetween(node1, 1, 2));

	}

	static class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	};

	public static RandomListNode copyRandomList(RandomListNode head) {
		if (head == null) {
			return null;
		}
		RandomListNode chead = null, ctail = null;
		RandomListNode temp = head;
		Map<RandomListNode, RandomListNode> mapping = new HashMap<>();
		while (temp != null) {
			RandomListNode copy = new RandomListNode(temp.label);
			copy.next = null;
			copy.random = null;
			mapping.put(temp, copy);

			if (chead == null) {
				chead = copy;
				ctail = copy;
			} else {
				ctail.next = copy;
				ctail = copy;
			}

			temp = temp.next;
		}

		temp = head;
		RandomListNode ctemp = chead;
		while (temp != null) {
			ctemp.random = temp.random == null ? null : mapping.get(temp.random);
			temp = temp.next;
			ctemp = ctemp.next;
		}

		return chead;

	}

	public static ListNode reverseBetween(ListNode A, int B, int C) {
		if (B == C) {
			return A;
		}

		ListNode fh = B == 1 ? null : A;
		ListNode temp = A;
		int i = 1;
		while (i <= B - 2) {
			temp = temp.next;
			i++;
		}
		ListNode ft = B == 1 ? null : temp;
		ListNode sh = B == 1 ? A : temp.next;
		temp = A;
		i = 1;
		while (i <= C - 1) {
			temp = temp.next;
			i++;
		}
		ListNode st = temp;
		ListNode th = temp.next;

		if (ft != null) {
			ft.next = null;
		}

		st.next = null;

		reverse(sh);
		if (ft != null) {
			ft.next = st;
		}
		sh.next = th;
		return fh == null ? st : fh;

	}

	public ListNode rotateRight(ListNode A, int B) {
		ListNode f = A, s = A;
		int i = 0;
		ListNode conter = A;
		int n = 0;
		while (conter != null) {
			n++;
			conter = conter.next;
		}

		B = B % n;
		if (B == 0) {
			return A;
		}
		while (i < B) {
			f = f.next;
			i++;
		}

		while (f.next != null) {
			s = s.next;
			f = f.next;
		}
		ListNode newHead = s.next;
		s.next = null;
		f.next = A;
		return newHead;

	}

	public static int isPallindrome(ListNode node) {
		ListNode slow = node, fast = node;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;

		}
		ListNode first = node;
		ListNode tbr = slow.next;
		slow.next = null;
		ListNode second = reverse(tbr);

		while (second != null) {
			if (first.val == second.val) {
				first = first.next;
				second = second.next;
			} else {
				return 0;
			}
		}

		return 1;

	}

	// -- reverse --

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

	public ListNode listCycle(ListNode A) {
		int s = 0, f = 2;
		ListNode slow = A;
		if (A.next == null || A.next.next == null) {
			return null;
		}

		ListNode fast = A.next.next;

		while (slow != fast) {
			slow = slow.next;
			s++;
			if (fast.next == null || fast.next.next == null) {
				return null;
			} else {
				fast = fast.next.next;
				f = f + 2;
			}

		}
		int n = f - s;
		slow = A;
		if (s > n) {
			for (int i = 1; i <= s - n; i++) {
				slow = slow.next;
			}
		} else if (n > s) {
			for (int i = 1; i <= n - s; i++) {
				fast = fast.next;
			}
		}

		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}

		return slow;

	}

	// -- reverse event --

	public ListNode reverseEven(ListNode A) {
		ListNode odd = A, oddLast = A, even = A.next, temp = even.next;
		odd.next = null;
		even.next = null;
		boolean isOdd = true;

		while (temp != null) {
			if (isOdd) {
				oddLast.next = temp;
				oddLast = temp;
				temp = temp.next;
				oddLast.next = null;
				isOdd = false;
			} else {
				ListNode tempNext = temp.next;
				temp.next = even;
				even = temp;
				temp = tempNext;
				isOdd = true;
			}
		}

		ListNode evenI = even;
		ListNode oddI = odd.next;
		ListNode res = odd;
		ListNode chainLast = res;
		res.next = null;
		isOdd = false;
		while (evenI != null && oddI != null) {
			if (isOdd) {
				chainLast.next = oddI;
				chainLast = oddI;
				oddI = oddI.next;
				chainLast.next = null;
				isOdd = false;
			} else {
				chainLast.next = evenI;
				chainLast = evenI;
				evenI = evenI.next;
				chainLast.next = null;
				isOdd = true;
			}
		}

		if (evenI == null) {
			chainLast.next = oddI;
		} else {
			chainLast.next = evenI;
		}

		return res;
	}

	// -- reverse K --

	public ListNode reverseList(ListNode A, int B) {
		ListNode target = A, prevTraget = null, res = null, itrRes = null;
		while (target != null) {
			itrRes = reverseK(target, B);
			if (prevTraget == null) {
				res = itrRes;
			} else {
				prevTraget.next = itrRes;
			}

			// finding next target and prev target
			prevTraget = itrRes;
			for (int i = 0; i < B - 1; i++) {
				prevTraget = prevTraget.next;
			}

			target = prevTraget.next;
		}

		return res;
	}

	public static ListNode reverseK(ListNode node, int k) {
		if (node == null) {
			return null;
		}

		if (node.next == null) {
			return node;
		}

		if (k == 1) {
			return node;
		}

		ListNode res = node, curr = node, prev = null;

		for (int i = 0; i < k - 1; i++) {
			if (curr == null) {
				break;
			}
			prev = curr;
			curr = curr.next;
			prev.next = curr.next;
			curr.next = null;
			curr.next = res;
			res = curr;
			curr = prev;
		}

		return res;
	}

	// -- delete dup 2 --

	public ListNode deleteDuplicates2(ListNode A) {

		ListNode res = null, resChainLast = null, curr = A;

		while (curr != null) {

			int currValue = curr.val;

			// no solution
			if (curr.next != null && currValue == curr.next.val) {

				while (curr != null && curr.val == currValue) {
					curr = curr.next;
				}

				if (curr == null) {
					break;
				}

			} else {
				// curr is part solution
				if (res == null) {
					res = curr;
					resChainLast = curr;
					curr = curr.next;
					resChainLast.next = null;
				} else {
					resChainLast.next = curr;
					resChainLast = resChainLast.next;
					curr = curr.next;
					resChainLast.next = null;
				}
			}

		}

		return res;
	}

	// -- delete duplicates --

	public ListNode deleteDuplicates(ListNode A) {
		// set up
		ListNode res = A;
		ListNode resChainLast = A;
		ListNode curr = A.next;
		A.next = null;

		while (curr != null) {
			int currChainValue = resChainLast.val;
			while (curr != null && curr.val == currChainValue) {
				curr = curr.next;
			}

			if (curr == null) {
				break;
			}
			resChainLast.next = curr;
			resChainLast = curr;
			curr = curr.next;
			resChainLast.next = null;

		}

		return res;

	}

	// -- merge 2 sorted lists

	public ListNode mergeTwoLists(ListNode A, ListNode B) {
		ListNode res = A.val < B.val ? A : B;
		ListNode p1 = res == A ? A.next : A;
		ListNode p2 = res == A ? B : B.next;
		ListNode curr = res;
		res.next = null;

		res.next = null;
		while (p1 != null && p2 != null) {
			if (p1.val < p2.val) {
				curr.next = p1;
				curr = curr.next;
				p1 = p1.next;
				curr.next = null;
			} else {
				curr.next = p2;
				curr = curr.next;
				p2 = p2.next;
				curr.next = null;
			}
		}

		if (p1 != null) {
			curr.next = p1;
		} else {
			curr.next = p2;
		}

		return res;
	}

}
