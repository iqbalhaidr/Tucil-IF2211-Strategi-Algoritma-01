public class block {
    private char id;
    private int[][] buffer;

    public block(char id, int[][] buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    public char getId() {
        return id;
    }

    public int[][] getBuffer() {
        return buffer;
    }

    public int getRowLen() {
        return buffer.length;
    }

    public int getColLen() {
        return buffer[0].length;
    }

    public void mirror() {
        int row = buffer.length;
        int col = buffer[0].length;
        int[][] mirrored = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mirrored[i][col - 1 - j] = buffer[i][j];
            }
        }
        buffer = mirrored;
    }

    public void rotate() {
        int row = buffer.length;
        int col = buffer[0].length;
        int[][] rotated = new int[col][row];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                rotated[j][row - 1 - i] = buffer[i][j];
            }
        }
        buffer = rotated;
    }

    public void print() {
        for (int[] row : buffer) {
            for (int cell : row) {
                System.out.print(cell == 1 ? id : '.');
            }
            System.out.println();
        }
    }
}
