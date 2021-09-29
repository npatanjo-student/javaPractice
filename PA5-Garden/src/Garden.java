public class Garden {

    private Plant[][] grid;
    private int rowCount, colCount;
    private Plant plant;

    public Garden(int rowCount, int colCount, Plant plant) {
        this.grid = new Plant[rowCount][colCount];
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.plant = plant;
    }

    public void gardenFill() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                grid[i][j] = plant;
            }
        }
    }

    public String toString() {
        String retStr = "";
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                retStr += grid[i][j] + " ";
            }
            retStr += "\n";
        }
        return retStr;
    }

}
