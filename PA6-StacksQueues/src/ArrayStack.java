/**
 * AUTHOR: Nate Patanjo
 * COURSE: CSC 210
 * FILE: ArrayStack.java Mar 16, 2021
 * ASSIGNMENT: PA6-StacksQueues
 * PURPOSE: The purpose of this program is to create a stack
 * using an ArrayList.
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
public class ArrayStack implements StackInterface {

    /** The Constant DEFAULT. */
    private static final int DEFAULT = 10;

    /** The array. */
    private Integer[] array;

    /** The size. */
    private int size;

    /**
     * Instantiates a new array stack.
     */
    public ArrayStack() {
        size = 0;
        array = new Integer[DEFAULT];
    }

    /**
     * Instantiates a new array stack copy.
     *
     * @param copy
     *            the stack you want to copy
     */
    public ArrayStack(ArrayStack copy) {
        this.size = copy.size;
        this.array = new Integer[this.size];
        for (int i = 0; i < this.size; i++) {
            this.array[i] = copy.array[i];
        }
    }

    /**
     * Grow stack. this is used to create a new array
     * if you add a value and the size is greater than the
     * size of the already existing array
     */
    private void growStack() {
        Integer[] newArr = new Integer[this.size * 2];
        for (int i = 0; i < this.size; i++) {
            newArr[i] = array[i];
        }
        this.array = newArr;
    }

    /**
     * Push. This method adds a new value to the stack
     *
     * @param value
     *            the value to be added to the stack
     */
    @Override
    public void push(int value) {
        if (size >= DEFAULT) {
            growStack();
        }
        array[this.size] = value;
        this.size++;
    }

    /**
     * Pop.This method removes the value at the top
     * of the stack and returns the value
     *
     * @return the int the value at the top
     *         of the stack
     */
    @Override
    public int pop() {
        if (this.size == 0) {
            return -1;
        }
        int tmp = array[this.size - 1];
        array[this.size] = null;
        this.size -= 1;
        return tmp;
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
        if (this.size == 0) {
            return -1;
        } else {
            return array[this.size - 1];
        }
    }

    /**
     * Checks if the stack is empty.
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
     * Size. This method returns the size of the
     * stack
     *
     * @return the int. this is the size of the stack
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Clear. this method clears the whole stack
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
     * of the stack
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
        if (o instanceof ArrayStack) {
            ArrayStack test = (ArrayStack) o;
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
