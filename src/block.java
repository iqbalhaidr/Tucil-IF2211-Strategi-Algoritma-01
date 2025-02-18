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

    public block mirror() {
        int row = buffer.length;
        int col = buffer[0].length;
        int[][] mirrored = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mirrored[i][col - 1 - j] = buffer[i][j];
            }
        }
        return new block(id, mirrored);
    }

    public block rotate() {
        int row = buffer.length;
        int col = buffer[0].length;
        int[][] rotated = new int[col][row];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                rotated[j][row - 1 - i] = buffer[i][j];
            }
        }
        return new block(id, rotated);
    }

    public void print() {
        for (int[] row : buffer) {
            for (int cell : row) {
                System.out.print(cell == 1 ? id : '.');
            }
            System.out.println();
        }
    }

    /* ======================================================================== */
    public static void main(String[] args) {
        int[][] buffer = {
                { 1, 0 },
                { 1, 0 },
                { 1, 1 }
        };

        block block = new block('B', buffer);
        System.out.println("Original:");
        block.print();

        System.out.println("\nRotated:");
        block b = block.rotate();
        b.print();
        b.rotate().print();

        System.out.println("\nMirrored:");
        block.mirror().print();
    }
    /* ======================================================================== */
}
