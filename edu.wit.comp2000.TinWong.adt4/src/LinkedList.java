import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import javax.swing.plaf.synth.SynthSpinnerUI;

/**
 * 
 * @author wongt1
 *
 *         This LinkedList is a singly-linked(only head reference) due to the
 *         small amount of space needed 
 *         However, for a more time efficient ADT -
 *         a doubly-linked (head and tail reference) would be implemented.
 *			
 * @param <T>
 */
public class LinkedList<T> implements ListInterface<T>, Iterable<T>, Comparable<T> {

	private Node firstNode;
	private int numberOfEntries;

	/**
	 * Node class.
	 */
	private class Node {
		private T data;
		private Node next;

		/**
		 * constructor for node
		 * @param dataPortion
		 */
		private Node(T dataPortion) {
			this(dataPortion, null);
		}

		/**
		 * constructor for node
		 * @param dataPortion
		 * @param nextNode
		 */
		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		/**
		 * accessor method for data
		 * @return
		 */
		private T getData() {
			return this.data;
		}

		/**
		 * mutator method for data
		 * @param newData
		 */
		private void setData(T newData) {
			this.data = newData;
		}

		/**
		 * accessor method for next node
		 * @return
		 */
		private Node getNextNode() {
			return this.next;
		}

		/**
		 * mutator method for next node
		 * @param nextNode
		 */
		private void setNextNode(Node nextNode) {
			this.next = nextNode;
		}
	}

	/**
	 * Constructor.
	 */

	public LinkedList() {
		initializeDataFields();
	}

	/**
	 * Iterator constructor
	 */
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new IteratorForLinkedList();
	}

	/**
	 * creates an instanceOf iterator
	 * 
	 * @return iterator
	 */
	public Iterator<T> getIterator() {
		return iterator();
	}

	/**
	 * Iterator class for Iterator
	 *
	 */
	private class IteratorForLinkedList implements Iterator<T> {
		private Node nextNode;

		private IteratorForLinkedList() {
			nextNode = firstNode;
		}

		@Override
		/**
		 * returns whether there's a next node or not
		 */
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return nextNode != null;
		}

		@Override
		/**
		 * returns the next node
		 */
		public T next() {
			// TODO Auto-generated method stub
			if (hasNext()) {
				Node returnNode = nextNode;
				nextNode = nextNode.getNextNode();

				return returnNode.getData();
			} else {
				throw new NoSuchElementException("Illegal call to next(); " + "iterator is after end of list.");
			}
		}
	}

	@Override
	/**
	 * add a newEntry to the end of the list
	 */
	public boolean add(T newEntry) {
		// TODO Auto-generated method stub
		Node newNode = new Node(newEntry);

		if (this.isEmpty()) {
			firstNode = newNode;
			this.numberOfEntries++;
			return true;
		} else {
			Node lastNode = getNodeAt(numberOfEntries - 1);
			lastNode.setNextNode(newNode);
			this.numberOfEntries++;

			return true;
		}
	}

	@Override
	/**
	 * add a newEntry to a newPosition if legal
	 * 
	 * @return boolean
	 */
	public boolean add(int newPosition, T newEntry) {
		// TODO Auto-generated method stub
		if ((newPosition >= 0) && (newPosition <= numberOfEntries)) {
			Node newNode = new Node(newEntry);

			if (newPosition == 0) {
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			} else {
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			}
			this.numberOfEntries++;

			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * removes the entry if givenPosition is legal beginning of the node is easy
	 * to add if in the middle, must shift the nodeBefore to the next node of
	 * the removed node
	 * 
	 * @return T or exception
	 */
	public T remove(int givenPosition) {
		// TODO Auto-generated method stub
		T result = null;

		if ((givenPosition >= 0) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			if (givenPosition == 0 && firstNode.getNextNode() != null) {
				result = firstNode.getData();
				firstNode = firstNode.getNextNode();
			} else {
				Node nodeBefore = getNodeAt(givenPosition - 1);
				Node nodeToRemove = nodeBefore.getNextNode();
				result = nodeToRemove.getData();
				Node nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);
			}
			this.numberOfEntries--;
			return result;
		} else {
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
		}
	}

	@Override
	/**
	 * clears the list
	 */
	public void clear() {
		// TODO Auto-generated method stub
		initializeDataFields();
	}

	@Override
	/**
	 * replaces the position with newEntry if givenPosition is legal
	 * 
	 * @return boolean
	 */
	public boolean replace(int givenPosition, T newEntry) {
		// TODO Auto-generated method stub
		if ((givenPosition >= 0) && (givenPosition < numberOfEntries)) {
			assert !isEmpty();
			Node desiredNode = getNodeAt(givenPosition);
			// T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * return the entry at givenPosition
	 */
	public T getEntry(int givenPosition) {
		// TODO Auto-generated method stub
		if ((givenPosition >= 0) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			return getNodeAt(givenPosition).getData();
		} else {
			throw new IndexOutOfBoundsException("Illegal position given.");
		}
	}

	@Override
	/**
	 * method that loops and checks starting from first node, If the node equals
	 * to anEntry, then return true else, return false.
	 * 
	 * @param T
	 *            entry
	 * @return boolean
	 */
	public boolean contains(T anEntry) {
		// TODO Auto-generated method stub
		for (T entry : this) {
			if (entry.equals(anEntry)) {
				return true;
			}
		}
		return false;
	}

	@Override
	/**
	 * returns the length of the list(numberOfEntries)
	 */
	public int getLength() {
		// TODO Auto-generated method stub
		return this.numberOfEntries;
	}

	@Override
	/**
	 * returns if list is empty using numberOfEntries
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.numberOfEntries == 0;
	}

	@Override
	/**
	 * LinkedList is never full until out of memory so...always false?
	 */
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * toArray for this
	 * 
	 * @return
	 */
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];

		int index = 0;
		Node currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		}

		return result;
	}

	/**
	 * sort method, uses an Insertion sort method O(n^2) which isn't the most efficient buuuuut
	 * easiest to code!
	 */
	public void sort() {
		if (this.getLength() > 1) {
			assert firstNode != null;

			Node unsortedPart = firstNode.getNextNode();
			assert unsortedPart != null;
			firstNode.setNextNode(null);

			while (unsortedPart != null) {
				Node nodeToInsert = unsortedPart;
				unsortedPart = unsortedPart.getNextNode();
				insertInOrder(nodeToInsert);
			}
		}
	}

	/**
	 * private method used in sort (Selection sort), inserts the smallest node between nodes or 
	 * places it at the beginning 
	 * @param nodeToInsert
	 */
	@SuppressWarnings("unchecked")
	private void insertInOrder(Node nodeToInsert){
		T item = nodeToInsert.getData();
		Node currentNode = firstNode;
		Node previousNode = null;

		//compiler error unless I cast a type onto item
		while ((currentNode != null) && (((Comparable<T>) item).compareTo(currentNode.getData()) > 0)) {
			previousNode = currentNode;
			currentNode = currentNode.getNextNode();
		}

		if (previousNode != null) {
			previousNode.setNextNode(nodeToInsert);
			nodeToInsert.setNextNode(currentNode);
		} else {
			nodeToInsert.setNextNode(firstNode);
			firstNode = nodeToInsert;
		}
	}

	/**
	 * shuffle method, heavily inspired by Fisher–Yates shuffle
	 * 
	 * @param rnd
	 */
	public void shuffle(Random rnd) {
		int size = this.getLength();

		T arr[] = this.toArray();

		for (int i = size; i > 1; i--) {
			swap(arr, i - 1, rnd.nextInt(i));
		}

		for (int i = 0; i < arr.length; i++) {
			this.replace(i, arr[i]);
		}
	}

	/**
	 * used when clearing and creating an instanceOf
	 */
	private void initializeDataFields() {
		this.firstNode = null;
		this.numberOfEntries = 0;
	}

	/**
	 * private method used to add, remove, replace, getEntry 
	 * it finds the currentNode at the givenPosition
	 * (private because it has the underlying data structure) 
	 * @param givenPosition
	 * @return
	 */
	private Node getNodeAt(int givenPosition) {
		Node currentNode = firstNode;

		assert (firstNode != null) && (0 <= givenPosition) && (givenPosition < numberOfEntries);

		for (int counter = 0; counter < givenPosition; counter++) {
			currentNode = currentNode.getNextNode();
		}

		assert currentNode != null;

		return currentNode;
	}

	/**
	 * Swap method used in shuffle and sort
	 * 
	 * @param x
	 * @param a
	 * @param b
	 */
	private static void swap(Object[] x, int a, int b) {
		Object t = x[a];
		x[a] = x[b];
		x[b] = t;
	}

	@Override
	/**
	 * Compare to method from Comparable
	 */
	public int compareTo(T t) {
		// TODO Auto-generated method stub
		return this.compareTo(t);
	}

	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<>();

		System.out.println("Created LinkedList.");
		System.out.println("Test if list is empty: " + list.isEmpty());

		list.add("Tin");
		list.add(1, "Yam");
		list.add("Wong");
		System.out.println("Added: Tin (1, Yam) Wong");

		System.out.println("Test if list is empty: " + list.isEmpty());

		Iterator<String> iterator = list.getIterator();

		System.out.println("Get length of the list (should be 3): " + list.getLength());

		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		System.out.println("Clearing list.");
		list.clear();

		list.add("Tin");
		list.add("Yam");
		list.add("Wong");
		list.add(null);
		list.remove(3);
		list.replace(0, "Lelouch");

		System.out.println("Added: Tin Yam Wong null \nRemoved: null \nReplaced(0, Lelouch)");
		for (String s : list) {
			System.out.println(s);
		}

		list.shuffle(new Random());
		System.out.println("Shuffled list:");

		for (String s : list) {
			System.out.println(s);
		}

		list.sort();
		System.out.println("Sorted list:");
		for (String s : list) {
			System.out.println(s);
		}
		
		System.out.println("Clearing list.");
		list.clear();

		System.out.println("Test if list is empty: " + list.isEmpty());
	}
}
