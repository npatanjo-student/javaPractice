public class PA5Main {
    public static void main(String[] args) {
        Plant testPlant = new Plant();
        Garden testGard = new Garden(4, 6, testPlant);
        testGard.gardenFill();
        System.out.println(testPlant.toString());
        System.out.println(testGard.toString());

    }
}
