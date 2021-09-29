
public class Main {

    public static void main(String[] args) {

        PatientQueue test = new PatientQueue();
        System.out.println(test.toString());
        System.out.println(test.isEmpty());
        test.enqueue(new Patient("Happy", 9));
        System.out.println(test.toString());
        test.enqueue(new Patient("justin", 26));
        System.out.println(test.toString());
        test.enqueue(new Patient("crazy", 4));
        System.out.println(test.toString());
        test.enqueue(new Patient("apple", 6));
        System.out.println(test.toString());
        test.enqueue(new Patient("Max", 65));
        System.out.println(test.toString());
        test.enqueue(new Patient("harod", 8));
        System.out.println(test.toString());
        test.enqueue(new Patient("Nate", 23));
        System.out.println(test.toString());
        test.enqueue(new Patient("Z", 9));
        System.out.println(test.toString());
        System.out.println(test.dequeue());
        System.out.println(test.toString());
        System.out.println(test.dequeue());
        System.out.println(test.toString());
        System.out.println(test.dequeue());
        System.out.println(test.toString());
        System.out.println(test.dequeue());
        System.out.println(test.toString());
        System.out.println(test.dequeue());
        System.out.println(test.toString());
        System.out.println(test.size());
        System.out.println(test.toString());




    }

}
