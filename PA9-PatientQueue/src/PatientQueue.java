/**
 * AUTHOR: Nate Patanjo
 * FILE: PatientQueue.java
 * ASSIGNMENT: Programming Assignment 9
 * COURSE: CSC210
 * PURPOSE: The purpose of this class is to create a patient
 * queue list that treats patients in order and accounting for
 * illness severity (priority). This is done using a min Heap
 * data structure
 * 
 * USAGE:
 * This program works like a normal queue except you
 * can input a priority.
 * INPUT:
 * PatientQueue test = new PatientQueue();
 * System.out.println(test.toString());
 * System.out.println(test.isEmpty());
 * test.enqueue(new Patient("Happy", 9));
 * System.out.println(test.toString());
 * test.enqueue(new Patient("Nate", 23));
 * System.out.println(test.toString());
 * test.enqueue(new Patient("Max", 65));
 * System.out.println(test.toString());
 * test.enqueue(new Patient("Z", 9));
 * System.out.println(test.toString());
 * System.out.println(test.dequeue());
 * System.out.println(test.toString());
 * System.out.println(test.dequeue());
 * System.out.println(test.toString());
 * System.out.println(test.size());
 * System.out.println(test.toString());
 * 
 * OUTPUT:
 * {}
 * true
 * {Happy (9)}
 * {Happy (9), Nate (23)}
 * {Happy (9), Nate (23), Max (65)}
 * {Happy (9), Z (9), Max (65), Nate (23)}
 * Happy
 * {Z (9), Nate (23), Max (65)}
 * Z
 * {Nate (23), Max (65)}
 * 2
 * {Nate (23), Max (65)}
 * 
 */
public class PatientQueue {

    /** The Constant DEFAULT_SIZE. */
    private static final int DEFAULT_SIZE = 10;

    /** The array. */
    private Patient[] array;

    /** The size. */
    private int size;

    /** The capacity. */
    private int capacity;

    /**
     * Instantiates a new patient queue.
     */
    public PatientQueue() {
        size = 0;
        array = new Patient[DEFAULT_SIZE];
        capacity = DEFAULT_SIZE;
    }

    /**
     * Grow queue. If the size of the queue exceeds the size
     * of the array this method is called to grow the queue
     */
    private void growQueue() {
        Patient[] newArr = new Patient[this.capacity * 2];
        for (int i = 1; i <= this.size; i++) {
            newArr[i] = array[i];
        }
        this.array = newArr;
        capacity = capacity * 2;
    }

    /**
     * Parent index. This returns the parent index
     * of the index
     *
     * @param index
     *            the index
     * @return the int the parent
     */
    private int parentIndex(int index) {
        return index / 2;
    }

    /**
     * Left child. this returns the left child of the given
     * index
     *
     * @param index
     *            the index
     * @return the int the leftchild
     */
    private int leftChild(int index) {
        return index * 2;
    }

    /**
     * Right child. This returns the right child of the given
     * index
     *
     * @param index
     *            the index
     * @return the int the right child
     */
    private int rightChild(int index) {
        return index * 2 + 1;
    }

    /**
     * Enqueue. This is method overrides the enqueue method
     * and creates a patient object then calls the regular
     * enqueue method
     *
     * @param name
     *            the name
     * @param priority
     *            the priority
     */
    public void enqueue(String name, int priority) {
        enqueue(new Patient(name, priority));
    }

    /**
     * Enqueue. This method adds a new node to the queue
     * it also calls the bubble up method to move the node to
     * the correct place.
     *
     * @param value
     *            the value
     */
    public void enqueue(Patient value) {
        if (size >= capacity - 1) {
            growQueue();
        }
        array[this.size + 1] = value;
        this.size++;
        if (size > 1) {
            minHeapBubbleUp(size);
        }
    }

    /**
     * Dequeue.This method takes away the first patient in
     * the queue. it then calls bubble down to bring the next
     * lowest queue to the front
     *
     * @return the string the name of the removed patient
     * @throws NullPointerException
     *             the null pointer exception
     */
    public String dequeue() throws NullPointerException {
        if (this.isEmpty()) {
            throw new NullPointerException("Empty queue cannot be dequeued");
        }
        String retPat = array[1].name;
        moveBack();
        this.size -= 1;
        minHeapBubbleDown(1);
        return retPat;
    }

    /**
     * Move back. this method moves the last patient to the front
     * of the list. It is called in dequeue()
     */
    private void moveBack() {
        array[1] = array[size];
    }

    /**
     * Min heap bubble up. This method takes the most recently added
     * patient and moves it to the correct location.
     *
     * @param index
     *            the index also the size of the queue
     */
    private void minHeapBubbleUp(int index) {
        while (index > 1) {
            if (array[index].priority < array[parentIndex(index)].priority) {
                swap(index, parentIndex(index));
                index = parentIndex(index);
            } else {
                index = 1;
            }
        }
    }

    /**
     * Swap check. This method checks to see if the priority of the
     * two patients being check is the same. if it is than it swaps only
     * if the child is higher in alphabetical order. If the patients being
     * checked are not equal than the patients are swapped.
     *
     * @param index
     *            the index to be swapped
     * @param parent
     *            the parent to be swapped
     */
    private void swapCheck(int index, int parent) {
        if (array[index].priority == array[parent].priority) {
            int comp = array[index].name.compareTo(array[parent].name);
            if (comp < 0) {
                System.out.println(comp);
                swap(index, parent);
            }
        } else {
            swap(index, parent);
        }
    }

    /**
     * Swap. this takes to indices and swaps the values
     *
     * @param index
     *            the index to be swapped
     * @param parent
     *            the parent to be swapped
     */
    private void swap(int index, int parent) {
        Patient tmp = array[index];
        this.array[index] = array[parent];
        this.array[parent] = tmp;
    }

    /**
     * Min heap bubble down. this method recurses through the array
     * tree style. It moves the lowest priority to the top by calling
     * swapCheck()
     *
     * @param index
     *            the index
     */
    private void minHeapBubbleDown(int index) {
        if (leftChild(index) <= size) {
            if (array[leftChild(index)].priority < array[rightChild(
                    index)].priority) {
                if (array[index].priority >= array[leftChild(index)].priority) {
                    swapCheck(leftChild(index), index);
                    minHeapBubbleDown(leftChild(index));
                }
            } else {
                if (array[index].priority >= array[rightChild(
                        index)].priority) {
                    swapCheck(rightChild(index), index);
                    minHeapBubbleDown(rightChild(index));
                }
            }
        }
    }

    /**
     * Change priority. This method changes the priority of any
     * given patient
     *
     * @param name
     *            the name
     * @param newP
     *            the new Priority
     */
    public void changePriority(String name, int newP) {
        for (int i = 1; i <= size; i++) {
            if (array[i].name == name) {
                if (newP > array[i].priority) {
                    array[i] = new Patient(name, newP);
                    minHeapBubbleDown(i);
                    return;
                } else if (newP < array[i].priority) {
                    array[i] = new Patient(name, newP);
                    minHeapBubbleUp(i);
                    return;
                }
            }
        }
    }

    /**
     * Peek. This method returns the name of the patient at the
     * front of the queue.
     *
     * @return the string name
     * @throws NullPointerException
     *             the null pointer exception
     */
    public String peek() throws NullPointerException {
        if (this.isEmpty()) {
            throw new NullPointerException("Empty queue cannot be peeked");
        }
        return array[1].name;
    }

    /**
     * Peek priority. This method returns the priority of the patient
     * at the front of the queue
     *
     * @return the int priority
     * @throws NullPointerException
     *             the null pointer exception
     */
    public int peekPriority() throws NullPointerException {
        if (this.isEmpty()) {
            throw new NullPointerException("Empty queue cannot be peeked");
        }
        return array[1].priority;
    }

    /**
     * Size. This method returns the size of the queue
     *
     * @return the int size
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the queue is empty.
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
     * Clear. This clears the whole queue
     */
    public void clear() {
        for (int i = 1; i <= size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * To string. This method creates a string representation
     * of the queue.
     *
     * @return the string
     */
    public String toString() {
        String retStr = "{";
        if (size > 0) {
            retStr += array[1].toString();
        }
        for (int i = 2; i <= size; i++) {
            retStr += ", " + array[i].toString();
        }
        return retStr += "}";
    }

}
