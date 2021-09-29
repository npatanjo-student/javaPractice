/*
 * There is no requirement for a file header comment for this 
 * assignment. Spend your time writing good testcases instead!
 */
import java.util.Queue;
import java.util.Stack;

public class Recursion {

	/**
	 * Write a recursive function that finds the index of s2 in s1. Do not use any
	 * string functions except for .length(), .equals(), and .substring(). Do not use
	 * any loops, or any data structures.
	 * @param s1
	 * @param s2
	 * @return Returns the index of the first time that
	 * 			s2 appears in s1 or -1 if s2 is not contained
	 * 			in s1.
	 */
	public static int indexOf(String s1, String s2) {
		int s2Len = s2.length();
        if (s2.isEmpty()) {
        	return -1;
        }
		return indexOfCounter(s1, s2, -s2Len);
	}
	public static int indexOfCounter(String s1, String s2, int counter) {
      if (s2.isEmpty()) {
			return counter;
		}
		if (s1.isEmpty()) {
			return -1;
		}
		if (s1.charAt(0) == s2.charAt(0)) {
			return indexOfCounter(s1.substring(1), s2.substring(1), counter + 1);
		} else {
			return indexOfCounter(s1.substring(1), s2, counter + 1);
		}
	}

	/**
	 * Write a recursive function that removes the first k even numbers
	 * from the stack. If there are less than k even elements in the stack,
	 * just remove all even elements. Do not use any loops or data structures
	 * other than the stack passed in as a parameter.
	 * @param stack
	 * @param k
	 * @return Returns the number of elements removed from the stack.
	 */
	public static int removeEvenNumbers(Stack<Integer> stack, int k) {
		return removeEvenNumbersHelper(stack, k, 0);
	}
	public static int removeEvenNumbersHelper(Stack<Integer> stack, int k, int numbersRemoved) {
		if (stack.empty()) {
			return numbersRemoved;
		}
		if (stack.pop() % 2 == 0 && k > 0) {
			return removeEvenNumbersHelper(stack, k - 1, numbersRemoved + 1);
		} else {
			return removeEvenNumbersHelper(stack, k, numbersRemoved);
		}
	}

	/**
	 * Write a recursive function that accepts an integer and
	 * returns a new number containing only the even digits, in the same
	 * order. If there are no even digits, return 0. Your function should
	 * work for positive or negative numbers. You are NOT allowed
	 * to use any data structures. You are not allowed to use Strings to
	 * solve this problem either.
	 * @param n
	 * @return The input with only the even digits remaining in the same
	 * order.
	 */
	public static int evenDigits(int n) {
		if (n < 0) {
			evenDigits(n*-1);
		}
		if (n < 10) {
			if (n % 2 == 0) {
				return n;
			} else {
				return 0;
			}
		}
		int remainder = n % 10;
		int nextNum = n / 10;
		if (remainder % 2 == 0) {
			return evenDigits(nextNum) * 10 + remainder;
		} else {
			return evenDigits(nextNum);
		}
	}

	/**
	 * Write a recursive function that evaluates a Queue<Character> as a mathematical
	 * expression. This queue can have any of the following characters:
	 * { ' , '+', '*'} or any single digit number. Evaluate this expression and
	 * return the result. For example, for the expression:
	 * "(((1+2)*(3+1))+(1*(2+2)))", each of these characters would be in the
	 * q. As you recursively evaluate characters from the expression, you will
	 * remove the characters from the q. After evaluating the above expression,
	 * you should return 16. You are guaranteed that there are NO two digit numbers,
	 * and that the expression is well formed (parenthesis match, etc...). Do not use any
	 * loops. Do not use any data structures besides the q passed in as a parameter.
	 * @param q
	 * @return The result of the mathematical expression.
	 */
	public static int evaluate(Queue<Character> q) {
		if (q.isEmpty()) {
			return 0;
		}
		int val = 0;
		Character firstChar = q.remove();
		if (Character.isDigit(firstChar)) {
			val = Character.digit(firstChar, 10);
		} else if (firstChar == '('){
			val = evaluate(q);
		}
		if (q.peek() == '+' || q.peek() == '*') {
			Character operator = q.remove();
			if (operator == '+') {
				val += evaluate(q);
			} else if (operator == '*') {
				val *= evaluate(q);
			}
		}
		if (firstChar == '(') {
			q.remove();
		}
		return val;
	}


	/**
	 * Write a recursive function that accepts a stack of integers and
	 * replaces each int with two copies of that integer. For example,
	 * calling repeatStack and passing in a stack of { 1, 2, 3} would change
	 * the stack to hold { 1, 1, 2, 2, 3, 3}. Do not use any loops. Do not use
	 * any data structures other than the stack passed in as a parameter.
	 * @param stack
	 */
	public static void repeatStack(Stack<Integer> stack) {
		if (!stack.empty()) {
			repeatStackHelper(stack);
		}
	}
	public static Stack<Integer> repeatStackHelper(Stack<Integer> stack) {
		if (!stack.empty()) {
			int stackNum = stack.peek();
			stack.pop();
			stack = repeatStackHelper(stack);
			stack.push(stackNum);
			stack.push(stackNum);
		}
		return stack;
		
	}

	/**
	 * Write a recursive function that accepts a Queue<Integer>. It
	 * should change every int in this queue to be double its original
	 * value. You may NOT use loops or any other data structures besides
	 * the queue passed in as a parameter. You may use a helper function.
	 * @param q
	 */
	public static void doubleElements(Queue<Integer> q) {
		if (q.size() > 0) {
			int index = q.size();
			doubleElementsHelper(q, index);
		}
	}
	public static Queue<Integer> doubleElementsHelper(Queue<Integer> q, int index) {
		if (index > 0) {
			int qNum = q.peek();
			q.remove();
			q.add(qNum * 2);
			q = doubleElementsHelper(q, index - 1);
		}
		return q;
		
	}

}


















