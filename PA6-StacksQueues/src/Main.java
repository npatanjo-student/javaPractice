
public class Main {

    public static void main(String[] args) {
        listStackRepeatStack_test2();

        ListStack test = new ListStack();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        repeatStack(test);
        System.out.println(test);

    }

    public static void listStackRepeatStack_test2() {
        StackInterface stack = new ListStack();
        repeatStack(stack);
        // assertEquals("{}", stack.toString());
        System.out.println(stack.toString());
    }

    public static void repeatStack(StackInterface stack) {
        if (stack.isEmpty()) {
            return;
        }
        int value = stack.pop();
        repeatStack(stack);
        stack.push(value);
        stack.push(value);
    }

}
