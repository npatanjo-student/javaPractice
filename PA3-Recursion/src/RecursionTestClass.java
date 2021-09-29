/*
 * This file will hold all of your testcases. Remember, to run all
 * of your tests, right-click on 'RunTests.java' and select 'Run As' ->
 * JUnit Test.
 */
import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;

public class RecursionTestClass {
	
	/*
	 * Here I have provided an example of one of the tests that I
	 * would write. For each of your tests, leave a brief comment
	 * above the test specifying its purpose. For example, for this
	 * test, I might write, "indexOf_test1 tests when s2 is not a 
	 * substring of s1. This should return -1."
	 */
    @Test
    public void test_indexOf_test1() {
        int result = Recursion.indexOf("Hello", "ell");
        System.out.println("indexOf(Hello, ell), got " + result);
        assertEquals(1, result);
    }
    /*
     * this tests for standard string input
     */
    @Test
    public void test_indexOf_test2() {
        int result = Recursion.indexOf("Hello", "l");
        System.out.println("indexOf(Hello, l), got " + result);
        assertEquals(2, result);
    }
    /*
     * this tests for an empty string input
     */
    @Test
    public void test_indexOf_test3() {
        int result = Recursion.indexOf("Hello", "");
        System.out.println("indexOf(Hello, ), got " + result);
        assertEquals(-1, result);
    }
    /*
     * this test for an input that is not in the string
     */
    @Test
    public void test_indexOf_test4() {
        int result = Recursion.indexOf("Hello", "World");
        System.out.println("indexOf(Hello, World), got " + result);
        assertEquals(-1, result);
    }
    /*
     * this tests cases sensitivity
     */
    @Test
    public void test_indexOf_test5() {
        int result = Recursion.indexOf("Hello", "H");
        System.out.println("indexOf(Hello, H), got " + result);
        assertEquals(0, result);
    }
    /*
     * this tests a standard stack of numbers alternating 
     * odd, even
     */
    @Test
    public void test_removeEvenNumbers_test1() {
    	Stack<Integer> stack = new Stack<Integer>();
    	for (int i = 0; i <= 9; i++) {
    		stack.push(i);
    	}
        int result = Recursion.removeEvenNumbers(stack, 3);
        System.out.println("removeEvenNumbers({9, 8, 7, 6, 5, 4, 3, 2, 1}, 3), got " + result);
        assertEquals(3, result);
    }
    /*
     * this is another standard test with numbers not alternating
     */
    @Test
    public void test_removeEvenNumbers_test2() {
    	Stack<Integer> stack = new Stack<Integer>();
    	stack.push(2);
    	stack.push(3);
    	stack.push(4);
    	stack.push(6);
    	stack.push(8);
    	stack.push(9);
        int result = Recursion.removeEvenNumbers(stack, 7);
        System.out.println("removeEvenNumbers({9, 8, 6, 4, 3, 2}, 3), got " + result);
        assertEquals(4, result);
    }
    /*
     * this tests an empty stack
     */
    @Test
    public void test_removeEvenNumbers_test3() {
    	Stack<Integer> stack = new Stack<Integer>();
        int result = Recursion.removeEvenNumbers(stack, 7);
        System.out.println("removeEvenNumbers({}, 3), got " + result);
        assertEquals(0, result);
    }
    /*
     * this tests an empty stack with a negative param
     */
    @Test
    public void test_removeEvenNumbers_test4() {
    	Stack<Integer> stack = new Stack<Integer>();
    	stack.push(2);
    	stack.push(3);
    	stack.push(4);
    	stack.push(6);
    	stack.push(8);
    	stack.push(9);
        int result = Recursion.removeEvenNumbers(stack, -1);
        System.out.println("removeEvenNumbers({9, 8, 6, 4, 3, 2}, -1), got " + result);
        assertEquals(0, result);
    }
    /*
     * this tests a standard input with alternating odd, even
     */
    @Test
    public void test_evenDigits_test1() {
        int result = Recursion.evenDigits(2345);
        System.out.println("evenDigits(2345), got " + result);
        assertEquals(24, result);
    }
    /*
     * this is another standard test but without alternating
     */
    @Test
    public void test_evenDigits_test2() {
        int result = Recursion.evenDigits(332245);
        System.out.println("evenDigits(332245), got " + result);
        assertEquals(224, result);
    }
    /*
     * this is a test with no even numbers
     */
    @Test
    public void test_evenDigits_test3() {
        int result = Recursion.evenDigits(1);
        System.out.println("evenDigits(1), got " + result);
        assertEquals(0, result);
    }
    /*
     * this is a test with only even numbers
     */
    @Test
    public void test_evenDigits_test4() {
        int result = Recursion.evenDigits(2);
        System.out.println("evenDigits(2), got " + result);
        assertEquals(2, result);
    }
    /*
     * this is a standard test with three numbers
     */
    @Test
    public void test_repeatStack_test1() {
    	Stack<Integer> stack = new Stack<Integer>();
    	stack.push(2);
    	stack.push(3);
    	stack.push(4);
        Recursion.repeatStack(stack);
        System.out.println("repeatStack({2, 3, 4}), got " + stack);
        assertEquals(stack.toString(), "[2, 2, 3, 3, 4, 4]");
    }
    /*
     * this is a test with an empty stack
     */
    @Test
    public void test_repeatStack_test2() {
    	Stack<Integer> stack = new Stack<Integer>();
        Recursion.repeatStack(stack);
        System.out.println("repeatStack({2, 3, 4}), got " + stack);
        assertEquals(stack.toString(), "[]");
    }
    /*
     * this is a test with a negative number in the stack
     */
    @Test
    public void test_repeatStack_test3() {
    	Stack<Integer> stack = new Stack<Integer>();
    	stack.push(2);
    	stack.push(-3);
    	stack.push(4);
        Recursion.repeatStack(stack);
        System.out.println("repeatStack({2, 3, 4}), got " + stack);
        assertEquals(stack.toString(), "[2, 2, -3, -3, 4, 4]");
    }
    /*
     * this is a test with a repeating value
     */
    @Test
    public void test_repeatStack_test4() {
    	Stack<Integer> stack = new Stack<Integer>();
    	stack.push(2);
    	stack.push(-3);
    	stack.push(4);
    	stack.push(4);
        Recursion.repeatStack(stack);
        System.out.println("repeatStack({2, 3, 4}), got " + stack);
        assertEquals(stack.toString(), "[2, 2, -3, -3, 4, 4, 4, 4]");
    }
    /*
     * this is a test with a standard input of three numbers
     */
    @Test
    public void test_doubleElement_test1() {
    	Queue<Integer> queue = new LinkedList<Integer>();
    	queue.add(2);
    	queue.add(3);
    	queue.add(4);
        Recursion.doubleElements(queue);
        System.out.println("doubleElements({2, 3, 4}), got " + queue);
        assertEquals(queue.toString(), "[4, 6, 8]");
    }
    /*
     * this is a test with an empty queue
     */
    @Test
    public void test_doubleElement_test2() {
    	Queue<Integer> queue = new LinkedList<Integer>();
        Recursion.doubleElements(queue);
        System.out.println("doubleElements({2, 3, 4}), got " + queue);
        assertEquals(queue.toString(), "[]");
    }
    /*
     * this is a test with a negative value
     */
    @Test
    public void test_doubleElement_test3() {
    	Queue<Integer> queue = new LinkedList<Integer>();
    	queue.add(2);
    	queue.add(-3);
    	queue.add(4);
        Recursion.doubleElements(queue);
        System.out.println("doubleElements({2, 3, 4}), got " + queue);
        assertEquals(queue.toString(), "[4, -6, 8]");
    }
    /*
     * this is the given test case
     */
    @Test
    public void test_evaluate_test1() {
    	String expr = "(((1+2)*(3+1))+(1*(2+2)))";
    	Queue<Character> q = new LinkedList<Character>();
    	for(char ch: expr.toCharArray()) {
    		q.add(ch);
    	}
    	int result = Recursion.evaluate(q);
    	System.out.println("evaluate((((1+2)*(3+1))+(1*(2+2)))), got " + result);
    	assertEquals(result, 16);
    }
    /*
     * this tests a smaller equation
     */
    @Test
    public void test_evaluate_test2() {
    	String expr = "((1+2)*(3+1))";
    	Queue<Character> q = new LinkedList<Character>();
    	for(char ch: expr.toCharArray()) {
    		q.add(ch);
    	}
    	int result = Recursion.evaluate(q);
    	System.out.println("evaluate((1+2)*(3+1)), got " + result);
    	assertEquals(result, 12);
    }
    /*
     * this tests an empty queue
     */
    @Test
    public void test_evaluate_test3() {
    	String expr = "";
    	Queue<Character> q = new LinkedList<Character>();
    	for(char ch: expr.toCharArray()) {
    		q.add(ch);
    	}
    	int result = Recursion.evaluate(q);
    	System.out.println("evaluate(), got " + result);
    	assertEquals(result, 0);
    } 
}
