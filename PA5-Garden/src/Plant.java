
public class Plant {

    private char blankPlant;
    private int row;
    private int col;

    Plant() {
        this.blankPlant = '.';
    }

    public String toString() {
        return String.valueOf(blankPlant);
    }
}
