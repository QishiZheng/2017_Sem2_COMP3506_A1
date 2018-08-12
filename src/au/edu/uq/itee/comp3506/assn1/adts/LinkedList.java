package au.edu.uq.itee.comp3506.assn1.adts;

import au.edu.uq.itee.comp3506.assn1.gameworld.GameObject;

/**
 * Implementation of Doubly Linked List.
 * 
 * Memory Usage Efficiency: O(n), the memory is O(n) if we create a size n linked
 * list.
 * 
 * Justification: This CDT class is implemented using doubly linked list. Singly
 * linked list was considered, but when implementing getPrevious() and insert() in 
 * singly linked list will result less run-time efficient since it has to iterate over
 * the whole list then wrap around to get the previous node. The run-time efficiency 
 * of getPrevious() and insert() would be O(n) in singly linked list. But using doubly
 * linked list, it is much more efficient to do getPreviosu() and insert().
 *  
 *  
 * Reference: 
 * Goodrich, M., Tamassia, R. and Goldwasser, M. (2014). Data structures and 
 * algorithms in Java. USA: Wiley, pp.135-137.
 * 
 * 
 * 
 * @author Qishi Zheng<Student No. 43759453>
 *
 * @param <T>
 */
public class LinkedList<T> implements GameList<T> {

	/**
	 * Nested Node class for LinkedList. 
	 * Most of the implementation of this class is from textbook, pp. 135-137
	 * 
	 * @param
	 *
	 */
	private static class Node<T> {
		// reference to the item stored in the node
		private Object item;
		// reference to the previous node in the list
		private Node<T> prev;
		// reference to next node in the list
		private Node<T> next;
		
		/**
		 * Constructor for node.
		 * 
		 * @param item  reference to the item stored in the node
		 * @param prev reference to previous node
		 * @param next reference to next node
		 */
		private Node(Object item, Node<T> prev, Node<T> next) {
			this.item = item;
			this.prev = prev;
			this.next = next;
		}

		/**
		 * Get the item stored at the node
		 * 
		 * @return the item stored at the node
		 */
		private T getItem() {
			return (T) item;
		}

		/**
		 * Get the previous node of this node
		 * 
		 * @return the previous node of this node
		 */
		private Node<T> getPrev() {
			return prev;
		}

		/**
		 * Set the previous node to the given node
		 * 
		 * @param n
		 *            given node to be added as the previous node
		 */
		private void setPrev(Node<T> p) {
			prev = p;
		}

		/**
		 * Get the next node of this node
		 * 
		 * @return the next node of this node
		 */
		private Node<T> getNext() {
			return next;
		}

		/**
		 * Set the next node to the given node
		 * 
		 * @param n
		 *            given node to be added as the next node
		 */
		private void setNext(Node<T> n) {
			next = n;
		}
	}
	// End of Node class implementation-------------------------------

	// instance variables for linkedList
	private Node<T> header; // header sentinel, the first node of the linked list
	private Node<T> trailer; // trailer sentinel, the last node of the linked list
	private int size = 0; // the size of the list(the number of nodes in the list)
	private Node<T> cursor; // reference to an specific element in the list

	/**
	 * 
	 * Construct a new empty linked list.
	 *
	 * Run-time Efficiency: O(c), where c is constant as the max number of 
	 * operations executed is 3.
	 */
	public LinkedList() {
		// create header
		header = new Node<T>(null, null, null);
		// create trailer, trailer is proceeded by header
		trailer = new Node<T>(null, header, null);
		// header is followed by trailer
		header.setNext(trailer);

		// cursor = new Node<T>(null, null, null);
	}

	/**
	 * Add an item to the end of the linked list. If the list is empty, then add
	 * the item to the head, if not empty. add to the end. The cursor will refer
	 * to the newly added item.
	 * 
	 * Run-time Efficiency: O(c), where c is constant as the max number of 
	 * operations executed is 8.
	 * 
	 * 
	 * @param item
	 *            The item to be added to the list.
	 */
	public void addToEnd(T item) {
		if (isEmpty()) {
			// add the item to the node after header
			Node<T> newNode = new Node<T>(item, header, trailer);
			header.setNext(newNode);
			trailer.setPrev(newNode);
			cursor = newNode;
			size++;
		} else {
			// add the given item just before trailer
			Node<T> newNode = new Node<T>(item, trailer.getPrev(), trailer);
			trailer.getPrev().setNext(newNode);
			trailer.setPrev(newNode);
			cursor = newNode;
			size++;
		}

	}

	/**
	 * Insert an item in front of the current cursor position in the list. The
	 * cursor will refer to the newly inserted item. If the list is empty
	 * {@code item} becomes the first and last item in the list.
	 * 
	 * Run-time Efficiency: O(c), where c is constant as the max number of 
	 * operations executed is 8.
	 * 
	 * @param item
	 *            The item to be inserted into the list.
	 */
	public void insert(T item) {
		if (isEmpty()) {
			Node<T> newNode = new Node<T>(item, header, trailer);
			header.setNext(newNode);
			trailer.setPrev(newNode);
			cursor = newNode;
			size++;
		} else {
			Node<T> newNode = new Node<T>(item, cursor.getPrev(), cursor);
			cursor.getPrev().setNext(newNode);
			cursor.setPrev(newNode);
			cursor = newNode;
			size++;
		}

	}

	/**
	 * Removes the item, at the current cursor position, from the list. Ensures
	 * that the previous item is correctly connected to the next item. After the
	 * removal the cursor will refer to the next item in the list. If the
	 * removed item was the last element in the list, then the cursor will refer
	 * to the previous element, which is now the last item in the list.
	 * 
	 * Run-time Efficiency: O(c), where c is constant as the max number of 
	 * operations executed is 15.
	 * 
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If an attempt is made to remove an element from an empty
	 *             list.
	 */
	public void remove() throws IndexOutOfBoundsException {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("Failed to remove element! The Linked List is empty!");
		} else if (cursor.getNext() != trailer) {
			cursor.getPrev().setNext(cursor.getNext());
			cursor.getNext().setPrev(cursor.getPrev());
			cursor = cursor.getNext();
			size--;
		} else {
			cursor.getPrev().setNext(cursor.getNext());
			cursor.getNext().setPrev(cursor.getPrev());
			cursor = cursor.getPrev();
			size--;
		}

	}

	/**
	 * Move the internal cursor to the first element in the list.
	 * 
	 * Run-time Efficiency: O(c), where c is constant as the max number of 
	 * operations executed is 5.
	 * 
	 * 
	 * @return The item at the first position in the list; null if the list is
	 *         empty.
	 */
	public T getFirst() {
		if (isEmpty()) {
			return null;
		}
		cursor = header.getNext();

		// first element is stored at the next node after header
		return cursor.getItem();
	}

	/**
	 * Move the internal cursor to the next element in sequential order in the
	 * list. If the cursor is at the end of the list it remains at that
	 * position, and returns the item at that position.
	 * 
	 * Run-time Efficiency: O(c), where c is constant as the max number of 
	 * operations executed is 11.
	 * 
	 * 
	 * @return The item at the new cursor position; null if the list is empty.
	 */
	public T getNext() {
		if (isEmpty()) {
			return null;
		} else if (cursor.getNext() != trailer) {
			cursor = cursor.getNext();
			return cursor.getItem();
		} else {
			return cursor.getItem();
		}

	}

	/**
	 * Move the internal cursor to the last element in the list.
	 * 
	 * Run-time Efficiency: O(c), where c is constant as the max number of 
	 * operations executed is 7.
	 * 
	 * 
	 * @return The item at the last position in the list; null if the list is
	 *         empty.
	 */
	public T getLast() {
		if (isEmpty()) {
			return null;
		}
		cursor = trailer.getPrev();
		return cursor.getItem();
	}

	/**
	 * Move the internal cursor to the previous element in sequential order in
	 * the list. If the cursor is at the beginning of the list it remains at
	 * that position, and returns the item at that position.
	 * 
	 * Run-time Efficiency: O(c), where c is constant as the max number of 
	 * operations executed is 11.
	 * 
	 * 
	 * @return The item at the new cursor position; null if the list is empty.
	 */
	public T getPrevious() {
		if (isEmpty()) {
			return null;
		} else if (cursor.getPrev() != header) {
			cursor = cursor.getPrev();
			return cursor.getItem();
		} else {
			return cursor.getItem();
		}
	}

	/**
	 * Finds an item in the list, moving the cursor to the item's position in
	 * the list. Starts searching from the beginning of the list, and stops when
	 * it finds the first instance of the item in the list. If the item is not
	 * found the cursor remains at the end of the list.
	 * 
	 * Run-time Efficiency: O(n), in this method, it will iterate over the list to
	 * find the item until finds the first instance of it or reaches the end.
	 * 
	 * 
	 * @param item
	 *            The item to be found.
	 * @return true if the item has been found in the list; false otherwise.
	 */
	public boolean find(T item) {
		boolean result = false;

		if (isEmpty()) {
			return result;
		} else if (header.getNext().getItem().equals(item)) {
			result = true;
			cursor = header.getNext();
		} else {
			cursor = header.getNext();
			while (cursor.getNext() != trailer) {
				cursor = cursor.getNext();
				if (cursor.getItem().equals(item)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Indicates if the list is empty or not.
	 * 
	 * Run-time Efficiency: O(c), where c is constant as the max number of 
	 * operations executed is 2
	 * 
	 * 
	 * @return true if the list is empty (has no elements, i.e. size is zero);
	 *         false otherwise.
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Indicates if the cursor is at the last element in the list. Will return
	 * false if the list is empty.
	 * 
	 * Run-time Efficiency: O(c), where c is constant as the max number of 
	 * operations executed is 6
	 * 
	 * 
	 * @return true if the cursor position is the last element in the list;
	 *         false otherwise.
	 */
	public boolean isLast() {
		if (isEmpty()) {
			return false;
		} else {
			return (cursor.getNext() == trailer);
		}
	}

}
