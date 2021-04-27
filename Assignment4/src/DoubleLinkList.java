
//-----------------------------------------------
//Assignment 4
//Programming Question DoubleLinkList Class
//Written by Jordan Chan Kum Sang- 40125997
//-----------------------------------------------
/**
* Name: Jordan Chan Kum Sang - 40125997
* Section: COMP 352 Section FF
* Assignment 4
* Due Date: December 4, 2020
*/


public class DoubleLinkList {

	// Node Class
	private static class Node {
		private String key;
		private Student value;

		private Node prev;

		private Node next;

		public Node(String key, Student value, Node p, Node n) {
			this.key = key;
			this.value = value;
			prev = p;
			next = n;
		}

		/**
		 * 
		 * @return key of node
		 */
		public String getKey() {
			return key;
		}

		/**
		 * 
		 * @return The Student of node
		 */
		public Student getValue() {
			return value;
		}

		/**
		 * 
		 * @return Previous node
		 */
		public Node getPrev() {
			return prev;
		}

		/**
		 * 
		 * @return Next node
		 */
		public Node getNext() {
			return next;
		}

		/**
		 * 
		 * @param p Previous node
		 */
		public void setPrev(Node pre) {
			prev = pre;
		}

		/**
		 * 
		 * @param n NExt node
		 */
		public void setNext(Node nex) {
			next = nex;
		}

	} 
	
	//Private Attributes
	private Node head;
	private Node tail;
	private int size = 0;

	/**
	 * Create empty list
	 */
	public DoubleLinkList() {
		head = new Node(null, null, null, null);
		tail = new Node(null, null, head, null);
		head.setNext(tail);
	}

	/**
	 * 
	 * @return Size of linked list
	 */
	public int size() {
		return size;
	}

	/**
	 * 
	 * @return If linked list is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 
	 * @return First element in list
	 */
	public String firstKey() {
		if (isEmpty())
			return null;
		return head.getNext().getKey(); 
	}

	/**
	 * 
	 * @return Last element in list
	 */
	public String lastKey() {
		if (isEmpty())
			return null;
		return tail.getPrev().getKey(); 
	}

	/**
	 * 
	 * @return First Student in list
	 */
	public Student firstValue() {
		if (isEmpty())
			return null;
		return head.getNext().getValue(); 
	}

	/**
	 * 
	 * @return Last Student in list
	 */
	public Student lastValue() {
		if (isEmpty())
			return null;
		return tail.getPrev().getValue(); 
	}

	/**
	 * 
	 * @param key   SIDC
	 * @param value Student of SIDC Adds this element to front of list
	 */
	public void addFirst(String key, Student value) {
		addBetween(key, value, head, head.getNext()); // place just after the header
	}

	/**
	 * 
	 * @param key   SIDC
	 * @param value Student of VIN Adds this element to end of list
	 */
	public void addLast(String key, Student value) {
		addBetween(key, value, tail.getPrev(), tail); // place just before the trailer
	}

	/**
	 * Removes and return the first element in the list.
	 */
	public void removeFirst() {
		if (isEmpty())
			return; // nothing to remove
		remove(head.getNext()); // first element is beyond header
	}

	/**
	 * Removes and returns the last element in the list.
	 */
	public void removeLast() {
		if (isEmpty())
			return; // nothing to remove
		remove(tail.getPrev());
	}

	/**
	 * 
	 * @param key         SIDC to be added
	 * @param value       Student to be added
	 * @param predecessor Previous node
	 * @param successor   Next node Adds this elements between nodes
	 */
	private void addBetween(String key, Student value, Node pred, Node succ) {
		// Create a new node
		Node newest = new Node(key, value, pred, succ);

		pred.setNext(newest);
		succ.setPrev(newest);
		// Increment size
		size++;
	}

	/**
	 * Removes a node
	 * 
	 * @param node to be removed
	 */
	private void remove(Node node) {
		Node predecessor = node.getPrev();
		Node successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;

	}

	/**
	 * 
	 * @param key The SIDC to be checked for
	 * @return If the SIDC is found
	 */
	public boolean contains(String key) {
		int i = 1;
		boolean flag = false;
		Node current = head;

		// Check if list is empty
		if (head == null) {
			System.out.println("List is empty");
			return false;
		}
		while (current != null && current.key != null) {
			// Compare value to be searched with every node in the doubly linked list
			if (current.key.equals(key)) {
				flag = true;
				break;
			}
			current = current.next;
			i++;
		}

		return (flag);
	}

	/**
	 * 
	 * @return The doubly linked list lexicographically sorted by SIDC 
	 */
	public DoubleLinkList sortList() {
		Node current = null, index = null;
		String key_tempo;
		Student value_tempo;

		// Check if list is empty
		if (head == null) {
			return this;
		} else {
			for (current = head.next; current.next != null && current.key != null; current = current.next) {
				for (index = current.next; index != null && index.key != null; index = index.next) {

					if (current.getKey().compareTo(index.getKey()) > 0) {
						key_tempo = current.getKey();
						value_tempo = current.getValue();

						current.key = index.getKey();
						current.value = index.getValue();

						index.key = key_tempo;
						index.value = value_tempo;

					}

				}
			}
			return this;
		}
	}

	/**
	 * 
	 * @param n The key whose node will be deleted
	 * @return True, if deletion was success
	 */
	public boolean deleteKey(String n) {
		// list is NULL or empty key is given
		if ((head == null && head.next == null) || n.isEmpty())
			return false;

		Node current = head;
		int i;

		for (i = 0; current != null && i < size; i++) {
			current = current.next;
			if (current.getKey().equals(n)) {
				break;
			}
		}

		if (current == null)
			return false;

		// Delete node associated with key
		remove(current);
		return true;

	}

	/**
	 * 
	 * @param key The SIDC whose value will be returned
	 * @return The Student with that key
	 */
	public Student getValueFromKey(String key) {
		Node current = head;

		if (head == null && current.next == null) {
			return null;
		} else {
			for (current = head.next; current.next != null; current = current.next) {

				if (current.getKey().equals(key)) {
					return (Student) current.getValue();
				}
			}
			return null;

		}
	}

	/**
	 * 
	 * @param key SIDC
	 * @return The next SIDC in the list
	 */
	public String nextKey(String key) {
		Node current = head;
		if (head == null && current.next == null)
			return null;
		else {
			for (current = head.next; current.next != null; current = current.next) {
				if (current.getKey().equals(key)) {
					if (current.next != null)
						return current.next.getKey();

				}
			}
		}
		return null;
	}
	
	public void addHere(String k, Student v) {
		Node current = head;
		for (current = head.next; current.next != null; current = current.next) {
			if (k.compareTo(current.key)<0) {
				this.addBetween (k,v,current.prev,current);
				break;
			}
		}
	}
	

	/**
	 * 
	 * @param key SIDC
	 * @return The previous SIDC in the list
	 */
	public String prevKey(String key) {
		Node current = tail;
		if (tail == null)
			return null;
		else {
			for (current = tail.prev; current.prev != null; current = current.prev) {
				if (current.getKey().equals(key)) {
					if (current.prev != null)
						return current.prev.getKey();

				}
			}
		}
		return null;
	}



	/**
	 * 
	 * @return Lexicographically sorted list
	 */
	public LinkedList<String> allKeys() {
		LinkedList<String> sorted = new LinkedList<String>();

		Node current = tail;
		if (tail == null)
			return null;
		else {
			for (current = head; current.next != null; current = current.next) {
				if (current.key != null)
					sorted.add(current.getKey());
			}

		}
		return sorted;

	}
}
