/**
 * AUTHOR: Nate Patanjo
 * COURSE: CSC 210
 * FILE: ListStack.java Mar 16, 2021
 * ASSIGNMENT: PA7-StacksQueues
 * PURPOSE: The purpose of this program is to create a stack
 * using a LinkedList.
 * 
 * USAGE:
 * Input (after initialization):
 * stack.push(1)
 * stack.push(2)
 * System.out.println(stack.toString());
 * stack.pop()
 * System.out.println(stack.toString());
 * 
 * Output:
 * {1,2}
 * {1}
 */

public class ListStack implements StackInterface {

    /** The front. */
    private Node front;

    /** The back. */
    private Node back;

    /** The size. */
    private int size;


    /**
     * Instantiates a new list stack.
     */
    public ListStack() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    /**
     * Instantiates a new list stack copy.
     *
     * @param copy
     *            the stack you want to copy
     */
    public ListStack(ListStack copy) {
        this.size = copy.size;
        this.front = null;
        this.back = null;
        for (int i = 0; i < copy.size; i++) {
            if (this.front == null) {
                this.front = new Node(copy.get(i), null);
                this.back = this.front;
            } else {
                this.back.next = new Node(copy.get(i + 1), null);
                this.back = this.back.next;
            }
        }
    }

    /**
     * Push. This method adds a new value to the stack
     *
     * @param value
     *            the value to be added to the stack
     */
    @Override
    public void push(int value) {
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
     * Gets the. This is a private method used to
     * get the value at a specific index
     *
     * @param index
     *            the index
     * @return the int value at the given index
     */
    private int get(int index) {
        Node curr = front;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

    /**
     * Pop. This method removes the value at the top
     * of the stack and returns the value
     *
     * @return the int the value at the top
     *         of the stack
     */
    @Override
    public int pop() {
        if (this.size > 1) {
            int tmp = get(size);
            Node curr = front;
            Node prev = null;
            for (int i = 0; i < size - 1; i++) {
                prev = curr;
                curr = curr.next;
            }
            curr = null;
            prev.next = curr;
            size -= 1;
            return tmp;
        } else if (this.size == 1) {
            int tmp = front.value;
            this.clear();
            return tmp;
        } else {
            return -1;
        }
    }

    /**
     * Peek. This method returns the value at the
     * top of the stack
     *
     * @return the int the value at the top
     *         of the stack
     */
    @Override
    public int peek() {
        if (this.size > 0) {
            return get(size);
        } else {
            return -1;
        }
    }

    /**
     * Checks if the stack is empty.
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
     * Size. This method returns the size of the
     * stack
     *
     * @return the int. this is the size of the stack
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Clear. this method clears the whole stack
     */
    @Override
    public void clear() {
        front = null;
        back = null;
        size = 0;
    }

    /**
     * To string. This method creates a string representation
     * of the stack
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
                retStr += curr.value;
            }
        }
        return retStr += "}";
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
        if (o instanceof ListStack) {
            ListStack test = (ListStack) o;
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
