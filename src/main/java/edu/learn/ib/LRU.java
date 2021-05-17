package edu.learn.ib;

import java.util.HashMap;
import java.util.Map;

public class LRU {

	public static void main(String[] args) {
		LRU lru = new LRU(1);
		System.out.println(lru.get(11));
		System.out.println(lru.get(1));
		lru.set(10, 13);
		lru.set(14, 8);
		lru.set(3, 9);
		lru.set(2, 3);
		System.out.println(lru.get(5));
		System.out.println(lru.get(6));
		lru.set(10, 5);
		lru.set(15, 13);
		lru.set(6, 7);
		lru.set(9, 6);
		System.out.println(lru.get(6)); // returns 12
	}

	static class DoubleLinkNode {
		public int val;
		public int key;
		public DoubleLinkNode prev;
		public DoubleLinkNode next;

		public DoubleLinkNode(int val, int key, DoubleLinkNode prev, DoubleLinkNode next) {
			this.val = val;
			this.key = key;
			this.prev = prev;
			this.next = next;
		}

	}

	private int capacity;
	private Map<Integer, DoubleLinkNode> mapping;
	private DoubleLinkNode head, tail;

	public LRU(int capacity) {
		this.capacity = capacity;
		this.mapping = new HashMap<>(capacity);
	}

	public int get(int key) {
		if (!mapping.containsKey(key)) {
			return -1;
		} else {
			DoubleLinkNode node = mapping.get(key);
			int res = node.val;
			makeNodeTail(node);
			return res;
		}
	}

	private void makeNodeTail(DoubleLinkNode node) {
		if (node == tail) {
			return;
		}

		if (node == head) {
			head = node.next;
		}

		if (node.prev != null) {
			node.prev.next = node.next;
		}

		if (node.next != null) {
			node.next.prev = node.prev;
		}

		node.next = null;
		node.prev = null;
		tail.next = node;
		node.prev = tail;
		tail = node;

	}

	public void set(int key, int value) {
		if (!mapping.containsKey(key)) {

			if (mapping.size() >= capacity) {
				createSpace();
			}

			DoubleLinkNode node = new DoubleLinkNode(value, key, tail, null);
			if (head == null) {
				head = node;
				tail = node;
			} else {
				tail.next = node;
				tail = node;
			}
			mapping.put(key, node);

		} else {
			DoubleLinkNode node = mapping.get(key);
			node.val = value;
			makeNodeTail(node);
		}
	}

	private void createSpace() {
		DoubleLinkNode tbr = head;
		mapping.remove(tbr.key);
		head = tbr.next;
		if(head!=null) {
			head.prev = null;
		}
		

		tbr.next = null;
	}
}
