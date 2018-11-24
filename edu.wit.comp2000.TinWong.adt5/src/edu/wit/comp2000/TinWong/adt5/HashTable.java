package edu.wit.comp2000.TinWong.adt5;

/**
 * to do list: resizing table(done) clear method configuring hashCode
 * method(done) fix toString() a bit
 * 
 * bug: chaining nodes(fixed)
 * 
 * 
 * @author wongt1
 *
 * @param <K>
 * @param <V>
 */

public class HashTable {
	/**
	 * buckets are represented using an array of Node
	 */
	private Node[] bucketList;

	/**
	 * Current capacity of array list
	 */
	private int numBuckets;

	/**
	 * Current numberOfEntries of array list
	 */
	private int numberOfEntries;

	/**
	 * private class of Node, (typical) private Node class for HashTable entry
	 *
	 * @param <T>
	 * @param <S>
	 */
	private class Node {
		private String key;
		private Integer value;
		private Node next;

		/**
		 * constructor for node
		 * 
		 * @param key
		 */
		private Node(String key, Integer value) {
			this(key, value, null);
		}

		/**
		 * constructor for node
		 * 
		 * @param key
		 * @param nextNode
		 */
		private Node(String key, Integer value, Node nextNode) {
			this.key = key;
			this.value = value;
			this.next = nextNode;
		}

		/**
		 * getter method for key
		 * 
		 * @return
		 */
		private Integer getValue() {
			return this.value;
		}

		/**
		 * getter method for value
		 */
		private String getKey() {
			return this.key;
		}

		/**
		 * setter method for data
		 * 
		 * @param newData
		 */
		@SuppressWarnings("unused")
		private void setKey(String key) {
			this.key = key;
		}

		private void setValue(Integer value) {
			this.value = value;
		}

		/**
		 * getter method for next node
		 * 
		 * @return
		 */
		private Node getNextNode() {
			return this.next;
		}

		/**
		 * setter method for next node
		 * 
		 * @param nextNode
		 */
		private void setNextNode(Node nextNode) {
			this.next = nextNode;
		}

		/**
		 * toString method for Node
		 */
		@Override
		public String toString() {
			String output = "";
			while (this.getNextNode() != null) {
				output += "Key: " + this.key + " Value: " + this.value + "\n";
			}
			return output;
		}
	}

	/**
	 * default constructor for HashTable
	 */
	public HashTable() {
		this(3);
	}

	/**
	 * Constructor with a parameter for numBuckets
	 * 
	 * @param numBuckets
	 */
	public HashTable(int numBuckets) {
		this.numBuckets = numBuckets;
		this.numberOfEntries = 0;
		this.bucketList = new Node[numBuckets];
	}

	/**
	 * find the key for buckets
	 * 
	 * @param key
	 * @return
	 */
	private int getHashIndex(String key) {
		int hashCode = getHashCode(key);
		hashCode = hashCode % this.numBuckets;
		return hashCode;
	}

	/**
	 * my own hashCode method, sums each char value then compressed to table
	 * size
	 * 
	 * @param str
	 * @return
	 */
	private int getHashCode(String str) {
		int sum = 0;

		for (int i = 0; i < str.length(); i++) {			
			sum += str.charAt(i) * 3;
		}

		return sum;
	}

	/**
	 * retrieve the Node, if found return value if not return null
	 * 
	 * @param key
	 * @return
	 */
	public Integer getValue(String key) {
		key = key.toLowerCase();
		// Find head of chain for given key
		int bucketIndex = getHashIndex(key);

		Node head = bucketList[bucketIndex];
		// Search key in chain
		while (head != null) {
			if (head.key.equals(key)) {
				return head.getValue();
			}
			head = head.getNextNode();
		}

		// If key not found
		return null;
	}

	/**
	 * add a Node to the HashTable
	 * 
	 * @param key
	 * @param value
	 */
	public Integer add(String key) {

		// resize table if it reaches load factor
		if (numberOfEntries == numBuckets) {
			this.resizeTable();
		}

		key = key.toLowerCase();
		int bucketIndex = getHashIndex(key);
		this.numberOfEntries++;

		// Find head of chain for given key
		Node head = bucketList[bucketIndex];

		// head == null when its the start of a chain
		if (head == null) {
			head = new Node(key, 1);
			bucketList[bucketIndex] = head;
		}
		// the chain isn't empty
		else {
			// searches for an equals
			while (head != null) {
				// increase value if found
				if (head.key.equals(key)) {
					head.setValue(head.getValue() + 1);
					return head.getValue();
				}

				// if next node is null, that means the end of the chain
				if (head.getNextNode() == null) {
					Node temp = new Node(key, 1);
					head.setNextNode(temp);
					return temp.getValue();
				}
				// else, keep traversing
				else {
					head = head.getNextNode();
				}
			}
		}

		return head.getValue();
	}

	/**
	 * method to resize the table each time load factor = 1.0
	 */
	private void resizeTable() {
		
		Node[] temp = this.bucketList;
		boolean primeLooped = false;
		
		//print out table metrics
		this.tableMetrics();

		// reset numberOfEntries back to 0
		this.numberOfEntries = 0;
		this.numBuckets = this.numBuckets * 2;
		
		// funky while loop, increments until numBuckets is a prime number
		// primeLooped is so that the loop is guranteed to run at least once
		while (!isPrime(this.numBuckets) || !primeLooped) {
			primeLooped = true;
			this.numBuckets++;
		}

		// a new array with new size for bucketList
		this.bucketList = new Node[this.numBuckets];

		// re-adding Node(s) from temp into bucketList
		for (Node n : temp) {
			// moving through the chain
			while (n != null) {
				// n.value is to add it n number of times
				for (int i = 0; i < n.value; i++) {
					this.add(n.getKey());
				}
				// to move to the next node
				n = n.getNextNode();
			}
		}
		System.out.println("After increasing table size: " + this.numBuckets);
	}

	@Override
	/**
	 * Standard toString method, prints out the table
	 */
	public String toString() {
		String output = "";
		for (int i = 0; i < this.bucketList.length; i++) {
			Node currentNode = this.bucketList[i];
			while (currentNode != null) {
				output += "Key: " + currentNode.getKey() + " Value: " + currentNode.getValue() + "\n";
				currentNode = currentNode.getNextNode();
			}
		}

		return output;
	}

	/**
	 * pseudocode found in java textbook, test if n is prime used to resize
	 * table
	 * 
	 * @param n
	 * @return
	 */
	private boolean isPrime(int n) {
		if (n <= 1) {
			return false;
		} else if (n <= 3) {
			return true;
		} else if (n % 2 == 0 || n % 3 == 0) {
			return false;
		}
		int i = 2;
		while (i < n / 2) {
			if (n % i == 0) {
				return false;
			}
			i++;
		}
		return true;
	}

	/**
	 * print out the table metrics when load factor is == 1.0
	 */
	private void tableMetrics() {
		int bucketsUsed = 0;
		double percentageBucketsUsed = 0;
		Node[] temp = this.bucketList;
		
		System.out.println("Current table size: " + this.numBuckets);
		System.out.println("Number of entries: " + this.numberOfEntries);
		System.out.println("Load factor: " + this.numberOfEntries * 1.0 / this.numBuckets);
		
		// calculating number of buckets in used
		for (Node n : temp) {
			if (n != null) {
				bucketsUsed++;
			}
		}

		percentageBucketsUsed = bucketsUsed * 1.0 / this.numBuckets;
		System.out.println("Number of buckets used: " + bucketsUsed);
		System.out.printf("Percentage of buckets used: %.2f%%%n", percentageBucketsUsed * 100);
	}

	/**
	 * main method User does not provide an initial table size. . .
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "The";
		String s1 = "What";
		String s2 = "He";
		String s3 = "eht";
		HashTable ht = new HashTable();
		ht.add(s);
		ht.add(s);
		ht.add(s1);
		ht.add(s2);
		ht.add(s3);

		System.out.println("Value of The: " + ht.getValue(s));
		System.out.println("Value of What: " + ht.getValue(s1));
		System.out.println("Value of He: " + ht.getValue(s2));
		System.out.println("Value of eht: " + ht.getValue(s3));

		System.out.println(ht);
	}
}
