import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;


public class PatientQueueTestClass {

    @Test
    public void testEnqueue() {
        PatientQueue test1 = new PatientQueue();
        String expected = "{Ford (2),Rein (6),Eve (3),Ben (9),Wu (7),Sasha (8),Anat (4)}";

        test1.enqueue(new Patient("Anat", 4));
        test1.enqueue(new Patient("Ben", 9));
        test1.enqueue(new Patient("Sasha", 8));
        test1.enqueue(new Patient("Wu", 7));
        test1.enqueue(new Patient("Rein", 6));
        test1.enqueue(new Patient("Ford", 2));
        test1.enqueue(new Patient("Eve", 3));

        String result = test1.toString();
        assertEquals(expected, result);

    }

    @Test
    public void sizeNone() {
        PatientQueue test1 = new PatientQueue();
        int expected = 0;

        int result = test1.size();
        assertEquals(expected, result);

    }

    @Test
    public void size2() {
        PatientQueue test1 = new PatientQueue();
        int expected = 7;

        test1.enqueue(new Patient("Anat", 4));
        test1.enqueue(new Patient("Ben", 9));
        test1.enqueue(new Patient("Sasha", 8));
        test1.enqueue(new Patient("Wu", 7));
        test1.enqueue(new Patient("Rein", 6));
        test1.enqueue(new Patient("Ford", 2));
        test1.enqueue(new Patient("Eve", 3));

        int result = test1.size();
        assertEquals(expected, result);

    }

}
