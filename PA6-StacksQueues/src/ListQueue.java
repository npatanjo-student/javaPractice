/**
 * AUTHOR: Nate Patanjo
 * COURSE: CSC 210
 * FILE: ListQueue.java Mar 16, 2021
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
public class ListQueue implements QueueInterface {

    /** The front. */
    private Node front;

    /** The back. */
    private Node back;

    /** The size. */
    private int size;

    /**
     * Instantiates a new list queue.
     */
    public ListQueue() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    /**
     * Instantiates a new list queue copy.
     *
     * @param copy
     *            the queue to get copied
     */
    public ListQueue(ListQueue copy) {
        this.size = copy.size;
        this.front = null;
        this.back = null;
        for (int i = 0; i < copy.size; i++) {
            if (this.front == null) {
                this.front = new Node(copy.front.value, null);
                this.back = this.front;
            } else {
                this.back.next = new Node(copy.front.next.value, null);
                this.back = this.back.next;
            }
        }
    }

    /**
     * Enqueue. This method adds a value to the queue
     *
     * @param value
     *            the value to be added
     */
    @Override
    public void enqueue(int value) {
        if (front == null) {
            front = new Node(value, null);
            back = front;
        } else {
            back.next = new Node(value, null);
            back = back.next;
        }
        size++;
    }

    /**
     * Dequeue. This method returns the value at the front of
     * the queue and returns the value
     *
     * @return the int. The value
     */
    @Override
    public int dequeue() {
        if (this.size > 0) {
            int tmp = front.value;
            front = front.next;
            size -= 1;
            return tmp;
        } else {
            return -1;
        }
    }

    /**
     * Peek. This method returns the value at the front of
     * the queue
     *
     * @return the int. The value
     */
    @Override
    public int peek() {
        if (this.size > 0) {
            return front.value;
        } else {
            return -1;
        }
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true, if is empty
     */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Size. Returns the size the queue
     *
     * @return the int. the size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Clear. Clears the whole queue
     */
    @Override
    public void clear() {
        front = null;
        back = null;
        size = 0;
    }

    /**
     * To string. This method creates a string representation
     * of the queue
     *
     * @return the string
     */
    @Override
    public String toString() {
        String retStr = "{";
        Node curr = front;
        for (int i = 0; i < size; i++) {
            if (i < size - 1) {
                retStr += curr.value + ",";
                curr = curr.next;
            } else {
                retStr += curr.value + "}";
            }
        }
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
        if (o instanceof ListQueue) {
            ListQueue test = (ListQueue) o;
            if (test.size == this.size) {
                Node currTest = test.front;
                Node currThis = this.front;
                for (int i = 0; i < this.size; i++) {
                    if (currTest.value != currThis.value) {
                        return false;
                    } else {
                        currTest = currTest.next;
                        currThis = currThis.next;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * PURPOSE:
     * this class just creates the nodes to be used in
     * the linked list
     */
    class Node {

        /** The value. */
        private int value;

        /** The next. */
        private Node next;

        /**
         * Instantiates a new node.
         */
        public Node() {
            this(0, null);
        }

        /**
         * Instantiates a new node.
         *
         * @param value
         *            the value of the node
         * @param next
         *            the next pointer
         */
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

}
