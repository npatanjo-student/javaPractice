import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
/**
 * AUTHOR: Nate Patanjo
 * FILE: MaxPQ.java
 * ASSIGNMENT: Wiki Racer PA 10
 * COURSE: CSC 210
 * PURPOSE: The purpose of this assignment is to go from one
 * link to another only traveling through the wiki links
 */
public class MaxPQ {
	
	/** The Constant DEFAULT_SIZE. */
	private static final int DEFAULT_SIZE = 10;
	
	/** The array. */
	private Ladder[] array;
	
	/** The size. */
	private int size;
	
	/** The capacity. */
	private int capacity;
	
	/**
	 * Instantiates a new max PQ.
	 */
	public MaxPQ() {
		size = 0;
        array = new Ladder[DEFAULT_SIZE];
        capacity = DEFAULT_SIZE;
	}
	
    /**
     * Parent index.
     *
     * @param index the index
     * @return the int
     */
    private int parentIndex(int index) {
        return index / 2;
    }
    
    /**
     * Left child.
     *
     * @param index the index
     * @return the int
     */
    private int leftChild(int index) {
        return index * 2;
    }
    
    /**
     * Right child.
     *
     * @param index the index
     * @return the int
     */
    private int rightChild(int index) {
        return index * 2 + 1;
    }
	
    /**
     * Enqueue.
     *
     * @param link the link
     * @param priority the priority
     */
    public void enqueue(List<String> link, int priority) {
    	Ladder tmp = new Ladder(link, priority);
    	enqueue(tmp);
    }
    
	/**
	 * Enqueue.
	 *
	 * @param value the value
	 */
	public void enqueue(Ladder value) {
        if (size >= capacity - 1) {
            growQueue();
        }
        array[this.size + 1] = value;
        this.size++;
        if (size > 1) {
            maxHeapBubbleUp(size);
        }
    }
	
    /**
     * Dequeue.
     *
     * @return the list
     * @throws NullPointerException the null pointer exception
     */
    public List<String> dequeue() throws NullPointerException {
        if (this.isEmpty()) {
            throw new NullPointerException("Empty queue cannot be dequeued");
        }
        List<String> retPat = array[1].links;
        moveBack();
        this.size -= 1;
        maxHeapBubbleDown(1);
        return retPat;
    }
	
	/**
	 * Grow queue.
	 */
	private void growQueue() {
        Ladder[] newArr = new Ladder[this.capacity * 2];
        for (int i = 1; i <= this.size; i++) {
            newArr[i] = array[i];
        }
        this.array = newArr;
        capacity = capacity * 2;
    }
	
    /**
     * Max heap bubble up.
     *
     * @param index the index
     */
    private void maxHeapBubbleUp(int index) {
        while (index > 1) {
            if (array[index].priority > array[parentIndex(index)].priority) {
                swap(index, parentIndex(index));
                index = parentIndex(index);
            } else {
                index = 1;
            }
        }
    }
    
    /**
     * Max heap bubble down.
     *
     * @param index the index
     */
    private void maxHeapBubbleDown(int index) {
        if (leftChild(index) <= size) {
            if (array[leftChild(index)].priority < array[rightChild(
                    index)].priority) {
                if (array[index].priority < array[leftChild(index)].priority) {
                    swap(leftChild(index), index);
                    maxHeapBubbleDown(leftChild(index));
                }
            } else {
                if (array[index].priority < array[rightChild(
                        index)].priority) {
                    swap(rightChild(index), index);
                    maxHeapBubbleDown(rightChild(index));
                }
            }
        }
    }
	
    /**
     * Swap.
     *
     * @param index the index
     * @param parent the parent
     */
    private void swap(int index, int parent) {
        Ladder tmp = array[index];
        this.array[index] = array[parent];
        this.array[parent] = tmp;
    }
    
    /**
     * Move back.
     */
    private void moveBack() {
        array[1] = array[size];
    }
    
    /**
     * Checks if is empty.
     *
     * @return true, if is empty
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Size.
     *
     * @return the int
     */
    public int size() {
        return size;
    }
    
    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
    	String retStr = "FRONT\n";
    	for(int i = 1; i <= size; i++) {
    		retStr += array[i].links;
    		retStr += " " + array[i].priority + "\n";
    	}
    	return retStr += "BACK";
    }
	
	/**
	 * The Class Ladder.
	 */
	public class Ladder {
		
		/** The links. */
		private List<String> links;
		
		/** The priority. */
		private int priority;
		
		/**
		 * Instantiates a new ladder.
		 *
		 * @param link the link
		 * @param priority the priority
		 */
		public Ladder(List<String> link, int priority) {
			this.links = new ArrayList<String>(link);
			this.priority = priority;
		}
		
		/**
		 * To string.
		 *
		 * @return the string
		 */
		public String toString() {
			return links.toString();
		}
	}

}
