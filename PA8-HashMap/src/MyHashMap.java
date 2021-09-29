import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/**
 * AUTHOR: Nate Patanjo
 * FILE: MyHashMap.java
 * ASSIGNMENT: Programming Assignment 8 - HashMap
 * COURSE: CSC 210
 * PURPOSE: The purpose of this program is to generate a Generic HashMap.
 * The HashMap has functionality and a printed representation. This 
 * program also keeps track of conflicts in the ordering of the data. 
 * 
 * USAGE:
 * 
 * -------------INPUT-------------
 * 		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>();
 *		map.put(1, 2);
 *		map.put(11, 22);
 *		map.put(1, 100);
 *		map.put(2, 4);
 *		map.put(3, 6);
 *		map.put(4, 8);
 *		map.put(6, 12);
 *		map.put(7, 14);
 *		map.printTable();
 *		System.out.println(map.keySet().toString());
 *		System.out.println("CONTAINS KEY 20: " + map.containsKey(20));
 *		System.out.println("CONTAINS VAL 78: " + map.containsValue(78));
 *		map.remove(4);
 *		map.remove(20);
 *		map.remove(12);
 *		map.remove(20);
 *		System.out.println("REMOVE: " + map.remove(5));
 *		map.put(10, 30);
 *		System.out.println("GET: " + map.get(12));
 *		map.printTable();
 * -------------OUTPUT-------------
 * 		Index 0: (0 conflicts), []
 *		Index 1: (0 conflicts), [1, ]
 *		Index 2: (0 conflicts), [2, ]
 *		Index 3: (1 conflicts), [3, 11, ]
 *		Index 4: (0 conflicts), [4, ]
 *		Index 5: (0 conflicts), []
 *		Index 6: (0 conflicts), [6, ]
 *		Index 7: (0 conflicts), [7, ]
 *	 	Total # of conflicts: 1
 *		[1, 2, 3, 4, 6, 7, 11]
 *  	CONTAINS KEY 20: false
 *		CONTAINS VAL 78: false
 *		REMOVE: null
 *		GET: null
 *		Index 0: (0 conflicts), []
 *		Index 1: (0 conflicts), [1, ]
 *		Index 2: (1 conflicts), [10, 2, ]
 *		Index 3: (1 conflicts), [3, 11, ]
 *		Index 4: (0 conflicts), []
 *		Index 5: (0 conflicts), []
 *		Index 6: (0 conflicts), [6, ]
 *		Index 7: (0 conflicts), [7, ]
 *		Total # of conflicts: 2
 * 
 * @param <K> the key type
 * @param <V> the value type
 */
public class MyHashMap<K, V> {
	
	/** The conflicts. */
	private int conflicts = 0;
	
	/** The num buckets. */
	private int numBuckets =  8;
	
	/** The size. */
	private int size = 0;
	
	/** The ret key set. */
	private Set<K> retKeySet;
	
	/** The map. */
	private ArrayList<Bucket> map;	
	
	/**
	 * Instantiates a new my hash map.
	 */
	public MyHashMap() {
		map = new ArrayList<Bucket>(numBuckets);
		for (int i = 0; i < numBuckets; i++) {
			map.add(i, new Bucket(null, null));
		}
		this.retKeySet = new HashSet<K>();
	}

	/**
	 * Put. Adds a new key and value to a HashMap. If the key is 
	 * already present the value of the already existing key is 
	 * changed to the new one. 
	 *
	 * @param key the key to be added or changed 
	 * @param value the value associated with the key
	 * @return V the previous value associated with the key
	 * or null if the key was not already present
	 */
	public V put(K key, V value) {
		Bucket curr = map.get(this.hash(key));
		V retVal = curr.addToBucket(key, value);
		this.size += 1;
		this.retKeySet.add(key);
		return retVal;
	}
	
	/**
	 * Gets the conflicts. A getter for the number of conflicts
	 * (used in the toString() method)
	 *
	 * @return the conflicts
	 */
	private int getConflicts() {
		for (Bucket b : map) {
			this.conflicts += b.getCollisions();
		}
		return this.conflicts;
	}
	
	/**
	 * Clear. clears the map
	 */
	public void clear() {
		map = new ArrayList<Bucket>(numBuckets);
		for (int i = 0; i < numBuckets; i++) {
			map.add(i, new Bucket(null, null));
		}
		this.size = 0;
		this.retKeySet = new HashSet<K>();
	}
	
	/**
	 * Contains key. Uses the set of keys to determine
	 * if the specified key is present in the map
	 *
	 * @param key the key
	 * @return true, if successful
	 */
	public boolean containsKey(K key) {
		return this.retKeySet.contains(key);
	}
	
	/**
	 * Contains value. This function calls a value search
	 * on each bucket in the HashMap. The purpose is to determine
	 * if the value is present in the map
	 *
	 * @param value the value to be found in the map
	 * @return true, if successful
	 */
	public boolean containsValue(V value) {
		for (Bucket b : map) {
			if (b.bucketContainsValue(value) == true) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if map is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Size. gives the size of the map (how many nodes there are)
	 *
	 * @return the int number of nodes
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Removes the. This calls the remove method in the bucket class
	 * it also changes the size and set
	 *
	 * @param key the key to be removed
	 * @return the v associated with the removed key
	 */
	public V remove(K key) {
		this.size -= 1;
		this.retKeySet.remove(key);
		return map.get(hash(key)).bucketRemove(key);
	}
	
	/**
	 * Prints the table.
	 */
	public void printTable() {
		System.out.println(this.toString());
	}
	
	/**
	 * Key set. Gives a set representation of the HashMap keys
	 *
	 * @return the sets the
	 */
	public Set<K> keySet() {
		return retKeySet;
	}
	
	/**
	 * Gets the value associated with the specified key. 
	 *
	 * @param key the key
	 * @return the v
	 */
	public V get(K key){
		return map.get(this.hash(key)).getVal(key);
	}
	
	/**
	 * Hash. This is the hash function used to place the 
	 * nodes
	 *
	 * @param key the key to get a code from
	 * @return the int is the code (0-7)
	 */

	private int hash(K key) {
		int hashCode = key.hashCode();
		int index = hashCode % numBuckets;
		return Math.abs(index);
	}
	
	/**
	 * To string. creates a string representation of the Map
	 *
	 * @return the string
	 */
	public String toString() {
		String retVal = "";
		for (int i = 0; i < this.numBuckets; i++) {
			retVal += "Index " + i + ": " + map.get(i).toString() + "\n";
		}
		retVal += "Total # of conflicts: " + this.getConflicts();
		this.conflicts = 0;
		return retVal;
	}
	
	/**
	 * The Class Bucket.
	 * 
	 * PURPOSE:
	 * the purpose of this class is to create a Bucket object which
	 * contains all HashNodes in the HashMap. 
	 */
	private class Bucket{
		
		/** The collisions. */
		private int collisions;

		/** The head. */
		private HashNode<K, V> head;
		
		/**
		 * Instantiates a new bucket.
		 *
		 * @param key the key
		 * @param value the value
		 */
		private Bucket(K key, V value) {
			this.collisions = 0;
			this.head = new HashNode<K, V>(key, value);
		}
		
		/**
		 * Adds a collision.
		 */
		private void addCollision() {
			this.collisions++;
		}
		
		/**
		 * Removes a collision.
		 */
		private void removeCollision() {
			this.collisions -= 1;
		}
		
		/**
		 * Adds to the Bucket. This method adds a node to the bucket
		 * or changes the value of the Node in the bucket if the
		 * key is already present
		 *
		 * @param key the key to be added or changed
		 * @param value the value to added 
		 * @return the v the previous value or null
		 */
		private V addToBucket(K key, V value) {
			HashNode<K, V> curr = this.head;
			V retVal = null;
			while (curr.getNext() != null) {
				if (curr.getKey() == key) {
					retVal = curr.getValue();
					curr.setValue(value);
					return retVal;
				}
				curr = curr.getNext();
			}
			this.addCollision();
			HashNode<K, V> node = new HashNode<K, V>(key, value);
			node.setNext(head);
			head = node;
			return null;
			
		}
		
		/**
		 * Gets the collisions. this gets the instance of collisions
		 *
		 * @return the number of collisions
		 */
		private int getCollisions() {
			if (this.collisions >= 1) {
				return this.collisions-1;
			} else {
				return this.collisions;
			}
		}
		
		/**
		 * Bucket contains value. This checks the specified bucket for 
		 * value argument to see if the map contains it
		 *
		 * @param value the value to be checked
		 * @return true, if successful
		 */
		private boolean bucketContainsValue(V value) {
			HashNode<K, V> curr = head;
			while (curr.getNext() != null) {
				if (curr.getValue() == value) {
					return true;
				}
				curr = curr.getNext();
			}
			return false;
		}
		
		/**
		 * Gets the val. This gets the value associated with the
		 * given key
		 *
		 * @param key the key associated with the desired value
		 * @return the val
		 */
		private V getVal(K key) {
			HashNode<K, V> curr = head;
			while (curr.getNext() != null) {
				if (curr.getKey().equals(key)) {
					return curr.getValue();
				}
				curr = curr.getNext();
			}
			return null;
		}
		
		/**
		 * Bucket remove. This method removes node containing the 
		 * given key. If the argument key does not exist, it returns
		 * null. Otherwise it returns the value associated with the key 
		 *
		 * @param key the key to be removed
		 * @return the v associated with the removed key
		 */
		private V bucketRemove(K key) {
			HashNode<K, V> curr = head;
			V retVal = null;
			HashNode<K, V> prev = null;
			if (head.getKey() == null) {
				return null;
			}
			if (head.getKey().equals(key)) {
				retVal = head.getValue();
				head = head.getNext();
				return retVal;
			} else if (head.getNext().getKey() == null) {
				return null;
			} else {
				while (curr.getNext() != null) {
					if (curr.getNext().getKey().equals(key)) {
						prev = curr;
						curr = curr.getNext();
						retVal = curr.getValue();
						prev.setNext(curr.getNext());
						this.removeCollision();
						return retVal;
					}
					curr = curr.getNext();
				}
				return retVal;
			}
		}
		
		/**
		 * To string. Creates string representation of the bucket
		 *
		 * @return the string
		 */
		public String toString() {
			String retVal = "(" + this.getCollisions() + " conflicts), [";
			HashNode<K, V> curr = head;
			while (curr != null) {
				if (curr.getKey() != null) {
					retVal += curr.getKey() + ", ";
				}
				curr = curr.getNext();
			}
			return retVal +"]";
		}
	}
}






