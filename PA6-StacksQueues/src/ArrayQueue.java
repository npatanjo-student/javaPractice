/**
 * AUTHOR: Nate Patanjo
 * COURSE: CSC 210
 * FILE: ArrayQueue.java Mar 16, 2021
 * ASSIGNMENT: PA6-StacksQueues
 * PURPOSE: The purpose of this program is to create a queue
 * using a LinkedList.
 * 
 * USAGE:
 * Input (after initialization):
 * queue.enqueue(1);
 * queue.enqueue(2);
 * System.out.println(queue.toString());
 * queue.dequeue()
 * System.out.println(queue.toString());
 * 
 * Output:
 * {1,2}
 * {2}
 */
public class ArrayQueue implements QueueInterface {

    /** The Constant DEFAULT. */
    private static final int DEFAULT = 10;

    /** The array. */
    private Integer[] array;

    /** The size. */
    private int size;

    /**
     * Instantiates a new array queue.
     */
    public ArrayQueue() {
        size = 0;
        array = new Integer[DEFAULT];
    }

    /**
     * Instantiates a new array queue copy.
     *
     * @param copy
     *            the queue to get copied
     */
    public ArrayQueue(ArrayQueue copy) {
        this.size = copy.size;
        this.array = new Integer[this.size];
        for (int i = 0; i < this.size; i++) {
            this.array[i] = copy.array[i];
        }
    }

    /**
     * Shrink queue. this method creates a new smaller array
     */
    private void shrinkQueue() {
        Integer[] newArr = new Integer[this.size - 1];
        for (int i = 1; i < this.size; i++) {
            newArr[i - 1] = array[i];
        }
        this.array = newArr;
    }

    /**
     * Grow queue.this method creates a new array that is
     * larger than the former array
     */
    private void growQueue() {
        Integer[] newArr = new Integer[this.size * 2];
        for (int i = 0; i < this.size; i++) {
            newArr[i] = array[i];
        }
        this.array = newArr;
    }

    /**
     * Enqueue. This method adds a value to the queue
     *
     * @param value
     *            the value to be added
     */
    @Override
    public void enqueue(int value) {
        if (size >= DEFAULT) {
            growQueue();
        }
        array[this.size] = value;
        this.size++;

    }

    /**
     * Dequeue. This method returns the value at the front of
     * the queue and returns the value
     *
     * @return the int. The value
     */
    @Override
    public int dequeue() {
        if (this.size == 0) {
            return -1;
        }
        int tmp = array[0];
        shrinkQueue();
        this.size -= 1;
        return tmp;
    }

    /**
     * Peek. This method returns the value at the front of
     * the queue
     *
     * @return the int. The value
     */
    @Override
    public int peek() {
        if (this.size == 0) {
            return -1;
        } else {
            return array[0];
        }
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true, if is empty
     */
    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Size. Returns the size of the queue
     *
     * @return the int. the size
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Clear. Clears the whole queue
     */
    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            array[i] = null;
        }
        this.size = 0;

    }

    /**
     * To string. This method creates a string representation
     * of the queue
     *
     * @return the string
     */
    @Override
    public String toString() {
        if (this.size == 0) {
            return "{}";
        }
        String retStr = "{";
        retStr += array[0];
        for (int i = 1; i < this.size; i++) {
            retStr += "," + array[i];
        }
        retStr += "}";

        return retStr;
    }

    /**
     * Equals. This method checks to see if the input
     * is equal to the instance of the class.
     *
     * @param o
     *            the Objects to be checked
     * @return true, if successful
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ArrayQueue) {
            ArrayQueue test = (ArrayQueue) o;
            if (test.size == this.size) {
                for (int i = 0; i < this.size; i++) {
                    if (test.array[i] != this.array[i]) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
