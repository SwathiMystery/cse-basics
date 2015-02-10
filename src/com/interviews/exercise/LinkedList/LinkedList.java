package com.interviews.exercise.LinkedList;

/**
 * Linked List Implementation in Java
 * 1. insertion at head , insertion at tail and at a given index.
 * 2.Read at a given index
 * 3. Delete at an index, delete a node, delete nth node from the tail, delete the list
 * 4. Reverse a list
 * 5. Cyclic
 * 6. Point of convergence if given 2 nodes if they do so.
 *
 * @author swathi on 2/8/15.
 */
public class LinkedList {
	private Node head;
	private int size;

	public LinkedList() {
		this.head = head;
		this.size = 0;
	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	// Insert at the head
	public Node insertAtHead(Node head, Object data) {
		Node tmpNode = new Node(data);
		tmpNode.setNext(head);
		this.head = tmpNode;
		this.size++;
		return head;
	}

	// Insert from the tail
	public void insertAtTail(Object data) {
		Node tmpNode = new Node(data);
		if (head == null) {
			this.head = tmpNode;
			this.size++;
		} else {
			Node curr = head;
			while (curr.getNext() != null) {
				curr = curr.getNext();
			}
			curr.setNext(tmpNode);
			this.size++;
		}
	}

	// Insert at a given index
	public void insertAtIndex(Object data, int index) {
		Node newNode = new Node(data);
		if (index <= 0) {
			System.out.println("Invalid index");
		} else if (index > size) {
			this.insertAtTail(data);
		} else {
			Node curr = head;
			Node tempNode = null;

			for (int i = 1; i < index && curr != null; i++) {
				curr = curr.getNext();
			}

			tempNode = curr;
			curr = curr.getNext();
			tempNode.setNext(newNode);
			newNode.setNext(curr);
			this.size++;
		}

	}

	// Get the data at a given index
	public Object get(int index) {
		if (index <= 0 || index > this.size)
			return null;
		Node curr = head;

		for (int i = 1; i < index & curr != null; i++) {
			curr = curr.getNext();
		}

		return curr.getData();
	}

	// Delete the node at a given index
	public void delete(int index) {
		if (index <= 0 || index > this.size) {
			System.out.println("Invalid index");
			return;
		} else if (index == 1) {
			head = head.getNext();
			this.size--;
			return;
		}
		Node curr = head;
		Node tempNode = null;

		for (int i = 1; i < index - 1 && curr.getNext() != null; i++) {
			curr = curr.getNext();
		}
		tempNode = curr;
		curr = curr.getNext();
		tempNode.setNext(curr.getNext());
		this.size--;
	}

	// Delete the node given a node
	public boolean deleteNode(Node node) {
		if (node == null || node.getNext() == null)
			return false;
		Node next = node.getNext();
		node.setData(next.getData());
		node.setNext(next.getNext());
		return true;
	}

	// Delete nth node from the end
	public Node deleteNthFromEnd(Node head, int n) {
		if (n == this.size) {
			head = head.getNext();
			return head;
		}
		Node curr = head;
		Node prev = head;
		Node temp = null;

		for (int i = 1; i <= n; i++) {
			curr = curr.getNext();
		}

		while (curr.getNext() != null) {
			curr = curr.getNext();
			prev = prev.getNext();
		}

		temp = prev;
		temp = temp.getNext();
		prev.setNext(temp.getNext());
		this.size--;
		return head;
	}

	// Delete list
	public Node deleteList(Node head) {
		head = null;
		return head;
	}

	// Reverse
	public Node reverse(Node node) {
		if (head == null)
			return head;
		if (head.getNext() == null)
			return head;

		Node curr = head;
		Node next = curr.getNext();
		Node loop = null;

		while (next != null) {
			curr.setNext(loop);
			loop = curr;
			curr = next;
			next = next.getNext();
		}

		head = curr;
		head.setNext(loop);
		return head;
	}

	public int size() {
		return this.size;
	}

	public boolean isCyclic(Node head) {
		Node slow = head;
		Node fast = head;

		boolean isCyclic = false;
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}

		if (slow == fast) {
			return true;
		} else {
			return false;
		}
	}

	public void findConvergence(Node head1, Node head2) {
		int length1 = 0;
		int length2 = 0;
		Node curr1 = head1;
		Node curr2 = head2;

		while (true) {
			if (curr1.getNext() == null) {
				break;
			}
			curr1 = curr1.getNext();
			length1++;
		}

		while (true) {
			if (curr2.getNext() == null) {
				break;
			}
			curr2 = curr2.getNext();
			length2++;
		}

		// if both curr1 and curr2 are pointing to null, they converged

		boolean isConverged = curr1 == curr2;

		if (isConverged) {
			System.out.println(" Two nodes have a convergence");

			Node longerNode = null;
			Node shorterNode = null;

			if (length1 < length2) {
				longerNode = head2;
				shorterNode = head1;
			} else {
				longerNode = head1;
				shorterNode = head2;
			}

			int difference = Math.abs(length1-length2);

			while (difference > 0) {
				longerNode = longerNode.getNext();
				difference--;
			}

			while (longerNode!=shorterNode) {
				longerNode = longerNode.getNext();
				shorterNode = shorterNode.getNext();
			}

			System.out.println("Convergent node " + longerNode.getData());
		} else {
			System.out.println("Two nodes do not converge");
		}


	}

}
