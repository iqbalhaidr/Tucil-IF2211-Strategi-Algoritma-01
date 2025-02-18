import java.util.Arrays;

public class papan {
    private int row, col;
    private char[][] buffer;

    public papan(int row, int col) {
        this.row = row;
        this.col = col;
        this.buffer = new char[row][col];

        for (char[] rows : buffer) {
            Arrays.fill(rows, '.');
        }
    }

    public boolean checkPlacement(block b, int tlx, int tly) {
        int[][] buff = b.getBuffer();
        int rows = buff.length;
        int cols = buff[0].length;

        if (tlx + cols > col || tly + rows > row) {
            return false;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (buff[i][j] == '1' && buff[tly + i][tlx + j] != '.') {
                    return false;
                }
            }
        }

        return true;
    }

    public void place(block b, int tlx, int tly) {
        char id = b.getId();
        int[][] buff = b.getBuffer();
        int rows = buff.length;
        int cols = buff[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (buff[i][j] == 1) {
                    buffer[tly + i][tlx + j] = id;
                }
            }
        }
    }

    public void remove(block b, int tlx, int tly) {
        int[][] buff = b.getBuffer();
        int rows = buff.length;
        int cols = buff[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (buff[i][j] == 1) {
                    buffer[tly + i][tlx + j] = '.';
                }
            }
        }
    }

    public void display() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(buffer[i][j]);
            }
            System.out.println();
        }
    }

    /* ======================================================================== */
    public static void main(String[] args) {
        papan p = new papan(5, 5);

        int[][] buffer = {
                { 1, 0 },
                { 1, 0 },
                { 1, 1 }
        };
        block b = new block('A', buffer);
        block c = b.rotate();
        p.place(c, 1, 0);
        // p.display();
        p.place(b, 0, 0);
        // p.remove(b, 0, 0);
        // System.out.println();
        p.display();
        // System.out.println(p.checkPlacement(b, 4, 4));
    }
}
